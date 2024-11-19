package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWraper;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.motorWraper;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.motorWraper.DIRECTION;

enum IntakeState {
    STOPPED,
    ROTATETOTAKE,
    ROTATETOEJECT
}
public class Intake {

    private IntakeState Currstate;
    private motorWraper motor;
    private Extendo extendo;
    Gamepad prev = new Gamepad();
    ServoWraper down1,down2;
    private double Goal=0;

    public Intake(HardwareMap hardwareMap){
        motor = new motorWraper(hardwareMap,"ceva", DIRECTION.FORWARD,new PIDCoefficients(0,0,0));
        extendo = new Extendo(hardwareMap);
        down1 = new ServoWraper(hardwareMap,"");
        down2 = new ServoWraper(hardwareMap,"");
        Currstate = IntakeState.STOPPED;
    }

    public double getPos(){
        return extendo.getPos();
    }

    public void setGoalExtendoMotor(double pos){
        extendo.setGoal(pos);
    }

    public void GoalUpd(){
        extendo.updPosition();
    }

    public void updIntake(Gamepad gamepad){

        if(gamepad.x!=prev.x&&gamepad.x){
            if(Currstate == IntakeState.ROTATETOEJECT)
                Currstate = IntakeState.ROTATETOTAKE;
            else
                Currstate = IntakeState.ROTATETOEJECT;
        }

        if(gamepad.a)
            Currstate = IntakeState.STOPPED;

        switch(Currstate){
            case STOPPED:
                motor.setPower(0);
                down1.setAngle(0);
                down2.setAngle(0);
                break;
            case ROTATETOTAKE:
                motor.setPower(1);
                down1.setAngle(100);
                down2.setAngle(100);
                break;
            case ROTATETOEJECT:
                motor.setPower(-1);
                down1.setAngle(100);
                down2.setAngle(100);
                break;
        }


        prev.copy(gamepad);
    }

}
