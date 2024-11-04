package org.firstinspires.ftc.teamcode.IntoTheDeep;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Outtake {

    ServoWraper s1,s2,picker;
    double s1Angle=0f,s2Angle=0f;
    int dpadup=0,dpaddown=0,dpadleft=0,dpadright=0;
    public Outtake(HardwareMap hardwareMap){
        s1 = new ServoWraper(hardwareMap,"");
        s2 = new ServoWraper(hardwareMap,"");
        picker = new ServoWraper(hardwareMap,"");
    }

    public void updOuttakemovment(){
        if(gamepad1.dpad_up)
            dpadup=1;
        if(gamepad1.dpad_down)
            dpaddown=1;
        if(gamepad1.dpad_left)
            dpadleft=1;
        if(gamepad1.dpad_right)
            dpadright=1;
        s1Angle += dpadup - dpaddown + dpadleft - dpadright;
        s2Angle += dpadup - dpaddown - dpadleft + dpadright;

        s1.setAngle(s1Angle);
        s2.setAngle(s2Angle);
    }

    public void setPicker(double Angle){
        picker.setAngle(Angle);
    }
}
