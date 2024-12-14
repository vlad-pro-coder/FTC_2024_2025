package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.MovingState;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWraper;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.motorWraper;

public class PTOInterchangeWraper {

    private motorWraper m1,m2;
    private ServoWraper connector;

    private PIDCoefficients pidcoefs = new PIDCoefficients(0,0,0);

    public PTOInterchangeWraper(HardwareMap hardwareMap){
        //connector = new ServoWraper(hardwareMap,"");
    }

    public void connectPTO(){
          connector.setProfilePosition(40);
    }
    public void disconnectPTO() {
        connector.setProfilePosition(0);
    }

    public void updServos(){
        connector.updProfile();
    }
}
