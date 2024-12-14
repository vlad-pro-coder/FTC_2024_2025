package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.moduleWrapers;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Extendo;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.IntakeRollingMotor;

enum IntakeState {
    STOPPED,
    ROTATETOTAKE,
    ROTATETOEJECT
}
public class Intake {

    private IntakeState Currstate;
    private Extendo extendo;
    Gamepad prev = new Gamepad();
    private double Goal=0;
    IntakeRollingMotor rollingMotor;

    public Intake(HardwareMap hardwareMap){
        extendo = new Extendo(hardwareMap);
        rollingMotor = new IntakeRollingMotor(hardwareMap);
        Currstate = IntakeState.STOPPED;
    }

    public double getPos(){
        return extendo.getPos();
    }

    public void setGoalExtendoMotor(double pos){
        extendo.setGoal(pos);
    }

    public void GoalUpd(){
        extendo.updPos();
    }

    public void updIntake(Gamepad gamepad){

        if(gamepad.x!=prev.x&&gamepad.x){
            if(Currstate == IntakeState.ROTATETOEJECT)
                Currstate = IntakeState.ROTATETOTAKE;
            else
                Currstate = IntakeState.ROTATETOEJECT;
        }
        if(gamepad.dpad_up)
        {
            Goal+=1;
            extendo.setGoal(Goal);
        }
        if(gamepad.dpad_down)
        {
            Goal-=1;
            extendo.setGoal(Goal);
        }

        if(gamepad.a)
            Currstate = IntakeState.STOPPED;

        switch(Currstate){
            case STOPPED:
                rollingMotor.stop();
                break;
            case ROTATETOTAKE:
                rollingMotor.rollForward();
                break;
            case ROTATETOEJECT:
                rollingMotor.rollBackward();
                break;
        }
        prev.copy(gamepad);
    }

}
