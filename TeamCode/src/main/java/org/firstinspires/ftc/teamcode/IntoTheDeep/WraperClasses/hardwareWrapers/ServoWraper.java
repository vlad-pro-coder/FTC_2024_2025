package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ServoWraper {

    private final double maxAngle = 355;
    private Servo servo;

    public double TargetPos=0;
    public double CurrentPos=0;
    public  double maxSpeed;
    public double maxAcceleration;
    public ElapsedTime time;
    public double distance;

    ///constants between pos changes;
    public double startPos,sign;
    public double distAccel,distanceCruise,timeInAccel,timeInCruise;

    public ServoWraper(HardwareMap hardwareMap, String name , double maxSpeed,double maxAcceleration){
        servo = hardwareMap.get(Servo.class,name);
        servo.setPosition(0);
        TargetPos = 0;
        this.maxSpeed = maxSpeed;
        this.maxAcceleration = maxAcceleration;
        time = new ElapsedTime();
    }

    private double AngleToPower(double angle){
        return angle / maxAngle;
    }
    private double PowerToAngle(double power){
        return power * maxAngle;
    }

    public void setProfilePosition(double TargetPos){

        this.startPos = CurrentPos;
        this.TargetPos = AngleToPower(TargetPos);
        sign = Math.signum(this.TargetPos - CurrentPos);
        distance = Math.abs(this.TargetPos - CurrentPos);

        distAccel = maxSpeed * maxSpeed / (2*maxAcceleration);

        if(distAccel * 2 > distance)
            distAccel = distance / 2;

        distanceCruise = distance - distAccel*2;
        timeInAccel = Math.sqrt(distAccel * 2 / maxAcceleration);
        timeInCruise = distanceCruise / maxSpeed;

        time.reset();
    }

    public void updProfile(){

        double currentTime = time.seconds();

        if(currentTime > timeInCruise + 2*timeInAccel){
            CurrentPos = TargetPos;
            return;
        }

        if(timeInAccel > currentTime){
            double distanceInAccel = 0.5 * maxAcceleration * currentTime * currentTime;
            this.CurrentPos = startPos + sign * distanceInAccel;
        }

        else if(timeInAccel + timeInCruise > currentTime){
            double timeCruised = currentTime - timeInAccel;
            double distCruised = timeCruised * maxSpeed;
            this.CurrentPos = startPos + sign * (distAccel + distCruised);
        }

        else{
            double timeInDecel = currentTime - timeInAccel - timeInCruise;
            double distDeceled = distanceCruise + distAccel + maxSpeed * timeInDecel - 0.5 * maxAcceleration * timeInDecel * timeInDecel;
            this.CurrentPos = startPos + sign * (distAccel + distanceCruise + distDeceled);
        }

        servo.setPosition(this.CurrentPos);
    }

    public double getPosAngle(){
        return PowerToAngle(CurrentPos);
    }

    public void setNormallyPos(double pos){
        servo.setPosition(pos);
    }

}
