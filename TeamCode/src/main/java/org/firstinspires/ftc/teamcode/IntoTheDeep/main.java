package org.firstinspires.ftc.teamcode.IntoTheDeep;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.IntoTheDeep.motorWraper.DIRECTION;
import org.firstinspires.ftc.teamcode.IntoTheDeep.HelperClasses.DirectName;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Vector;

public class main extends LinearOpMode {

    Vector<DirectName> LiftMotorData = new Vector<>(Arrays.asList(
            new DirectName("1", DIRECTION.FORWARD),
            new DirectName("2", DIRECTION.FORWARD)
    ));

    Vector<DirectName> ExtendoMotorData = new Vector<>(Arrays.asList(
            new DirectName("11", DIRECTION.FORWARD),
            new DirectName("22", DIRECTION.FORWARD)
    ));

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain wheels = new drivetrain(hardwareMap);
        PIDdependent Lift = new PIDdependent(hardwareMap,LiftMotorData);
        PIDdependent Extendo = new PIDdependent(hardwareMap,ExtendoMotorData);
        Outtake outtake = new Outtake(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        boolean Switch = false;//0-pentru extendo, 1-pentru lift
        Gamepad prevgmp = new Gamepad();
        double extendboth = 0;

        while(opModeInInit()){
            wheels.idleMotors();
        }

        waitForStart();

        while(opModeIsActive()) {
            wheels.setWheelsPower();
            outtake.updOuttakemovment();
            intake.updIntake();

            if(gamepad1.circle!=prevgmp.circle&&gamepad1.circle) {
                Switch = !Switch;
                extendboth=0;
                Lift.Goal = extendboth;
                Extendo.Goal = extendboth;
            }

            if(gamepad1.triangle!=prevgmp.triangle&&gamepad1.triangle) {
                extendboth = Math.min(700,extendboth+100);
                if(Switch == false)
                    Extendo.Goal =  extendboth;
                else
                    Lift.Goal =  extendboth;
                }
            }

            if(gamepad1.x!=prevgmp.x&&gamepad1.x){
                extendboth = Math.max(0,extendboth-100);
                if(Switch == false)
                    Extendo.Goal =  extendboth;
                else
                    Lift.Goal =  extendboth;
            }


            if(gamepad1.square)
                 outtake.setPicker(60);
            else
                outtake.setPicker(0);

            Lift.upd();
            Extendo.upd();

            prevgmp.copy(gamepad1);
    }
}

