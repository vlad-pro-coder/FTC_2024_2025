package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.MovingState;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWraper;

public class WheelieServosWraper {

    ServoWraper s1,s2;
    private double target = 0;

    public WheelieServosWraper(HardwareMap hardwareMap){
        //s1 = new ServoWraper(hardwareMap,"");
        //s2 = new ServoWraper(hardwareMap,"");
    }

    public void setPos(double angle){
        s1.setProfilePosition(angle);
        s2.setProfilePosition(angle);
        target = angle;
    }

    public MovingState getState(){
        if(s1.getPosAngle() == target&&s2.getPosAngle() == target)
            return MovingState.REACHED;
        return MovingState.MOVING;
    }

    public void updServos(){
        s1.updProfile();
        s2.updProfile();
    }

}
