package org.firstinspires.ftc.teamcode.Teleop;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class driveRobotCentric {

    private final DcMotor mfl,mfr,mbl,mbr;
    private final double idlePower = 0;
    private double speedreduction = 0.1;
    private boolean isactive = false;

    Gamepad prev = new Gamepad();

    driveRobotCentric(HardwareMap hardwareMap) {
        mfl = hardwareMap.get(DcMotor.class, "mfl");
        mfr = hardwareMap.get(DcMotor.class, "mfr");
        mbl = hardwareMap.get(DcMotor.class, "mbl");
        mbr = hardwareMap.get(DcMotor.class, "mbr");

        mfl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mfr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mbl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mbr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }


    void setZeroPower()
    {
            mfl.setPower(idlePower);
            mfr.setPower(idlePower);
            mbl.setPower(idlePower);
            mbr.setPower(idlePower);
    }

    public void setWheelsPower() {

            double x = gamepad1.left_stick_x,y = -gamepad1.left_stick_y;
            double rx = gamepad1.right_trigger - gamepad1.left_trigger;

            double difference = Math.min(Math.abs(x)+Math.abs(y)+Math.abs(rx),1);

            mfl.setPower((y+x+rx)/difference*speedreduction);
            mbl.setPower((y-x+rx)/difference*speedreduction);
            mfr.setPower((y-x-rx)/difference*speedreduction);
            mbr.setPower((y+x-rx)/difference*speedreduction);

        }


    }



