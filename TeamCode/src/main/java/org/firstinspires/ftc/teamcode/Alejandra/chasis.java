package org.firstinspires.ftc.teamcode.Alejandra;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

public class chasis {

    DcMotorEx mfl,mfr,mbl,mbr;
    public chasis(HardwareMap hardwareMap){
        mfl = hardwareMap.get(DcMotorEx.class,"mfs");
        mfr = hardwareMap.get(DcMotorEx.class,"mfd");
        mbl = hardwareMap.get(DcMotorEx.class,"msd");
        mbr = hardwareMap.get(DcMotorEx.class,"mss");


        mfr.setDirection(DcMotorSimple.Direction.REVERSE);
        mbl.setDirection(DcMotorSimple.Direction.REVERSE);
        mbr.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void idleMotors(){
        mfl.setPower(0);
        mfr.setPower(0);
        mbl.setPower(0);
        mbr.setPower(0);
    }

    public void setWheelsPower(Gamepad gamepad) {

        double x = gamepad.left_stick_x;
        double y = gamepad.left_stick_y;
        double rx = gamepad.right_trigger - gamepad.left_trigger;

        double difference = Math.min(Math.abs(x)+Math.abs(y)+Math.abs(rx),1);

        mfr.setPower((y+rx+x)/difference * 0.7);
        mfl.setPower((y-rx-x)/difference * 0.7);
        mbr.setPower((y-rx+x)/difference * 0.7);
        mbl.setPower((y+rx-x)/difference * 0.7);


    }
}
