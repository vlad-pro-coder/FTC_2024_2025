package org.firstinspires.ftc.teamcode.IntoTheDeep;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses.LinearFunction;

public class AsymMotionProfile {
    private double maxVelocity, acceleration, deceleration, initialVelocity;
    private ElapsedTime time = new ElapsedTime();
    private double accelerationTime, deccelarationTime, constantTime,mvUsed,velocity, sig;
    private double initialPosition, targetPosition, currentPosition;

    private double t0 = 0, t1 = 0, t2 = 0;
    public AsymMotionProfile(double maxVelocity, double acceleration, double deceleration){
        this.maxVelocity = maxVelocity;
        this.acceleration = acceleration;
        this.deceleration = deceleration;
    }

    public void SetCoeficients(double maxvel,double accel,double decel){
        maxVelocity = maxvel;
        acceleration = accel;
        deceleration = decel;
    }

    public void startMotion(double initialPosition, double targetPosition, double initialVelocity){
        this.initialVelocity = initialVelocity;
        startMotion(initialPosition, targetPosition);
    }
    public void startMotion(double initialPos, double targetPos){
        initialVelocity = 0;
        if(initialPos == targetPos) {
            currentPosition = targetPosition;
            return;
        }
        RobotLog.i("(stub! - handled) Asymmetric Motion can't make a profile for a distance of 0 units");
        double dist = Math.abs(targetPos - initialPos);
        sig = Math.signum(targetPos - initialPos);
        initialPosition = initialPos;
        targetPosition = targetPos;
        currentPosition = initialPosition;

        accelerationTime = (maxVelocity - initialVelocity) / acceleration;
        deccelarationTime = maxVelocity / deceleration;

        if(dist <= (accelerationTime + deccelarationTime) * maxVelocity / 2){
            constantTime = 0;
            mvUsed = Math.sqrt(2*dist / (1.0 / acceleration + 1.0 / deceleration));
            accelerationTime = mvUsed / acceleration;
            deccelarationTime = mvUsed / deceleration;

        } else {
            constantTime = dist / maxVelocity - accelerationTime / 2 - deccelarationTime / 2;
            mvUsed = maxVelocity;
        }

        t0 = accelerationTime;
        t1 = t0 + constantTime;
        t2 = t1 + deccelarationTime;


        velocity = 0;
        time.reset();
    }
    public double getPrecentOfMotion(){
        return LinearFunction.resultfunction(initialPosition,targetPosition,0,100,currentPosition);
    }
    private double a(double t){
        if(t <= t0) return acceleration;
        if(t <= t1) return 0;
        if(t <= t2) return deceleration;
        return 0;
    }
    private double v(double t){
        if(t <= t0) return t * acceleration + initialVelocity;
        if(t <= t1) return maxVelocity + initialVelocity;
        if(t <= t2) return maxVelocity + initialVelocity - deceleration * (t - t1);
        return 0;
    }
    private double p(double t){
        if(t <= t0) return acceleration / 2 * t * t + t * initialVelocity;
        if(t <= t1) return acceleration / 2 * t0 * t0 + mvUsed * (t - t0) + initialVelocity * t0;
        if(t <= t2) return acceleration / 2 * t0 * t0 + mvUsed * (t - t0) - deceleration / 2 * (t - t1) * (t - t1) + initialVelocity * t0;
        return 0;
    }
    public void update(){
        if(time.seconds() < t2)
            currentPosition = initialPosition + sig * p(time.seconds());
        else currentPosition = targetPosition;
    }
    public double getVelocity(){
        return v(time.seconds());
    }
    public double getAcceleration(){
        return a(time.seconds());
    }
    public double getPosition(){
        return currentPosition;
    }
    public void setInstant(double position) { currentPosition = position; }
    public boolean motionEnded(){
        return time.seconds() >= t2;
    }
    public double getTimeUntilMotionEnd(){
        return t2 - time.seconds();
    }
    public double getTargetPosition(){ return targetPosition; }
}