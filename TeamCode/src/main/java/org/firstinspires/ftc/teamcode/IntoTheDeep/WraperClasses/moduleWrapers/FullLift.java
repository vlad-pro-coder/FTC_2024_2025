package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.moduleWrapers;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.MovingState;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.motorWraper;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Lift;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.PTOInterchangeWraper;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.PTOResultedMotors;

@Config
public class FullLift {
    public static double goalPos=0;
    public boolean IsPTOactive = false;
    Lift lift;
    PTOResultedMotors ptoMotors;

    public FullLift(HardwareMap hardwareMap){
        lift = new Lift(hardwareMap);
        ptoMotors = new PTOResultedMotors(hardwareMap);
    }

    public void activatePTOchange(){IsPTOactive=true;}
    public void deactivatePTOchange(){IsPTOactive=false;}

    public void setGoal(double pos){
        lift.setGoal(goalPos);
        ptoMotors.setGoal(goalPos);
    }

    public void updPos(){
        lift.updPos();
        if(IsPTOactive)
            ptoMotors.updPos();
    }

    public MovingState getState(){
        return lift.getPos() == goalPos? MovingState.REACHED:MovingState.MOVING;
    }

    public void changecoefs(PIDCoefficients coefs){
        lift.changecoefs(coefs);
        ptoMotors.changeCoefs(coefs);
    }

}
