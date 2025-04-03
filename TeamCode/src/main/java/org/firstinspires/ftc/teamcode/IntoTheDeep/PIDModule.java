package org.firstinspires.ftc.teamcode.IntoTheDeep;

import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDModule {

    private double lasterror = 0;
    private double integral = 0;
    private final double antiwindup = 0.9;

    private ElapsedTime time = new ElapsedTime();

    public double freq = 100;
    public double lastPower = 0;

    public double Update(double currerror,PIDCoefficients coefs){
        if(time.milliseconds()*1000 < 1.0/freq)
            return lastPower;

        double dt = time.seconds();
        double derivate = (currerror-lasterror)/dt;
        integral = (integral + currerror*dt);

        lasterror = currerror;
        time.reset();

        lastPower = currerror*coefs.p+derivate*coefs.d+integral*coefs.i;
        return lastPower;
    }

    public void resetIntegral(){
        integral = 0;
        time.reset();
    }

}
