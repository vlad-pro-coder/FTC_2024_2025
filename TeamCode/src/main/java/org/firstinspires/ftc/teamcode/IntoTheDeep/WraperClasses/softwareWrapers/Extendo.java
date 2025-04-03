package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.ExtendoQueue;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.LiftQueue;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.main.qextendolength;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.IntoTheDeep.PIDModule;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.Task;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.TaskEnums;

@Config
public class Extendo {
    public static DcMotorEx m1,encoderExtendo;
    PIDModule pid = new PIDModule();
    public PIDCoefficients coefs = new PIDCoefficients(0.0015,0.00001,0.0007);
    public static Task currTask = new Task(0,30, TaskEnums.EXTEND_TO_POS);
    public static double p=0.006,d=0.0003,i=0.0001;
    public static double powertomotors;
    private double limExtendo = 870;
    private double pos = 0;

    private double posExtendo = 0;


    public Extendo(HardwareMap hardwareMap)
    {
        m1 = hardwareMap.get(DcMotorEx.class,"extendo1");
        encoderExtendo = hardwareMap.get(DcMotorEx.class,"mfl");

        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        encoderExtendo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encoderExtendo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        MotorConfigurationType mkt;
        mkt = m1.getMotorType();
        mkt.setAchieveableMaxRPMFraction(1);

        m1.setMotorType(mkt);
    }

    public static boolean CurrTaskDoneness(){
        return Math.abs(currTask.component1target - encoderExtendo.getCurrentPosition()) <= currTask.Treshhold;
    }

    public void  runExtendoContinuos(Gamepad gm1)
    {
        //teleop

        if(Math.abs(gm1.right_stick_y) <= 0.02) {
        double power = pid.Update(currTask.component1target - encoderExtendo.getCurrentPosition(), new PIDCoefficients(p,i,d));
        powertomotors = power;
        m1.setPower(-power);
        }
        else if(gm1.right_stick_y > 0.02) {
            if(encoderExtendo.getCurrentPosition() > 0)
                m1.setPower(gm1.right_stick_y);
            currTask.component1target = encoderExtendo.getCurrentPosition();
        }
        else if(gm1.right_stick_y < 0.02) {
            if(encoderExtendo.getCurrentPosition() < limExtendo)
                m1.setPower(gm1.right_stick_y);
            currTask.component1target = encoderExtendo.getCurrentPosition();
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
