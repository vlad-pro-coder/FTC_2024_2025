package org.firstinspires.ftc.teamcode.Alejandra;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class linkage {

    Servo s;

    public linkage(HardwareMap hardwareMap){
        s = hardwareMap.get(Servo.class,"");
    }

    public void setPos(double target){
        s.setPosition(target);
    }
}
