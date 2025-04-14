package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses.Devices;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class RobotLocalizer {

    PinPoint pinpoint;

    private final double X = 118.016, Y = 0.116, yawScalar = 1.0019;
    private final PinPoint.EncoderDirection xPod = PinPoint.EncoderDirection.FORWARD, yPod = PinPoint.EncoderDirection.FORWARD;
    public Pose2D pos = new Pose2D(DistanceUnit.MM,0,0,AngleUnit.DEGREES,0);

    public RobotLocalizer(HardwareMap hardwareMap){
        pinpoint = hardwareMap.get(PinPoint.class,"pinpoint");
        pinpoint.setOffsets(X, Y,DistanceUnit.MM);
        pinpoint.setEncoderResolution(PinPoint.GoBildaOdometryPods.goBILDA_4_BAR_POD);
//        pinPoint.setYawScalar(yawScalar);
        pinpoint.setEncoderDirections(xPod, yPod);
        pinpoint.resetPosAndIMU();
    }

    public void resetPosition(){
        pinpoint.resetPosAndIMU();
    }

    public double DistFromPoint(Pose2D startPos){
        //in mm
        if(startPos == null)
            return 0;
        return Math.sqrt((startPos.getX(DistanceUnit.MM) - pos.getX(DistanceUnit.MM)) * (startPos.getX(DistanceUnit.MM) - pos.getX(DistanceUnit.MM))
         + (startPos.getY(DistanceUnit.MM) - pos.getY(DistanceUnit.MM)) * (startPos.getY(DistanceUnit.MM) - pos.getY(DistanceUnit.MM)));
    }

    public void update(){
        pos = pinpoint.getPosition();
        pinpoint.update();
    }
}
