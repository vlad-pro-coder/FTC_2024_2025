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

@TeleOp(name = "PTOTest")
@Config
public class PtoTest extends LinearOpMode {

    public static double pos = 0,prevpos = 0;

    public static boolean isengaged = false;
    public static boolean lastengage = false;

    public static FullLift fullLift;

    public static PTOsystem ptosystem;
    public static double power = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        fullLift = new FullLift(hardwareMap);
        //ptosystem = new PTOsystem(hardwareMap);
        GlobalQueues.PutLiftTask(250, 10, TaskEnums.DISENGAGEPTO);
        //ptosystem.PTOActivated();
        while (opModeInInit()) {

        }
        waitForStart();

        while (opModeIsActive()) {
            if(pos != prevpos){
                GlobalQueues.PutLiftTask(pos, 30, TaskEnums.SPECIMEN_WALL_HEIGHT);
                prevpos = pos;
            }

            if(isengaged != lastengage){
                if(isengaged) {
                    GlobalQueues.PutLiftTask(195, 10, TaskEnums.ENGAGEPTO);
                    lastengage = isengaged;
                }
                else{
                    GlobalQueues.PutLiftTask(250, 10, TaskEnums.DISENGAGEPTO);
                    lastengage = isengaged;
                }
            }

            fullLift.runFullLiftContinuos();
            //ptosystem.setPower(gamepad1.left_stick_y);

            telemetry.addData("lift position",fullLift.lift.getPosition());
            telemetry.addData("power to motors",fullLift.powertomotors);
            telemetry.addData("procentage",fullLift.CurrTaskDoneness());

            telemetry.update();
        }

    }
}