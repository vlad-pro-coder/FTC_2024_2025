package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses.LinearFunction.resultfunction;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.LiftQueue;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.isPTOActive;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.main.qliftlength;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.IntoTheDeep.PIDModule;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.Task;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.TaskEnums;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWithMotionProf;

@Config
public class FullLift {

    public Lift lift;
    PTOsystem ptosystem;
    ServoWithMotionProf engage;
    private Task currTask = new Task(0, 30,TaskEnums.SPECIMEN_WALL_HEIGHT);
    private PIDModule pid = new PIDModule();
    public static double p=0.04,d=0,i=0;//debug
    private PIDCoefficients twomotorscoefs = new PIDCoefficients(0.011,0.0005,0.0006);
    private PIDCoefficients ptomotorscoefs = new PIDCoefficients(0.04,0,0);
    private double doneval = 0;//cam shit

    private double startf=0,endf=825,resultstartf=-0.11,resultendf=0.25;
    private boolean lastisPTOActive = false;

    //debug
    public static double powertomotors = 0;

    public FullLift(HardwareMap hardwareMap){
        lift = new Lift(hardwareMap);
        ptosystem = new PTOsystem(hardwareMap);
    }

    public void defaultPosition(){
        GlobalQueues.PutLiftTask(100,30,TaskEnums.GIVE_LIFT_POS);
    }

    public boolean CurrTaskDoneness(){
        if(currTask.TaskState == TaskEnums.GIVE_POWER_TILL_POS)
        {
            if(currTask.component1target < 0)
                return doneval <= currTask.Treshhold;
            return doneval >= currTask.Treshhold;
        }
        return Math.abs(doneval - currTask.component1target) <= currTask.Treshhold;
    }

    public void  runFullLiftContinuos()
    {
        if(isPTOActive != lastisPTOActive){
            if(isPTOActive)
                ptosystem.PTOActivated();
            else
                ptosystem.PTODeactivate();
            lastisPTOActive = isPTOActive;
        }
        if(currTask != null) {
                double power = pid.Update(currTask.component1target + lift.getPosition(),
                        isPTOActive ? ptomotorscoefs : twomotorscoefs)
                        + (isPTOActive ? 0:resultfunction(startf,endf,resultstartf,resultendf,-lift.getPosition()));
                powertomotors = power;
                if (!isPTOActive) {
                    if(currTask.TaskState != TaskEnums.SAMPLE_LOW_BASKET_HEIGHT && currTask.TaskState != TaskEnums.SAMPLE_HIGH_BASKET_HEIGHT && Math.abs(currTask.component1target + lift.getPosition()) < 15)
                        lift.setPower(0);
                    lift.setPower(power);
                }
                else {
                    lift.setPower(0);
                    ptosystem.setPower(power);
                }
                doneval = -lift.getPosition();
        }

        qliftlength = LiftQueue.size();
        if(LiftQueue.isEmpty())
            return;
        if(currTask != null && !CurrTaskDoneness())
            return;
        if(currTask == null|| CurrTaskDoneness()) {
            this.currTask = LiftQueue.poll();
            pid.resetIntegral();
        }
    }
}
