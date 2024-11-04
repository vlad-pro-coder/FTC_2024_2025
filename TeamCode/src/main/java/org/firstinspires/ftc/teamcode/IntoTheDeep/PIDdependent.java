package org.firstinspires.ftc.teamcode.IntoTheDeep;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.IntoTheDeep.HelperClasses.DirectName;
import org.firstinspires.ftc.teamcode.IntoTheDeep.motorWraper.DIRECTION;

import java.util.Vector;

public class PIDdependent {

    double Goal=0f;
    public static PIDCoefficients pidcoef = new PIDCoefficients(0.01,0,0);
    PIDModule pid = new PIDModule(pidcoef);
    Vector<motorWraper> activemotors = new Vector<motorWraper>();

    public PIDdependent(HardwareMap hardwareMap, Vector<DirectName> actionMotor){
            for(DirectName piece:actionMotor)
                activemotors.add(new motorWraper(hardwareMap,piece.name,piece.direction));

    }
    public void upd(){
        pid.SetPIDcoefs(pidcoef);

        double power = pid.Update(Goal - activemotors.get(0).getPos());

        for(int i=0;i<activemotors.size();i++)
            activemotors.get(i).setPower(power);
    }
    public void setTargetPos(double Pos){
        Goal = Pos;
        pid.resetIntegral();
    }
}
