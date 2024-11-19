package org.firstinspires.ftc.teamcode.IntoTheDeep;

import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDModule {

    private PIDCoefficients pidcoef;

    private double lasterror = 0;
    private double integral = 0;

    private ElapsedTime time = new ElapsedTime();

    public PIDModule(PIDCoefficients coefs){
        pidcoef = coefs;
    }

    public double Update(double currerror){

        double dt = time.seconds();
        double derivate = (lasterror-currerror)/dt;
        integral+=lasterror*dt;

        lasterror = currerror;
        time.reset();

        return currerror*pidcoef.p+derivate*pidcoef.d+integral*pidcoef.i;
    }
    public void resetIntegral(){
        integral = 0;
    }
    public void SetPIDcoefs(PIDCoefficients coefs){
        pidcoef = coefs;
    }

}
