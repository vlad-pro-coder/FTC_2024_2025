package org.firstinspires.ftc.teamcode.mapuscosmin;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp(name = "servocosmin")
public class cevacuservo extends LinearOpMode {

    ///primul fst
    ///al doilea fdr
    ///treilea spate dreapta
    ///patrulea spate stanga

    DcMotor motor;

    @Override
    public void runOpMode() throws InterruptedException {

        motor = hardwareMap.get(DcMotorEx.class,"test");
        waitForStart();

        while(opModeIsActive())
        {
            motor.setPower(0.3);
        }
    }
}
