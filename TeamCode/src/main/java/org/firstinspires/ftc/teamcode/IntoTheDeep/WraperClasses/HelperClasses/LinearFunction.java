package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses;

public class LinearFunction {
    public static double resultfunction(double start,double end,double resultstart,double resultend,double x){
        if(x<start)
            return 0;
        if(x>end)
            return resultend;
        return (resultend - resultstart) / (end - start) * x + resultstart;
    }
}
