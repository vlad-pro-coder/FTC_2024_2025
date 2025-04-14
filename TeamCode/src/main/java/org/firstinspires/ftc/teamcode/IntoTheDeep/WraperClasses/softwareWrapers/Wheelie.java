package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.LiftQueue;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.OuttakeQueue;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.Task;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.TaskEnums;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWithMotionProf;

public class Wheelie {

    private ServoWithMotionProf wheelie1,wheelie2;
    private Task currTask = new Task(0,10, TaskEnums.WHEELIE);

    public Wheelie(HardwareMap hardwareMap){
        wheelie1 = new ServoWithMotionProf(hardwareMap,"wheelie1",700,1000,700);
        wheelie2 = new ServoWithMotionProf(hardwareMap,"wheelie2",700,1000,700);
    }

    public void defaultPosition(){
        CloseWheelie();
    }

    public boolean CurrTaskDoneness(){
        return Math.abs(wheelie1.CurrPos - currTask.component1target) <= currTask.Treshhold;
    }

    ///wheelie1 closed = 147, wheelie2 closed = 211
    ///wheelie1 open = 280, wheelie2 open = 78
    //dif = 133
    public void OpenWheelie(){

        currTask.component1target = 250;
        wheelie1.setProfilePosition(250);
        wheelie2.setProfilePosition(40);
    }

    public void CloseWheelie(){
        currTask.component1target = 160;
        wheelie1.setProfilePosition(160);
        wheelie2.setProfilePosition(130);
    }

    public void runWheelieContinuos(){
        wheelie1.updProfile();
        wheelie2.updProfile();
    }

}
