package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;


public class Lift {

    DcMotorEx m1,m2;
    private double dm1 = 1,dm2 = 1;


    public Lift(HardwareMap hardwareMap)
    {
        m1 = hardwareMap.get(DcMotorEx.class,"mlift1");
        m2 = hardwareMap.get(DcMotorEx.class,"mlift2");

        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        MotorConfigurationType mkt;
        mkt = m1.getMotorType();
        mkt.setAchieveableMaxRPMFraction(1);

        m1.setMotorType(mkt);
        m2.setMotorType(mkt);
    }

    public void  setPower(double val)
    {
        m1.setPower(dm1 * val);
        m2.setPower(dm2 * val);
    }

    public void setDirections(double dm1,double dm2) {
        this.dm1 = dm1;
        this.dm2 = dm2;
    }

    public double getPosition(){
        return m1.getCurrentPosition();
    }
}
