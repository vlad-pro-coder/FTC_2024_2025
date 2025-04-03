package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.teamcode.IntoTheDeep.AsymMotionProfile;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses.CutOffResolution;


public class ServoWraper{
    private boolean isCR = false;

    private volatile double MaxAngle = 355;
    Servo s;

    public ServoWraper(HardwareMap hardwareMap,String name){
        s = hardwareMap.get(Servo.class,name);
    }

    synchronized public void setMaxAngle(double angle){
        MaxAngle = angle;
    }
    private double thisAngle = 0;
    synchronized public void setAngle(double angle){
        thisAngle = angle;
        s.setPosition(angle / MaxAngle);
    }
    public double getAngle(){
        if(s == null)
            return thisAngle;
        return s.getPosition() * MaxAngle;
    }

    public void setPosition(double val){
        s.setPosition(val);
    }
    // -------------------- CR Implementation --------------------
}