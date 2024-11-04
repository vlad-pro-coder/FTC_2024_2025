package org.firstinspires.ftc.teamcode.Teleop.Outtakecombo;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Outtake {

    LinearMotionServos frontbackservo1,frontbackservo2,updownservo,picker1,picker2, rotatorservo;
    AngleServo angleservo;

    double updownpos = 0;
    double rotatepos = 0;


    public Outtake(HardwareMap hardwareMap){
        frontbackservo1 = new LinearMotionServos(hardwareMap,"fbs1");
        frontbackservo2 = new LinearMotionServos(hardwareMap,"fbs2");
        //picker1 = new LinearMotionServos(hardwareMap,"pick1");
        //picker2 = new LinearMotionServos(hardwareMap,"pick2");
        updownservo = new LinearMotionServos(hardwareMap,"updownservo");
        rotatorservo = new LinearMotionServos(hardwareMap,"rotservo");

        angleservo = new AngleServo(hardwareMap);

    }

    public void updAngler(){
        angleservo.update();
    }
    public void forcePosAngler(double pos){angleservo.forcePos(pos);}

    public void openpicker1(){
        picker1.setPos(1);
    }
    public void openpicker2(){
        picker2.setPos(1);
    }

    public void movefrontback(double poz){
        frontbackservo1.setPos(poz);
        frontbackservo2.setPos(poz);
    }

    public void rotateservo(double poz){
        rotatorservo.setPos(poz);
    }

    public void updoservo(double poz) {
        updownservo.setPos(poz);
    }

}
