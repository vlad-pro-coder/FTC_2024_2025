package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.MovingState;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWraper;

public class WheelieServosWraper {

    ServoWraper s1,s2;
    private double target = 0;

    public WheelieServosWraper(HardwareMap hardwareMap){
        s1 = new ServoWraper(hardwareMap,"");
        s2 = new ServoWraper(hardwareMap,"");
    }

    public void setPos(double angle){
        s1.setAngle(angle);
        s2.setAngle(angle);
        target = angle;

    }

    public MovingState getState(){
        if(s1.getPos() == target&&s2.getPos() == target)
            return MovingState.REACHED;
        return MovingState.MOVING;
    }

}
