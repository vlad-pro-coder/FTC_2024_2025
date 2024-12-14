package org.firstinspires.ftc.teamcode.Alejandra;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.motorWraper;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.PTOInterchangeWraper;

public class Lift {

    public static double goalPos=0;
    public static PIDCoefficients pidcoef = new PIDCoefficients(0.01,0,0);
    private motorWraper m1,m2;
    public Lift(HardwareMap hardwareMap){
        m1 = new motorWraper(hardwareMap,"test", motorWraper.DIRECTION.FORWARD,pidcoef);
        m2 = new motorWraper(hardwareMap,"test", motorWraper.DIRECTION.FORWARD,pidcoef);
    }

    public void setGoal(double pos){
        m1.setGoalPos(goalPos);
        m2.setGoalPos(goalPos);
    }

    public void updPos(){
        m1.updGoalPos();
        m2.updGoalPos();
    }

    public void changecoefs(PIDCoefficients coefs){
        m1.changeCoefs(coefs);
        m2.changeCoefs(coefs);
    }

    public PositionState getPos() {
        if(m1.getPos() == goalPos&&m2.getPos() == goalPos)
            return PositionState.REACHED;
        return PositionState.MOVING;
    }
}
