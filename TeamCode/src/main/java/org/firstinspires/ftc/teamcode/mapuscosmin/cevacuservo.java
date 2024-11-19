package org.firstinspires.ftc.teamcode.mapuscosmin;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "servocosmin")
public class cevacuservo extends LinearOpMode {

    Servo s1,s2,s3,s4;

    @Override
    public void runOpMode() throws InterruptedException {
        s1 = hardwareMap.get(Servo.class,"s1");
        s2 = hardwareMap.get(Servo.class,"s2");
        s3 = hardwareMap.get(Servo.class,"s3");
        s4 = hardwareMap.get(Servo.class,"s4");

        waitForStart();

        while(opModeIsActive())
        {
            s1.setPosition(1);
            s2.setPosition(0);
            s3.setPosition(0);
            s4.setPosition(0);
        }
    }
}
