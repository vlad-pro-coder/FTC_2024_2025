package org.firstinspires.ftc.teamcode.IntoTheDeep;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.motorWraper;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.motorWraper.DIRECTION;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

public class drivetrain {

    motorWraper mfl,mfr,mbl,mbr;
    public drivetrain(HardwareMap hardwareMap){
            motorWraper mfl = new motorWraper(hardwareMap,"mfl",DIRECTION.FORWARD,new PIDCoefficients(0,0,0));
            motorWraper mfr = new motorWraper(hardwareMap,"mfr",DIRECTION.FORWARD,new PIDCoefficients(0,0,0));
            motorWraper mbl = new motorWraper(hardwareMap,"mbl",DIRECTION.FORWARD,new PIDCoefficients(0,0,0));
            motorWraper mbr = new motorWraper(hardwareMap,"mbr",DIRECTION.FORWARD,new PIDCoefficients(0,0,0));
    }

    public void idleMotors(){
        mfl.setPower(0);
        mfr.setPower(0);
        mbl.setPower(0);
        mbr.setPower(0);
    }

    public void setWheelsPower() {

        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double rx = gamepad1.right_trigger - gamepad1.left_trigger;

        double difference = Math.min(Math.abs(x)+Math.abs(y)+Math.abs(rx),1);

        mfl.setPower((y+x+rx)/difference);
        mbl.setPower((y-x+rx)/difference);
        mfr.setPower((y-x-rx)/difference);
        mbr.setPower((y+x-rx)/difference);
    }
}
