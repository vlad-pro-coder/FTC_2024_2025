package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWraper;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.motorWraper;

public class PTOInterchangeWraper {

    private motorWraper m1,m2;
    private ServoWraper connector;

    private PIDCoefficients pidcoefs = new PIDCoefficients(0,0,0);

    public PTOInterchangeWraper(HardwareMap hardwareMap){
        m1 = new motorWraper(hardwareMap,"", motorWraper.DIRECTION.FORWARD,pidcoefs);
        m2 = new motorWraper(hardwareMap,"", motorWraper.DIRECTION.FORWARD,pidcoefs);
        connector = new ServoWraper(hardwareMap,"");
    }

    public void connectPTO(){
          connector.setAngle(40);
    }
    public void disconnectPTO(){
        connector.setAngle(0);
    }

    public void changecoefs(PIDCoefficients coefs){
        m1.changeCoefs(coefs);
        m2.changeCoefs(coefs);
    }
}
