package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.IntoTheDeep.AsymMotionProfile;

public class ServoWithMotionProf {

    public AsymMotionProfile motionProfile;
    public ServoWraper servo;
    HardwareMap hwmap;

    public double CurrPos = 0;

    public ServoWithMotionProf(HardwareMap hardwareMap,String name,double maxvel,double accel,double decel){
        servo = new ServoWraper(hardwareMap,name);
        motionProfile = new AsymMotionProfile(maxvel,accel,decel);
        this.CurrPos = 0;
        this.hwmap = hardwareMap;
    }

    public void setCoefs(double maxvel,double accel,double decel){
        motionProfile.SetCoeficients(maxvel,accel,decel);
    }

    public void Change_servo(String name){
        servo = new ServoWraper(hwmap,name);
    }//debug

    public void setProfilePosition(double targetPos){
        motionProfile.startMotion(this.CurrPos,targetPos);
        motionProfile.setInstant(targetPos);
    }

    public void updProfile(){
        motionProfile.update();
        servo.setAngle(motionProfile.getPosition());
        CurrPos = motionProfile.getPosition();
    }
}
