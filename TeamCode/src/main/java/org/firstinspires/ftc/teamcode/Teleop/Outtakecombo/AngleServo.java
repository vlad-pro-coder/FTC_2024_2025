package org.firstinspires.ftc.teamcode.Teleop.Outtakecombo;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class AngleServo {
    IMU.Parameters parameters;
    IMU imu;
    Servo angler;

    public AngleServo(HardwareMap hardwareMap){

        angler = hardwareMap.get(Servo.class,"angleservo");
        imu = hardwareMap.get(IMU.class, "imu");
        parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
                )
        );
        imu.initialize(parameters);
    }
    public void update(){
        double Angle_Robot = imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.DEGREES);
        angler.setPosition((180-Angle_Robot)/180);
    }
    public void forcePos(double pos){
        angler.setPosition(pos);
    }

}
