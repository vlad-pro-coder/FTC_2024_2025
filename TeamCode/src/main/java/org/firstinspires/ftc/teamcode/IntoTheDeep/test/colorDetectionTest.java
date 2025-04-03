package org.firstinspires.ftc.teamcode.IntoTheDeep.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses.ColorSensor;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses.Colors;

@TeleOp(name = "color_tunning")
@Config
public class colorDetectionTest extends LinearOpMode {

    ColorSensor colorsensor;
    @Override
    public void runOpMode() throws InterruptedException {
        //sensor1 = hardwareMap.get(ColorSensor.class,"colorsensor");
        colorsensor = new ColorSensor(hardwareMap,"colorsensor");
        while (opModeInInit()) {

        }
        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("culori",colorsensor.getRawColors());
            telemetry.addData("distance",colorsensor.getDistance());

            telemetry.addData("red",colorsensor.getColorSeenBySensor() == Colors.ColorType.RED);
            telemetry.addData("yellow",colorsensor.getColorSeenBySensor() == Colors.ColorType.YELLOW);
            telemetry.addData("blue",colorsensor.getColorSeenBySensor() == Colors.ColorType.BLUE);
            telemetry.addData("none",colorsensor.getColorSeenBySensor() == Colors.ColorType.NONE);

            telemetry.update();

        }

    }
}
