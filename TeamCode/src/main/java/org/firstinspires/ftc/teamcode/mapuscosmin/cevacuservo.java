package org.firstinspires.ftc.teamcode.mapuscosmin;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "servocosmin")
public class cevacuservo extends LinearOpMode {

    CRServo s1,s2;

    @Override
    public void runOpMode() throws InterruptedException {
        s1 = hardwareMap.get(CRServo.class,"s1");
        s2 = hardwareMap.get(CRServo.class,"s2");

        waitForStart();

        while(opModeIsActive())
        {
            s1.setPower(-1);
            s2.setPower(1);
        }
    }
}
