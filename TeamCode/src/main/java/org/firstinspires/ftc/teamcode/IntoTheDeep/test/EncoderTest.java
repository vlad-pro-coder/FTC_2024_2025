package org.firstinspires.ftc.teamcode.IntoTheDeep.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorController;

@TeleOp
public class EncoderTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotorController eh = hardwareMap.get(DcMotorController.class, "Control Hub"), mh = hardwareMap.get(DcMotorController.class, "Expansion Hub 2");
        waitForStart();
        while(opModeIsActive()){
            for(int i = 0; i < 4; i++){
                telemetry.addData("EHub" + i, eh.getMotorCurrentPosition(i));
                telemetry.addData("CHub" + i, mh.getMotorCurrentPosition(i));
            }
            telemetry.update();
        }
    }
}
