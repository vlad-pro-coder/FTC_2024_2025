package org.firstinspires.ftc.teamcode.IntoTheDeep.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.TaskEnums;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.FullLift;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.PTOsystem;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Wheelie;


@TeleOp(name = "WheelieTest")
@Config
public class Whelie extends LinearOpMode {

    public static boolean pos = false,prevpos = false;
    Wheelie wheelie;
    FullLift fullLift;

    @Override
    public void runOpMode() throws InterruptedException {
        wheelie = new Wheelie(hardwareMap);
        fullLift = new FullLift(hardwareMap);
        wheelie.CloseWheelie();
        while (opModeInInit()) {

        }
        waitForStart();

        while (opModeIsActive()) {
            if(pos != prevpos){
                if(!pos)
                wheelie.CloseWheelie();
                else
                    wheelie.OpenWheelie();
                prevpos = pos;
            }

            wheelie.runWheelieContinuos();

            telemetry.addData("lift_pos",fullLift.lift.getPosition());

            telemetry.update();
        }

    }
}
