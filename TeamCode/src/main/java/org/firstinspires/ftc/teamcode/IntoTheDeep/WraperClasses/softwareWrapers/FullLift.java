package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses.LinearFunction.resultfunction;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.LiftQueue;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.main.qliftlength;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.IntoTheDeep.PIDModule;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses.LinearFunction;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.Task;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.TaskEnums;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWithMotionProf;

@Config
public class FullLift {

    public static Lift lift;
    PTOsystem ptosystem;
    ServoWithMotionProf engage;
    private boolean isPTOActive = false;
    public static Task currTask = new Task(0, 30,TaskEnums.SPECIMEN_WALL_HEIGHT);
    private static PIDModule pid = new PIDModule();
    public static double p=20,d=4,i=1;//debug
    private PIDCoefficients twomotorscoefs = new PIDCoefficients(0.011,0.0005,0.0006);
    private PIDCoefficients ptomotorscoefs = new PIDCoefficients(0,0,0);
    public static double doneval = 0;//cam shit

    public static double startf=0,endf=825,resultstartf=-0.1,resultendf=0.25;

    //debug
    public static double powertomotors = 0;

    public FullLift(HardwareMap hardwareMap){
        lift = new Lift(hardwareMap);
        ptosystem = new PTOsystem(hardwareMap);
        engage = new ServoWithMotionProf(hardwareMap,"PTOengager",3000,4500,2000);

    }

    public void defaultPosition(){
        GlobalQueues.PutLiftTask(100,30,TaskEnums.GIVE_LIFT_POS);
    }

    public static boolean CurrTaskDoneness(){
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
        if(currTask != null) {
            if (currTask.TaskState == TaskEnums.ENGAGEPTO || currTask.TaskState == TaskEnums.DISENGAGEPTO) {
                engage.updProfile();
                doneval = engage.CurrPos;
            }
            else if(currTask.TaskState == TaskEnums.GIVE_POWER_TILL_POS){
                ///component2 este targetul
                //component1 este puterea
                doneval = -lift.getPosition();
                if(CurrTaskDoneness()) {
                    currTask.component1target = currTask.Treshhold;
                    currTask.Treshhold = 30;
                    currTask.TaskState = TaskEnums.GIVE_LIFT_POS;
                }
                lift.setPower(currTask.component1target);
            }
            else {
                double power = pid.Update(currTask.component1target + lift.getPosition(),
                        isPTOActive ? new PIDCoefficients(p,i,d) : twomotorscoefs)
                        + resultfunction(startf,endf,resultstartf,resultendf,-lift.getPosition());
                powertomotors = power;
                if (!isPTOActive)
                    lift.setPower(power);
                else {
                    lift.setPower(0);
                    ptosystem.setPower(power);
                }
                doneval = -lift.getPosition();
            }
        }

        qliftlength = LiftQueue.size();
        if(LiftQueue.isEmpty())
            return;
        if(currTask != null && !CurrTaskDoneness())
            return;
        if(currTask == null|| CurrTaskDoneness()) {
            this.currTask = LiftQueue.poll();
            switch (currTask.TaskState){
                case ENGAGEPTO: {
                    isPTOActive = true;
                    engage.setProfilePosition(currTask.component1target);
                    //ptosystem.PTOActivated();
                }
                case DISENGAGEPTO: {
                    isPTOActive = false;
                    engage.setProfilePosition(currTask.component1target);
                    //ptosystem.PTODeactivate();
                }
            }
            if(currTask.TaskState != TaskEnums.DISENGAGEPTO && currTask.TaskState != TaskEnums.ENGAGEPTO)
                pid.resetIntegral();
        }
    }
}
