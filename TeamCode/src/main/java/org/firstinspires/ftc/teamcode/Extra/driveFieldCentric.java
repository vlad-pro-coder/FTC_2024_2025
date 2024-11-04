package org.firstinspires.ftc.teamcode.Extra;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class driveFieldCentric extends LinearOpMode {

    DcMotor mfl,mfr,mbl,mbr;
    double idlePower = 0;

    @Override
    public void runOpMode()
    {
        mfl = hardwareMap.get(DcMotor.class,"dc1");
        mfr = hardwareMap.get(DcMotor.class,"dc2");
        mbl = hardwareMap.get(DcMotor.class,"dc3");
        mbr = hardwareMap.get(DcMotor.class,"dc4");

        ///unghi al robotului in functie de orientarea control hubului
        IMU imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);


        mfl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mfr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mbl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mbr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while(opModeInInit())
        {
            mfl.setPower(idlePower);
            mfr.setPower(idlePower);
            mbl.setPower(idlePower);
            mbr.setPower(idlePower);
        }

        waitForStart();

        while(opModeIsActive())
        {
            double x = gamepad1.left_stick_x,y = -gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;
            double ClawState = gamepad1.right_trigger;
            double angleRob = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            double rotX = x * Math.cos(-angleRob) - y * Math.sin(-angleRob);
            double rotY = x * Math.sin(-angleRob) + y * Math.cos(-angleRob);

            double difference = Math.min(Math.abs(x)+Math.abs(y)+Math.abs(rx),1);

            mfl.setPower((rotY+rotX+rx)/difference);
            mbl.setPower((rotY-rotX+rx)/difference);
            mfr.setPower((rotY-rotX-rx)/difference);
            mbr.setPower((rotY+rotX-rx)/difference);
        }
    }


}
