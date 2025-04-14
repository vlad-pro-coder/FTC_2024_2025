package org.firstinspires.ftc.teamcode.IntoTheDeep.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses.Devices.PinPoint;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses.Devices.RobotLocalizer;

@TeleOp()
public class Localizer_test extends LinearOpMode {

    RobotLocalizer pinpoint;
    @Override
    public void runOpMode() throws InterruptedException {
        pinpoint = new RobotLocalizer(hardwareMap);

        waitForStart();
        while(opModeIsActive()){

            Pose2D pos = pinpoint.pos;

            pinpoint.update();

            telemetry.addData("x",pos.getX(DistanceUnit.MM));
            telemetry.addData("y",pos.getY(DistanceUnit.MM));
            telemetry.addData("h",pos.getHeading(AngleUnit.DEGREES));

            telemetry.addData("distance from 0,0",pinpoint.DistFromPoint(new Pose2D(DistanceUnit.MM,0,0,AngleUnit.DEGREES,0)));

            telemetry.update();

        }
    }
}
