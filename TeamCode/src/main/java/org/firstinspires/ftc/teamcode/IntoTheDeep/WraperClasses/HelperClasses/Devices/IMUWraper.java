package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses.Devices;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class IMUWraper {

    IMU imu;
    double timePassed = 0;
    private final double freq = 20;
    YawPitchRollAngles orientation;
    AngularVelocity angularVelocity;
    public IMUWraper(HardwareMap hardwareMap,String name){
        imu = hardwareMap.get(IMU.class,name);
    }

    public void initialize_IMU(){
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;

        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

        imu.initialize(new IMU.Parameters(orientationOnRobot));
        imu.resetYaw();
        orientation = imu.getRobotYawPitchRollAngles();
        angularVelocity = imu.getRobotAngularVelocity(AngleUnit.DEGREES);
    }

    public double getPitch(){
        if(System.currentTimeMillis() - timePassed > freq)
            orientation = imu.getRobotYawPitchRollAngles();
        return orientation.getPitch(AngleUnit.DEGREES);
    }
    public double getYaw(){
        if(System.currentTimeMillis() - timePassed > freq)
            orientation = imu.getRobotYawPitchRollAngles();
        return orientation.getYaw(AngleUnit.DEGREES);
    }
    public double getRoll(){
        if(System.currentTimeMillis() - timePassed > freq)
            orientation = imu.getRobotYawPitchRollAngles();
        return orientation.getRoll(AngleUnit.DEGREES);
    }

    public double getVelPitch(){
        if(System.currentTimeMillis() - timePassed > freq)
            angularVelocity = imu.getRobotAngularVelocity(AngleUnit.DEGREES);
        return angularVelocity.xRotationRate;
    }
    public double getVelYaw(){
        if(System.currentTimeMillis() - timePassed > freq)
            angularVelocity = imu.getRobotAngularVelocity(AngleUnit.DEGREES);
        return angularVelocity.zRotationRate;
    }
    public double getVelRoll(){
        if(System.currentTimeMillis() - timePassed > freq)
            angularVelocity = imu.getRobotAngularVelocity(AngleUnit.DEGREES);
        return angularVelocity.yRotationRate;
    }
}
