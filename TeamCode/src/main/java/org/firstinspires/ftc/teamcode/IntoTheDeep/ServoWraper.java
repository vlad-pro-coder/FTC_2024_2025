package org.firstinspires.ftc.teamcode.IntoTheDeep;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoWraper {

    double maxAngle = 355;
    Servo servo;
    public ServoWraper(HardwareMap hardwareMap, String name){
        servo = hardwareMap.get(Servo.class,name);
    }

    public void setAngle(double angle){
        servo.setPosition(angle/maxAngle);
    }
    public void setNormally(double value){
        servo.setPosition(value);
    }

}
