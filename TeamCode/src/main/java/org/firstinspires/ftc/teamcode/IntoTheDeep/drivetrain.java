package org.firstinspires.ftc.teamcode.IntoTheDeep;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.motorWraper;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.motorWraper;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

public class drivetrain {

    DcMotorEx mfl,mfr,mbl,mbr;

    public String data,data_console;
    public drivetrain(HardwareMap hardwareMap){
        mfl = hardwareMap.get(DcMotorEx.class,"mfl");
        mfr = hardwareMap.get(DcMotorEx.class,"mfr");
        mbl = hardwareMap.get(DcMotorEx.class,"mbl");
        mbr = hardwareMap.get(DcMotorEx.class,"mbr");

        mfl.setDirection(DcMotorSimple.Direction.REVERSE);
        mbl.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void idleMotors(){
        mfl.setPower(0);
        mfr.setPower(0);
        mbl.setPower(0);
        mbr.setPower(0);
    }

    public void setWheelsPower(Gamepad gm1) {

        double x = gm1.left_stick_x;
        double y = gm1.left_stick_y;
        double rx = gm1.right_trigger - gm1.left_trigger;

        double difference = Math.max(Math.abs(x)+Math.abs(y)+Math.abs(rx*rx*rx/2.0),1.0);

        data = "mfr" + " " + (y+x-rx*rx*rx/2.0)/difference + " " + "mfl" + " " + (y+x+rx*rx*rx/2.0)/difference + " " + "mbr" + " " + (y-x-rx*rx*rx/2.0)/difference + " " + "mbl" + " " + (y-x+rx*rx*rx/2.0)/difference;
        data_console = "x" + " " + x + "y" + " " + y + "rx" + " " + rx + "difference" + " " + difference;

        mfl.setPower((double)(y-x+rx*rx*rx/2.0)/difference);
        mbl.setPower((double)(y+x+rx*rx*rx/2.0)/difference);
        mfr.setPower((double)(y+x-rx*rx*rx/2.0)/difference);
        mbr.setPower((double)(y-x-rx*rx*rx/2.0)/difference);
    }
}
