package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.moduleWrapers;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.MovingState;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWraper;

public class Outtake {

    ServoWraper s1,s2,picker;;
    public static double GoalangleArm = 0, GoalanglePivot = 0, GoalAnglePicker = 0;


    public Outtake(HardwareMap hardwareMap){
        //s1 = new ServoWraper(hardwareMap,"s1");
        //s2 = new ServoWraper(hardwareMap,"s2");
        //picker = new ServoWraper(hardwareMap,"s3");
    }

    /*public void updPos(){
        double angleCalcs1 = GoalangleArm + GoalanglePivot;
        double angleCalcs2 = GoalangleArm - GoalanglePivot;

        s1.setAngle(angleCalcs1);
        s2.setAngle(angleCalcs2);
    }*/

    private double s1angle(double angleArm,double anglePivot){return angleArm + anglePivot;}
    private double s2angle(double angleArm,double anglePivot){return angleArm - anglePivot;}
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

    public MovingState getState(){
        return (s1angle(GoalangleArm,GoalanglePivot) == s1.getPosAngle()&&
                s2angle(GoalangleArm,GoalanglePivot) == s2.getPosAngle()&&
                GoalAnglePicker == picker.getPosAngle()?MovingState.REACHED:MovingState.MOVING);
    }

    public void setGoal(double angleArm,double anglePivot,double Picker){
        GoalAnglePicker = Picker;
        GoalangleArm = angleArm;
        GoalanglePivot = anglePivot;
        s1.setProfilePosition(s1angle(angleArm,anglePivot));
        s2.setProfilePosition(s2angle(angleArm,anglePivot));
        picker.setProfilePosition(Picker);
        /*if(RightServo != s1.getPos()&&LeftServo !=s2.getPos() && Picker != picker.getPos())
            currentState = OuttakeState.MOVING;
        else
            currentState = OuttakeState.REACHED;

        return currentState;*/
    }

    public void updServos(){
        s1.updProfile();
        s2.updProfile();
        picker.updProfile();
    }

}
