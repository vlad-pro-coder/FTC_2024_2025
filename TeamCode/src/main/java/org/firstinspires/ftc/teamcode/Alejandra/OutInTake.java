package org.firstinspires.ftc.teamcode.Alejandra;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Alejandra.hardwareWrapers.ServoWraper;

public class OutInTake {

    private double maxSpeed=2,maxAccel=10;
    private ServoWraper s1,s2,picker;
    OutInTake(HardwareMap hardwareMap){
        s1 = new ServoWraper(hardwareMap,"",maxSpeed,maxAccel);
        s2 = new ServoWraper(hardwareMap,"",maxSpeed,maxAccel);
        picker = new ServoWraper(hardwareMap,"",maxSpeed,maxAccel);
    }

    public void setPos(double s1Pos,double s2Pos,double pickerPos){
        s1.setProfilePosition(s1Pos);
        s2.setProfilePosition(s2Pos);
        picker.setProfilePosition(pickerPos);
    }

    public void updServos(){
        s1.updProfile();
        s2.updProfile();
        picker.updProfile();
    }

    public PositionState getPos() {
        if(s1.getPos() == PositionState.REACHED&&s2.getPos() == PositionState.REACHED&&picker.getPos() == PositionState.REACHED)
            return PositionState.REACHED;
        return PositionState.MOVING;
    }
}
