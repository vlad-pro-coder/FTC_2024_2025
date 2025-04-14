package org.firstinspires.ftc.teamcode.IntoTheDeep;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.Commands.isExtendoReady;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.Commands.wheeliedone;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.drive.Drive;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses.Devices.IMUWraper;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses.Devices.RobotLocalizer;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.Commands;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.TaskEnums;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Extendo;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.FullLift;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Intake;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Outtake;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Wheelie;
import org.firstinspires.ftc.teamcode.IntoTheDeep.test.Localizer_test;

@TeleOp(name = "new_robot")
@Config
public class main extends LinearOpMode {

    drivetrain Drivetrain;

    public static boolean entercomm=false,docomm=false,outtakedone = false;
    public static double qextendolength=0,qliftlength=0,qouttakelength=0;
    public static IMUWraper imu;

    public static double pos,prevpos;
    public static RobotLocalizer pinpoint;

    @Override
    public void runOpMode() throws InterruptedException {
        Drivetrain = new drivetrain(hardwareMap);
        Commands commands = new Commands(hardwareMap);
        imu = new IMUWraper(hardwareMap,"imuProst");
        //ElapsedTime time = new ElapsedTime();
        pinpoint = new RobotLocalizer(hardwareMap);

        while (opModeInInit()) {
            Drivetrain.idleMotors();
            commands.initRobot();
        }
        waitForStart();

        while (opModeIsActive()) {
            Drivetrain.setWheelsPower(gamepad1);


            commands.robotcommands(gamepad1,gamepad2);

            /*telemetry.addData("fps", 1/time.seconds());
            telemetry.addData("can enter", gamepad1.square);
            telemetry.addData("enter command",entercomm);
            telemetry.addData("do command",docomm);
            telemetry.addData("extendo",qextendolength);
            telemetry.addData("lift",qliftlength);
            telemetry.addData("outtake",qouttakelength);
            telemetry.addData("outtake done",outtakedone);
            telemetry.addData("currTask", Outtake.currTask.component1target + " " + Outtake.currTask.component2target + " " + Outtake.currTask.component3target);
            telemetry.addData("extendo servo pos",Outtake.extendos1.CurrPos);
            telemetry.addData("lift position", FullLift.lift.getPosition());
            telemetry.addData("extendo position", Extendo.m1.getCurrentPosition());
            telemetry.addData("lift curr task", FullLift.currTask.component1target);
            telemetry.addData("lift curr pos through val", FullLift.doneval);
            telemetry.addData("power to lift motors", FullLift.powertomotors);
            telemetry.addData("is lift ready", FullLift.CurrTaskDoneness());

            telemetry.addData("extendo curr pos through val", Extendo.encoderExtendo.getCurrentPosition());
            telemetry.addData("power to extendo motors", Extendo.powertomotors);
            telemetry.addData("is extendo ready", Extendo.CurrTaskDoneness());

            telemetry.addData("difference in pos", Extendo.currTask.component1target - Extendo.encoderExtendo.getCurrentPosition());

            telemetry.addData("chasis", Drivetrain.data);
            telemetry.addData("chasis variables",Drivetrain.data_console);*/

            telemetry.addData("imu pitch", imu.getPitch());
            telemetry.addData("extendo ready", isExtendoReady);
            telemetry.addData("extendo power", Extendo.powertomotors);
            telemetry.addData("wheelie done", wheeliedone);


            //time.reset();
            telemetry.update();
        }
    }
}

