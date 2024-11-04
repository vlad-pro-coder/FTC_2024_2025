package org.firstinspires.ftc.teamcode.Teleop.Outtakecombo;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class LinearMotionServos {
    Servo s;
    public LinearMotionServos(HardwareMap hardwareMap, String name){
        s = hardwareMap.get(Servo.class,name);
    }

    public void setPos(double num){
        s.setPosition(num);
    }
    public double getPos(){
        return s.getPosition();
    }
}
