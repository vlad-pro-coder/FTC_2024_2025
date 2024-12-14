package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.motorWraper;

public class IntakeRollingMotor {

    motorWraper motor;
    public IntakeRollingMotor(HardwareMap hardwareMap){
        //motor = new motorWraper(hardwareMap,"", motorWraper.DIRECTION.FORWARD,new PIDCoefficients(0,0,0));
    }
    public void rollForward(){motor.setPower(1);}
    public void rollBackward(){motor.setPower(-1);}
    public void stop(){motor.setPower(0);}
}
