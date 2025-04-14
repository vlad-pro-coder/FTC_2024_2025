package org.firstinspires.ftc.teamcode.IntoTheDeep.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

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
        ServoController sc = hardwareMap.get(ServoController.class, "Servo Hub 1");
        while (opModeInInit()) {

        }
        waitForStart();
        while(opModeIsActive()) {
            //s = new ServoWraper(hardwareMap,name);
            sc.setServoPosition(1, pos/355.f);
            //s.setAngle(pos);
        }
    }
}
