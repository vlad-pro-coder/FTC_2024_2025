package org.firstinspires.ftc.teamcode.Teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

@Config
public class Lift {

    DcMotorEx l1,l2,l3;
    private double TargetPos = 0;
    public static PIDCoefficients pidcoef = new PIDCoefficients(0.01,0,0);
    PIDController pid = new PIDController(pidcoef);

    public Lift(HardwareMap hardwareMap){

        l1  = hardwareMap.get(DcMotorEx.class,"lift1");
        l2  = hardwareMap.get(DcMotorEx.class,"lift2");
        l3  = hardwareMap.get(DcMotorEx.class,"lift3");

        l1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        l1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        l2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        l3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        l3.setDirection(DcMotor.Direction.REVERSE);

        MotorConfigurationType mct = l1.getMotorType();
        mct.setAchieveableMaxRPMFraction(1);
        l1.setMotorType(mct);

        mct = l2.getMotorType();
        mct.setAchieveableMaxRPMFraction(1);
        l2.setMotorType(mct);

        mct = l3.getMotorType();
        mct.setAchieveableMaxRPMFraction(1);
        l3.setMotorType(mct);
    }

    public void upd(){

        pid.SetPIDcoefs(pidcoef);

        double power = pid.Update(TargetPos-l3.getCurrentPosition());

        //l1.setPower(power);
        //l2.setPower(power);
        l3.setPower(power);
    }
    public void setTargetPos(double Pos){
        TargetPos = Pos;
        pid.resetIntegral();
    }
    public double currPos(){
        return l3.getCurrentPosition();
    }
}
