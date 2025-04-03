package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses.Devices;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class RobotLocalizer {

    PinPoint pinpoint;

    public RobotLocalizer(HardwareMap hardwareMap){
        pinpoint = hardwareMap.get(PinPoint.class,"imu");
    }

    public Pose2D getPosition(){
        return pinpoint.getPosition();
    }
}
