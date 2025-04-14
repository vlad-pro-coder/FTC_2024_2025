package org.firstinspires.ftc.teamcode.IntoTheDeep.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWithMotionProf;

@TeleOp(name = "calibrate_servo")
@Config
public class servotest extends LinearOpMode {

    public static double maxvel = 1000,accel = 1000,decel = 1000;

    public static String name1="wheelie1";
    public static String name2="wheelie2";
    public static String prevname="wheelie1";

    public static double anglePosition = 0;
    public static double prevangle = 0;

    ///wheelie1 closed = 147, wheelie2 closed = 211
    ///wheelie1 open = 250, wheelie2 open = 108


    @Override
    public void runOpMode() throws InterruptedException {
        ServoWithMotionProf servo1 = new ServoWithMotionProf(hardwareMap,name1,maxvel,accel,decel);
        while (opModeInInit()) {

        }
        waitForStart();

        while(opModeIsActive()) {

            if(!prevname.equals(name1)){
                servo1.Change_servo(name1);
                prevname = name1;
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
