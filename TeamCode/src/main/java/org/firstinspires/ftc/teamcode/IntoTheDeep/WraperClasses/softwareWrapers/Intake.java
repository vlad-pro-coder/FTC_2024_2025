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

    private motorWraper m1;
    private ServoWraper Intakeputa,dropdownservo;
    private ColorSensor colorsensor;
    private TEAM team =  TEAM.BLUE;
    Task currTask;

    //private boolean commandactive = false, setbyprogram = false, forceeject = false;
    //private double direction = 1;

    private Gamepad prevgm = new Gamepad();

    public Intake(HardwareMap hardwareMap,boolean isRedTeam){
        m1 = new motorWraper(hardwareMap,"mintake");
        dropdownservo = new ServoWraper(hardwareMap,"dropdown");
        Intakeputa = new ServoWraper(hardwareMap,"puta");
        colorsensor = new ColorSensor(hardwareMap,"colorsensor");
        if(isRedTeam)
            team = TEAM.RED;
    }

    public void runIntakeContinuos(Gamepad gm2) {

        Colors.ColorType colorseen = colorsensor.getColorSeenBySensor();
        if(colorsensor.isOperational()) {
            if ( colorseen != Colors.ColorType.NONE && !gm2.left_bumper)
                Intakeputa.setAngle(20);//clampangle
            else
                Intakeputa.setAngle(150);
        }
        else {
            if(gm2.right_bumper || gm2.left_bumper){
                Intakeputa.setAngle(150);
            }
            else {
                Intakeputa.setAngle(20);
            }
        }

        if (gm2.left_bumper)
            m1.setPower(-0.8);

        if (gm2.right_bumper) {
            dropdownservo.setAngle(190);//downangle
            m1.setPower(0.8);
        } else
            dropdownservo.setAngle(290);

        if (!gm2.right_bumper && !gm2.left_bumper)
            m1.setPower(0);

        prevgm.copy(gm2);
    }

}
