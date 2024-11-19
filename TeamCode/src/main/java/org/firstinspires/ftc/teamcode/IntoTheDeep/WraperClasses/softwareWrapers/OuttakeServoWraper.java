package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWraper;

enum OuttakeState{
    MOVING,
    REACHED,
}

public class OuttakeServoWraper {

    ServoWraper s1,s2,picker;
    OuttakeState currentState;

    public OuttakeServoWraper(HardwareMap hardwareMap){
        s1 = new ServoWraper(hardwareMap,"s1");
        s2 = new ServoWraper(hardwareMap,"s2");
        picker = new ServoWraper(hardwareMap,"s3");
    }

        /*private double interval(double nr){
        return Math.min(maxAngle,Math.max(nr,0));
    }

    public void updOuttakemovmentTest(){
        s1Angle = interval(s1Angle + dpadup - dpaddown + dpadleft - dpadright);
        s2Angle = interval(s2Angle + dpadup - dpaddown - dpadleft + dpadright);

        telemetrie.addData("gamepad1.dpad_up",s1Angle);
        telemetrie.addData("gamepad1.dpad_down",s2Angle);
        telemetrie.update();

        outtakeServoWraper.setGoal(s1Angle,s2Angle,0);
    }

    public void OuttakeControlsTest(Gamepad gamepad){
        dpadup =gamepad.dpad_up?0.3:0;
        dpaddown=gamepad.dpad_down?0.3:0;
        dpadleft = gamepad.dpad_left?0.3:0;
        dpadright = gamepad.dpad_right?0.3:0;

        updOuttakemovmentTest();
    }*/

    public void setGoal(double RightServo,double LeftServo,double Picker){
        s1.setAngle(RightServo);
        s2.setAngle(LeftServo);
        picker.setAngle(Picker);
        /*if(RightServo != s1.getPos()&&LeftServo !=s2.getPos() && Picker != picker.getPos())
            currentState = OuttakeState.MOVING;
        else
            currentState = OuttakeState.REACHED;
        
        return currentState;*/
    }

}
