package org.firstinspires.ftc.teamcode.Alejandra;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import com.qualcomm.robotcore.hardware.DcMotorImplEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "alejandra_vladut")
public class main extends LinearOpMode {

    //Lift lift = new Lift(hardwareMap);

    //OutInTake outin = new OutInTake(hardwareMap);

    @Override
    public void runOpMode() throws InterruptedException {


        chasis sasiu = new chasis(hardwareMap);
        //OutInTake outInTake = new OutInTake(hardwareMap);
        //linkage Linkage = new linkage(hardwareMap);
        Gamepad prevpad = new Gamepad();
        double servoPos = 0.46;
        Servo claw = hardwareMap.get(Servo.class,"test");

        while (opModeInInit()) {
            //wheels.idleMotors();
        }
        waitForStart();

        while (opModeIsActive())
        {
            sasiu.setWheelsPower(gamepad1);

            if(!prevpad.cross&&gamepad1.cross)
            {
                claw.setPosition(servoPos);
                servoPos = servoPos == 0.46?0.7:0.46;
            }

            prevpad.copy(gamepad1);

        }
    }
}
