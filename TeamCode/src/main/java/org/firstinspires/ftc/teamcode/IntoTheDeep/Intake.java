package org.firstinspires.ftc.teamcode.IntoTheDeep;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.IntoTheDeep.motorWraper.DIRECTION;

public class Intake {

    motorWraper motor;
    public Intake(HardwareMap hardwareMap){
        motor = new motorWraper(hardwareMap,"ceva", DIRECTION.FORWARD);
    }

    public void updIntake(){
        if(gamepad1.left_bumper)
            motor.setPower(1);
        else if(gamepad1.right_bumper)
            motor.setPower(-1);
        else
            motor.setPower(0);
    }
}
