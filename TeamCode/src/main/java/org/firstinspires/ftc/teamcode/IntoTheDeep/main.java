package org.firstinspires.ftc.teamcode.IntoTheDeep;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWraper;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.moduleWrapers.FullLift;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Lift;


@TeleOp(name = "new_robot")
@Config
public class main extends LinearOpMode {

    public static double anglepos=0;
    public static double speed = 2;
    public static double acceleration = 10;
    public static double pos = 0;
    ElapsedTime time = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        //drivetrain wheels = new drivetrain(hardwareMap);
        //FullLift fulllift = new FullLift(hardwareMap);


        ServoWraper s = new ServoWraper(hardwareMap,"test",speed,acceleration);


        while (opModeInInit()) {
            //wheels.idleMotors();
        }
        waitForStart();

        while (opModeIsActive()) {
            time.reset();
            if(s.TargetPos != anglepos/355)
                s.setProfilePosition(anglepos);

            telemetry.addData("target pos",s.TargetPos);
            telemetry.addData("curr Pos",s.CurrentPos);
            telemetry.addData("time passed",s.time.seconds());
            telemetry.addData("distance",s.distance);
            telemetry.addData("direction",s.sign);

            telemetry.addData("acceldist",s.distAccel);
            telemetry.addData("cruisedist",s.distanceCruise);

            telemetry.addData("time in cruise",s.timeInCruise);
            telemetry.addData("time in accel",s.timeInAccel);

            s.updProfile();
            s.maxSpeed = speed;
            s.maxAcceleration = acceleration;

            //s.setNormallyPos(pos);
            telemetry.addData("fps",1/time.seconds());
            telemetry.update();
        }
    }
}

