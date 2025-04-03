package org.firstinspires.ftc.teamcode.IntoTheDeep.test;

import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.PutOuttakeTask;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.TaskEnums;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Extendo;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.FullLift;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Outtake;

@TeleOp(name = "lift_outtake_positions")
@Config
public class intakeliftTasksTest extends LinearOpMode {

    public static double pos = 0,prevpos = 0;
    public static String entercomm="",prevcomm="";
    public static double circular = 0,extendo = 0,claw = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        Outtake outtake = new Outtake(hardwareMap);
        FullLift fullLift = new FullLift(hardwareMap);
        while (opModeInInit()) {
            outtake.defaultPosition();
            outtake.runOuttakeContinuos();
        }
        waitForStart();

        while (opModeIsActive()) {

            outtake.runOuttakeContinuos();
            fullLift.runFullLiftContinuos();

            if(!prevcomm.equals(entercomm)){
                PutOuttakeTask(circular,extendo,claw,10, TaskEnums.DISENGAGEPTO);
                prevcomm =  entercomm;
            }

            if(pos != prevpos){
                GlobalQueues.PutLiftTask(pos, 30, TaskEnums.SPECIMEN_WALL_HEIGHT);
                prevpos = pos;
            }

            telemetry.update();
        }


    }
}
