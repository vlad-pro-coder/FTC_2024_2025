package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoWraper {

    private final double maxAngle = 355;
    private Servo servo;
    public ServoWraper(HardwareMap hardwareMap, String name){
        servo = hardwareMap.get(Servo.class,name);
        servo.setPosition(0);
    }

    public void setAngle(double angle){
        servo.setPosition(angle/maxAngle);
    }
    public void setNormally(double value){
        servo.setPosition(value);
    }
    public double getPos(){
        return servo.getPosition();
    }

}
