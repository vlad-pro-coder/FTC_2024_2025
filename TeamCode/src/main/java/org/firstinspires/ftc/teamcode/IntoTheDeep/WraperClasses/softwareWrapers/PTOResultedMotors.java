package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.motorWraper;

public class PTOResultedMotors {

    private motorWraper m1,m2;
    private PIDCoefficients pidcoefs = new PIDCoefficients(0,0,0);

    public PTOResultedMotors(HardwareMap hardwareMap) {
        //m1 = new motorWraper(hardwareMap,"", motorWraper.DIRECTION.FORWARD,pidcoefs);
        //m2 = new motorWraper(hardwareMap,"", motorWraper.DIRECTION.FORWARD,pidcoefs);
    }

    public void changeCoefs(PIDCoefficients coefs){
        m1.changeCoefs(coefs);
        m2.changeCoefs(coefs);
    }
    public void setGoal(double Goal){
        m1.setGoalPos(Goal);
        m2.setGoalPos(Goal);
    }
    public void updPos(){
        m1.updGoalVel();
        m2.updGoalVel();
    }
}
