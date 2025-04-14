package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.IntoTheDeep.PIDModule;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.Task;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWithMotionProf;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.motorWraper;

public class PTOsystem {

    private motorWraper mfl,mfr,mbl,mbr;

    public PTOsystem(HardwareMap hardwareMap){
        mfl = new motorWraper(hardwareMap,"mfl");
        mfr = new motorWraper(hardwareMap,"mfr");
        mbl = new motorWraper(hardwareMap,"mbl");
        mbr = new motorWraper(hardwareMap,"mbr");

        mfl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mfl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mfr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mfr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mbl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mbl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mbr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mbr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void  setPower(double val)
    {
        mfl.setPower(val);
        mfr.setPower(val);
        mbl.setPower(val);
        mbr.setPower(val);
    }

    public void PTOActivated(){
        mbr.setDirection(DcMotorEx.Direction.FORWARD);
        mbl.setDirection(DcMotorEx.Direction.FORWARD);
        mfl.setDirection(DcMotorEx.Direction.REVERSE);
        mfr.setDirection(DcMotorEx.Direction.REVERSE);
    }

    public void PTODeactivate(){
        mbr.setDirection(DcMotorEx.Direction.FORWARD);
        mbl.setDirection(DcMotorEx.Direction.REVERSE);
        mfl.setDirection(DcMotorEx.Direction.REVERSE);
        mfr.setDirection(DcMotorEx.Direction.FORWARD);
    }

}
