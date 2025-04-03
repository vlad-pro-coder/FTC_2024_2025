package org.firstinspires.ftc.teamcode.IntoTheDeep.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWithMotionProf;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWraper;

@TeleOp(name = "probe_servo")
@Config
public class servotestonrobot extends LinearOpMode {

    ServoWraper s;
    public static String name = "puta";
    public static double pos = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        while (opModeInInit()) {

        }
        waitForStart();
        while(opModeIsActive()) {
            s = new ServoWraper(hardwareMap,name);
            s.setAngle(pos);
        }
    }
}
