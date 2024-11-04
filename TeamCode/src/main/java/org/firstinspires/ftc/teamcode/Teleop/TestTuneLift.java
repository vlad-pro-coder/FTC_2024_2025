package org.firstinspires.ftc.teamcode.Teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Teleop.Outtakecombo.Outtake;

@Config
@TeleOp(name = "lift")
public class TestTuneLift extends LinearOpMode {

    Lift lift;

    Outtake outtake;
    public static double setTarget = 0;
    Gamepad prevgmp = new Gamepad();
    private boolean isOutActive = false;
    private boolean onceOutMake = false;
    private double frontbackservosPos = 0;
    private double updoservosPos = 0;
    private double rotateservosPos = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        lift = new Lift(hardwareMap);
        outtake = new Outtake(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        waitForStart();

        while(opModeIsActive())
        {
            ///lift
            if( gamepad1.dpad_down){
                setTarget = Math.max(0,setTarget-200);
            }
            if( gamepad1.dpad_up){
                setTarget = Math.min(1360,setTarget+200);
            }
            ///

            ///intake
            ///

            ///outtake

            if((gamepad1.square!=prevgmp.square&&gamepad1.triangle!=prevgmp.triangle)&&(gamepad1.square&&gamepad1.triangle)){
                isOutActive = !isOutActive;
                onceOutMake = false;
            }
            if(isOutActive){
                ///servo uri fata spate
                if(prevgmp.triangle!=gamepad1.triangle && gamepad1.triangle) {
                    frontbackservosPos += 0.10;
                    outtake.movefrontback(frontbackservosPos);
                }
                if(prevgmp.x!=gamepad1.x && gamepad1.x) {
                    frontbackservosPos -= 0.10;
                    outtake.movefrontback(frontbackservosPos);
                }


                //servo uri sus jos
                if(prevgmp.square!=gamepad1.square && gamepad1.square) {
                    updoservosPos += 0.10;
                    outtake.movefrontback(updoservosPos);
                }
                if(prevgmp.circle!=gamepad1.circle && gamepad1.circle) {
                    updoservosPos -= 0.10;
                    outtake.movefrontback(updoservosPos);
                }

                ///servo uri rotare
                if(prevgmp.dpad_left!=gamepad1.dpad_left && gamepad1.dpad_left) {
                    rotateservosPos += 0.10;
                    outtake.movefrontback(rotateservosPos);
                }
                if(prevgmp.dpad_right!=gamepad1.dpad_right && gamepad1.dpad_right) {
                    rotateservosPos -= 0.10;
                    outtake.movefrontback(rotateservosPos);
                }


            }

            ///


            telemetry.addData("gamepad1.dpad_up",gamepad1.dpad_up);
            telemetry.addData("gamepad1.dpad_down",gamepad1.dpad_down);
            telemetry.addData("targetPos",setTarget);
            telemetry.addData("CurrPos",lift.currPos());
            telemetry.update();

            lift.setTargetPos(setTarget);
            lift.upd();
            if(isOutActive){
                if(!onceOutMake) {
                    outtake.movefrontback(1);
                    frontbackservosPos = 1;
                    rotateservosPos = 0.5;
                    updoservosPos = 0.5;
                    outtake.updoservo(0.5);
                    outtake.rotateservo(0.5);
                }
                onceOutMake = true;
                outtake.updAngler();
            }
            else {
                if(!onceOutMake) {
                    outtake.forcePosAngler(0);
                    outtake.movefrontback(0);
                    outtake.updoservo(0);
                    outtake.rotateservo(0);
                    frontbackservosPos = 0;
                    rotateservosPos = 0;
                    updoservosPos = 0;
                }
                onceOutMake = true;
            }

            prevgmp.copy(gamepad1);
        }
    }
}
