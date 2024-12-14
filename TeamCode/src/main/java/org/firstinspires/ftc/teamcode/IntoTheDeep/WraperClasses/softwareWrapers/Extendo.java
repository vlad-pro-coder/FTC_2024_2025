package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.motorWraper;

public class Extendo {

    motorWraper m1;
    public static double goal = 0;
    public static final double Multiplier = 0.3;
    Gamepad prev = new Gamepad();

    public Extendo(HardwareMap hardwareMap){
        //m1 = new motorWraper(hardwareMap,"", motorWraper.DIRECTION.FORWARD,new PIDCoefficients(0,0,0));
    }

    public void setGoal(double goal) {
        m1.setGoalPos(goal);
    }
    public void updPos(){
        m1.updGoalPos();
    }

    public double getPos(){
        return m1.getPos();
    }


}
