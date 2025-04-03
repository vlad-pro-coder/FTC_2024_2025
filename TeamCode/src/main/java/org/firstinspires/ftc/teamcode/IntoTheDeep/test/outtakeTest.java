package org.firstinspires.ftc.teamcode.IntoTheDeep.test;


import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.PutOuttakeTask;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.Task;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.TaskEnums;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Outtake;

@TeleOp(name = "outtake_pos_test")
@Config
public class outtakeTest extends LinearOpMode {

    Outtake outtake;
    public static String entercomm="",prevcomm="";
    public static double circular = 0,extendo = 0,claw = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        outtake = new Outtake(hardwareMap);
        outtake.defaultPosition();
        while (opModeInInit()) {
            outtake.runOuttakeContinuos();

            telemetry.addData("circular servo pos",outtake.overheads1.CurrPos);
            telemetry.addData("extendo servo pos",outtake.extendos1.CurrPos);
            telemetry.addData("claw servo pos",outtake.claws1.CurrPos);
            telemetry.addData("worst error",outtake.CurrTaskDoneness());

            telemetry.update();
        }
        waitForStart();

        while (opModeIsActive()) {

            if(!prevcomm.equals(entercomm)){
                PutOuttakeTask(circular,extendo,claw,10, TaskEnums.DISENGAGEPTO);
                prevcomm =  entercomm;
            }

            telemetry.addData("circular servo pos",outtake.overheads1.CurrPos);
            telemetry.addData("extendo servo pos",outtake.extendos1.CurrPos);
            telemetry.addData("claw servo pos",outtake.claws1.CurrPos);

            telemetry.addData("error circular",Math.abs(outtake.overheads1.CurrPos - outtake.currTask.component1target));
            telemetry.addData("error extendo",Math.abs(outtake.extendos1.CurrPos - outtake.currTask.component2target));
            telemetry.addData("error claw",Math.abs(outtake.claws1.CurrPos - outtake.currTask.component3target));

            telemetry.addData("worst error",outtake.CurrTaskDoneness());

            telemetry.update();

            outtake.runOuttakeContinuos();

        }
    }
}
