package org.firstinspires.ftc.teamcode.IntoTheDeep.test;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.Commands;
import org.firstinspires.ftc.teamcode.IntoTheDeep.drivetrain;

@TeleOp(name = "test_motor")
@Config
public class MotorTest extends LinearOpMode {


    DcMotorEx motor;
    public static String name = "mlift1";
    public static double power = 0;
    @Override
    public void runOpMode() throws InterruptedException {

        while (opModeInInit()) {

        }
        waitForStart();

        while (opModeIsActive()) {
            motor = hardwareMap.get(DcMotorEx.class,name);
            motor.setPower(gamepad1.left_stick_y);
        }
    }
}
