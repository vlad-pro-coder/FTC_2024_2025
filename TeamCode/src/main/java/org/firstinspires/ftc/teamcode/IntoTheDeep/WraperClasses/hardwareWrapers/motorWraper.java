package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.IntoTheDeep.PIDModule;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.MovingState;


public class motorWraper {
    public enum DIRECTION{
        FORWARD,
        INVERSE
    }

    PIDModule pidModule;
    private DcMotorEx motor;
    private double GoalPos = 0;

    public motorWraper(HardwareMap hardwareMap, String HubId, DIRECTION direction, PIDCoefficients pidcoefs){

        motor = hardwareMap.get(DcMotorEx.class,HubId);

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        switch(direction) {
            case FORWARD:
                motor.setDirection(DcMotor.Direction.FORWARD);
            case INVERSE:
                motor.setDirection(DcMotor.Direction.REVERSE);
        }

        MotorConfigurationType mct = motor.getMotorType();
        mct.setAchieveableMaxRPMFraction(1);
        motor.setMotorType(mct);

        pidModule = new PIDModule(pidcoefs);
    }

    public void setPower(double power){
        motor.setPower(power);
    }
    public double getPos(){
        return motor.getCurrentPosition();
    }
    public void setGoalPos(double Goal){
        GoalPos = Goal;
        pidModule.resetIntegral();
    }

    public void updGoalPos(){
        //other coeficients
        motor.setPower(pidModule.Update(GoalPos - getPos()));
    }
    public void updGoalVel(){
        //other coeficients
        motor.setVelocity(pidModule.Update(GoalPos - getPos()));
    }

    public void changeCoefs(PIDCoefficients pidcoefs){
        pidModule.SetPIDcoefs(pidcoefs);
    }

    public MovingState getState(){
        if(GoalPos == motor.getCurrentPosition())
            return MovingState.REACHED;
        return MovingState.MOVING;
    }



}
