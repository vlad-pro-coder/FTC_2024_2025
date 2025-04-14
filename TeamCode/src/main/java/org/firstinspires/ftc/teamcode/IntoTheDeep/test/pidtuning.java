package org.firstinspires.ftc.teamcode.IntoTheDeep.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.TaskEnums;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.FullLift;

@TeleOp(name = "pid_tunning")
@Config
public class pidtuning extends LinearOpMode {

    public static double pos = 0,prevpos = 0;

    public static FullLift fullLift;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        fullLift = new FullLift(hardwareMap);
        while (opModeInInit()) {

        }
        waitForStart();

        while (opModeIsActive()) {
            /*if(pos != prevpos){
                GlobalQueues.PutLiftTask(pos, 30, TaskEnums.SPECIMEN_WALL_HEIGHT);
                prevpos = pos;
            }

            fullLift.runFullLiftContinuos();
*/
            telemetry.addData("lift position",fullLift.lift.getPosition());
            telemetry.addData("power to motors",fullLift.powertomotors);
            telemetry.addData("procentage",fullLift.CurrTaskDoneness());

            telemetry.update();
        }

    }
}
