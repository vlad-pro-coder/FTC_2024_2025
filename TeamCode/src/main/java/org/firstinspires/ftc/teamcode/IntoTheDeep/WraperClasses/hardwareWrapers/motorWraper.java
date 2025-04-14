package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

public class motorWraper{

    DcMotorEx m;

    public motorWraper(HardwareMap hardwareMap,String name){
        m = hardwareMap.get(DcMotorEx.class,name);
        MotorConfigurationType mkt;
        mkt = m.getMotorType();
        mkt.setAchieveableMaxRPMFraction(1);

        m.setMotorType(mkt);
    }

    public void setMode(DcMotorEx.RunMode mode){
        m.setMode(mode);
    }

    public void setPower(double power){
        m.setPower(power);
    }

    public double getCurrentPosition(){
        return m.getCurrentPosition();
    }

    public void setDirection(DcMotorEx.Direction direction){
        m.setDirection(direction);
    }
}