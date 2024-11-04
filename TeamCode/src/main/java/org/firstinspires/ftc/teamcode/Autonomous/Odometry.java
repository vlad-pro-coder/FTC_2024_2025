package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Odometry {

    final static double L = 20f ;///variabil
    final static double B = 11f ;///variabil
    final static double R = 3f ;///variabil
    final static double N = 8192 ;///variabil

    private final double C = 2.0 * Math.PI * R / N; //cm per tick

    private int prevxleft = 0;
    private int prevxright = 0;
    private int prevy = 0;

    public int currxleft = 0;
    public int currxright = 0;
    public int curry = 0;

    public int heading = 0;

    private DcMotor XwheelLeft;
    private DcMotor XwheelRight;
    private DcMotor Ywheel;

    xyu START = new xyu(0,0,Math.toRadians(heading));
    xyu pos = START;

    Odometry(HardwareMap hardwareMap){

        XwheelLeft = hardwareMap.get(DcMotor.class,"ceva");
        XwheelRight = hardwareMap.get(DcMotor.class,"ceva");
        Ywheel = hardwareMap.get(DcMotor.class,"ceva");

    }

    public xyu GetOdometry()
    {
        prevxleft = currxleft;
        prevxright = currxright;
        prevy = curry;

        currxleft = XwheelLeft.getCurrentPosition();
        currxright = XwheelRight.getCurrentPosition();
        curry = Ywheel.getCurrentPosition();

        int dn1 = currxleft - prevxleft;
        int dn2 = currxright - prevxright;
        int dn3 = curry - prevy;

        double dx = C*(dn1+dn2)/2;
        double dy = C*(dn3 - B*(dn2-dn1)/L);
        double du = C*(dn2-dn1)/L;

        double unghi_in_rad = pos.u + (du / 2.0);

        pos.x = dx*Math.cos(unghi_in_rad) - dy*Math.sin(unghi_in_rad);
        pos.y = dx*Math.sin(unghi_in_rad) + dy*Math.cos(unghi_in_rad);
        pos.x += unghi_in_rad;

        xyu to_pass = pos;

        to_pass.u = Math.toDegrees(to_pass.u);

        return to_pass;
    }

}
