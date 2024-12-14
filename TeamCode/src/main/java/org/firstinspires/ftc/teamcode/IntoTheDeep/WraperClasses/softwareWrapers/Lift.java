package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.motorWraper;

public class Lift {

    public static double goalPos=0;
    motorWraper fastMotor;
    PTOInterchangeWraper PTOWraper;
    public static PIDCoefficients pidcoef = new PIDCoefficients(0.01,0,0);
    public Lift(HardwareMap hardwareMap){
        fastMotor = new motorWraper(hardwareMap,"test", motorWraper.DIRECTION.FORWARD,pidcoef);
    }

    public void setGoal(double pos){
        fastMotor.setGoalPos(goalPos);
    }

    public void updPos(){
        fastMotor.updGoalPos();
    }

    public double getPos(){
        return fastMotor.getPos();
    }

    public void changecoefs(PIDCoefficients coefs){
        fastMotor.changeCoefs(coefs);
    }
}
