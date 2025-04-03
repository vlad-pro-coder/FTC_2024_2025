package org.firstinspires.ftc.teamcode.IntoTheDeep.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.TaskEnums;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Extendo;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.FullLift;

@TeleOp(name = "extendo_tunning")
@Config
public class extendotunning extends LinearOpMode {

    public static double pos = 0,prevpos = 0;

    public static Extendo extendo;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        extendo = new Extendo(hardwareMap);
        while (opModeInInit()) {

        }
        waitForStart();

        while (opModeIsActive()) {

            extendo.runExtendoContinuos(gamepad1);

            if(prevpos != pos){
                GlobalQueues.PutExtendoTask(pos,30,TaskEnums.DISENGAGEPTO);
                prevpos = pos;
            }

            telemetry.addData("lift position",extendo.m1.getCurrentPosition());
            telemetry.addData("power to motors",extendo.powertomotors);
            telemetry.addData("task",extendo.currTask.component1target);
            telemetry.addData("can advance?", extendo.CurrTaskDoneness());
            telemetry.addData("este specimen wall",extendo.currTask.TaskState == TaskEnums.EXTEND_TO_POS);

            telemetry.update();
        }


    }
}
