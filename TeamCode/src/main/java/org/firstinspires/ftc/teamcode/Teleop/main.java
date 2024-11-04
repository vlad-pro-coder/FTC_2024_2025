package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "happy_haphy")
public class main extends LinearOpMode {

    @Override
    public void runOpMode() {
        driveRobotCentric Robotdrive = new driveRobotCentric(hardwareMap);
        Lift RobotLift = new Lift(hardwareMap);

        while(opModeInInit()) {
            Robotdrive.setZeroPower();
        }

        waitForStart();

        while(opModeIsActive()){
            Robotdrive.setWheelsPower();

        }
    }
}
