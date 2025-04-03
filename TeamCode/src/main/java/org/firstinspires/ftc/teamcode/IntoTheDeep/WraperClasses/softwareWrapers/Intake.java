package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;


import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses.ColorSensor;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses.Colors;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.Task;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.TaskEnums;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWithMotionProf;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWraper;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.motorWraper;

public class Intake {

    public enum TEAM{
        BLUE,
        RED,
    }

    private DcMotorEx m1;
    private ServoWraper Intakeputa,dropdownservo;
    public ColorSensor colorsensor;
    private TEAM team =  TEAM.BLUE;
    Task currTask;

    //private boolean commandactive = false, setbyprogram = false, forceeject = false;
    //private double direction = 1;

    private Gamepad prevgm = new Gamepad();

    public Intake(HardwareMap hardwareMap,boolean isRedTeam){
        m1 = hardwareMap.get(DcMotorEx.class,"mintake");
        dropdownservo = new ServoWraper(hardwareMap,"dropdown");
        Intakeputa = new ServoWraper(hardwareMap,"puta");
        colorsensor = new ColorSensor(hardwareMap,"colorsensor");
        if(isRedTeam)
            team = TEAM.RED;
    }

    public void runIntakeContinuos(Gamepad gm2) {

        if(colorsensor.getColorSeenBySensor() != Colors.ColorType.NONE && !gm2.left_bumper)
            Intakeputa.setAngle(150);//clampangle
        else
            Intakeputa.setAngle(254);

        if(gm2.left_bumper)
            m1.setPower(-0.8);

        if(gm2.right_bumper){
            dropdownservo.setAngle(190);//downangle
            m1.setPower(0.8);
        }
        else
            dropdownservo.setAngle(290);

        if(!gm2.right_bumper && !gm2.left_bumper)
            m1.setPower(0);

        /*if(commandactive | forceeject)
            m1.setPower(direction);
        else
            m1.setPower(0);

        Colors.ColorType detectedColor = sensor1.getColorSeenBySensor();
        if ((team == TEAM.RED && detectedColor == Colors.ColorType.BLUE) ||
                (team == TEAM.BLUE && detectedColor == Colors.ColorType.RED)) {
            forceeject = true;
            direction = -1;
            setbyprogram = true;
            clawservo.setAngle(120);//open position
        }
        else
        {
            if(detectedColor == Colors.ColorType.NONE)
                clawservo.setAngle(120);//open position
            else {
                //there is a corect sample
                clawservo.setPosition(0);
            }
            direction = 1;
            setbyprogram = false;
            forceeject = false;
        }*/



        /*if(IntakeQueue.isEmpty())
            return;
        if(currTask.procentageDone(dropdownservo.CurrPos) < 95)
            return;
        if(currTask.procentageDone(dropdownservo.CurrPos) > 95) {
            currTask = IntakeQueue.poll();
            dropdownservo.setProfilePosition(currTask.component1target);
        }*/

        prevgm.copy(gm2);
    }

}
