package org.firstinspires.ftc.teamcode.IntoTheDeep.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses.Colors;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Intake;

@TeleOp(name = "intake_test")
@Config
public class IntakeTest extends LinearOpMode {

    Intake intake;
    @Override
    public void runOpMode() throws InterruptedException {
        intake = new Intake(hardwareMap,true);
        while (opModeInInit()) {

        }
        waitForStart();

        while (opModeIsActive()) {


            telemetry.update();

            intake.runIntakeContinuos(gamepad1);
        }
    }
}
