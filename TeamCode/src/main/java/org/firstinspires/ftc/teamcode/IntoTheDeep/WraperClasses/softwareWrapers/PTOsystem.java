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

    private DcMotorEx mfl,mfr,mbl,mbr;

    public PTOsystem(HardwareMap hardwareMap){
        mfl = hardwareMap.get(DcMotorEx.class,"mfl");
        mfr = hardwareMap.get(DcMotorEx.class,"mfr");
        mbl = hardwareMap.get(DcMotorEx.class,"mbl");
        mbr = hardwareMap.get(DcMotorEx.class,"mbr");

        mfl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mfl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mfr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mfr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mbl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mbl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mbr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mbr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        MotorConfigurationType mkt;
        mkt = mfl.getMotorType();
        mkt.setAchieveableMaxRPMFraction(1);

        mfl.setMotorType(mkt);
        mfr.setMotorType(mkt);
        mbl.setMotorType(mkt);
        mbr.setMotorType(mkt);
    }

    public void  setPower(double val)
    {
        mfl.setPower(val);
        mfr.setPower(val);
        mbl.setPower(val);
        mbr.setPower(val);
    }

    public void PTOActivated(){
        mbr.setDirection(DcMotorSimple.Direction.REVERSE);
        mbl.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void PTODeactivate(){
        mbr.setDirection(DcMotorSimple.Direction.FORWARD);
        mbl.setDirection(DcMotorSimple.Direction.FORWARD);
    }

}
