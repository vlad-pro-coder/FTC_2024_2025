package org.firstinspires.ftc.teamcode.IntoTheDeep;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;



public class motorWraper {
    public enum DIRECTION{
        FORWARD,
        INVERSE
    }

    private final DcMotorEx motor;

    public motorWraper(HardwareMap hardwareMap,String HubId,DIRECTION direction){

        motor = hardwareMap.get(DcMotorEx.class,HubId);

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor.setDirection(DcMotor.Direction.FORWARD);
        switch(direction) {
            case FORWARD:
                motor.setDirection(DcMotor.Direction.FORWARD);
            case INVERSE:
                motor.setDirection(DcMotor.Direction.REVERSE);
        }

        MotorConfigurationType mct = motor.getMotorType();
        mct.setAchieveableMaxRPMFraction(1);
        motor.setMotorType(mct);
    }

    public void setPower(double power){
        motor.setPower(power);
    }
    public double getPos(){
        return motor.getCurrentPosition();
    }

}
