package org.firstinspires.ftc.teamcode.IntoTheDeep;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Extendo;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Intake;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Lift;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.OuttakeServoWraper;


@TeleOp(name = "new_robot")
public class main extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain wheels = new drivetrain(hardwareMap);
        Lift lift = new Lift(hardwareMap);

        while (opModeInInit()) {
            wheels.idleMotors();
        }

        waitForStart();

        while (opModeIsActive()) {
            wheels.setWheelsPower();
            lift.updPos();
        }
    }
}

