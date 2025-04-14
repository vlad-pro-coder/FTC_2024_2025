package org.firstinspires.ftc.teamcode.IntoTheDeep.test;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses.Devices.IMUWraper;

@TeleOp(name = "IMU_test")
@Config
public class IMUtest extends LinearOpMode {

    IMUWraper imu;
    @Override
    public void runOpMode() throws InterruptedException {
        imu = new IMUWraper(hardwareMap,"imuProst");
        imu.initialize_IMU();
        waitForStart();
        while(opModeIsActive()){

            telemetry.addData("pitch",imu.getPitch());
            telemetry.addData("roll",imu.getRoll());
            telemetry.addData("yaw",imu.getPitch());

            telemetry.update();
        }
    }
    }
