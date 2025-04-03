package org.firstinspires.ftc.teamcode.IntoTheDeep.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWithMotionProf;

@TeleOp(name = "calibrate_servo")
@Config
public class servotest extends LinearOpMode {

    public static double maxvel = 3000,accel = 4500,decel = 2000;

    public static String name="extendos1";
    public static String prevname="extendos1";

    public static double anglePosition = 0;
    public static double prevangle = 0;


    @Override
    public void runOpMode() throws InterruptedException {
        ServoWithMotionProf servo1 = new ServoWithMotionProf(hardwareMap,name,maxvel,accel,decel);
        while (opModeInInit()) {

        }
        waitForStart();

        while(opModeIsActive()) {

            if(!prevname.equals(name)){
                servo1.Change_servo(name);
                prevname = name;
            }

            if (anglePosition != prevangle) {
                servo1.setProfilePosition(anglePosition);
                prevangle = anglePosition;
            }

            servo1.setCoefs(maxvel,accel,decel);

            telemetry.addData("procentage over1",servo1.motionProfile.getPrecentOfMotion());
            telemetry.addData("over1target",servo1.motionProfile.getPosition());
            telemetry.addData("over1targetencoder",servo1.servo.getAngle());


            telemetry.update();

            servo1.updProfile();
        }
    }
}
