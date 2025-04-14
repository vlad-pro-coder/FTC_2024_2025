package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.ExtendoQueue;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.LiftQueue;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.main.qextendolength;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.IntoTheDeep.PIDModule;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.Task;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.TaskEnums;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.motorWraper;

@Config
public class Extendo {
    private motorWraper m1,encoderExtendo;
    PIDModule pid = new PIDModule();
    private PIDCoefficients coefs = new PIDCoefficients(0.0065,0.0001,0.0003);
    private Task currTask = new Task(0,30, TaskEnums.EXTEND_TO_POS);
    public static double p=0.006,d=0.0003,i=0.0001;
    public static double powertomotors;
    private final double limMaxExtendo = 800;
    private final double limMinExtendo = 50;
    private double pos = 0;


    public Extendo(HardwareMap hardwareMap)
    {
        m1 = new motorWraper(hardwareMap,"extendo1");
        encoderExtendo = new motorWraper(hardwareMap,"mfl");

        m1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        m1.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        m1.setDirection(DcMotorSimple.Direction.REVERSE);

        encoderExtendo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encoderExtendo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public boolean CurrTaskDoneness(){
        return Math.abs(currTask.component1target - pos) <= currTask.Treshhold;
    }

    public void  runExtendoContinuos(Gamepad gm1)
    {
        //teleop

        pos = encoderExtendo.getCurrentPosition();
        double y = -gm1.right_stick_y;

        if(currTask.TaskState == TaskEnums.RETRACT_EXTENDO_FOR_CYCLE && !CurrTaskDoneness())
            currTask.TaskState = TaskEnums.EXTEND_TO_POS;

        if(Math.abs(y) <= 0.02 || (currTask.TaskState == TaskEnums.RETRACT_EXTENDO_FOR_CYCLE && !CurrTaskDoneness())) {
            double power = pid.Update(currTask.component1target - pos, coefs);
            powertomotors = power;
            if(Math.abs(currTask.component1target - pos) > 10)//opreste motor cand ajunge la destinatie
                m1.setPower(power);
            else
                m1.setPower(0);
        }
        else{
            if(y > 0.02 && pos < limMaxExtendo)
                m1.setPower(Math.signum(y) * y * y);
            else if(y < 0.02 && pos > limMinExtendo)
                m1.setPower(Math.signum(y) * y * y);
            else
                m1.setPower(0);
            currTask.component1target = Math.max(limMinExtendo,Math.min(pos,limMaxExtendo));
        }

        qextendolength = ExtendoQueue.size();

        if(ExtendoQueue.isEmpty())
            return;
        if(currTask != null && !CurrTaskDoneness())
            return;
        if(currTask == null || CurrTaskDoneness()) {
            currTask = ExtendoQueue.poll();
            pid.resetIntegral();
        }
    }
}
