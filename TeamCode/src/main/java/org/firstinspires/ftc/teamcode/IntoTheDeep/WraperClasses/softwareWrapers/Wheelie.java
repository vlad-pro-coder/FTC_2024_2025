package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.LiftQueue;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.OuttakeQueue;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.WheelieQueue;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.Task;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWithMotionProf;

public class Wheelie {

    private ServoWithMotionProf wheelie1,wheelie2;
    private Task currTask;

    public Wheelie(HardwareMap hardwareMap){
        wheelie1 = new ServoWithMotionProf(hardwareMap,"wheelie1",0,0,0);
        wheelie2 = new ServoWithMotionProf(hardwareMap,"wheelie2",0,0,0);
    }

    public void defaultPosition(){
        wheelie1.CurrPos = 120;
        wheelie2.CurrPos = 120;

        wheelie1.setProfilePosition(120);
        wheelie2.setProfilePosition(120);
    }

    public boolean CurrTaskDoneness(){
        return Math.abs(wheelie1.CurrPos - currTask.component1target) <= currTask.Treshhold;
    }

    public void runWheelieContinuos(){
        wheelie1.updProfile();
        wheelie2.updProfile();

        if(WheelieQueue.isEmpty())
            return;
        if(currTask != null && CurrTaskDoneness())
            return;
        if(currTask == null && CurrTaskDoneness()) {
            currTask = WheelieQueue.poll();
            wheelie1.setProfilePosition(currTask.component1target);
            wheelie2.setProfilePosition(currTask.component1target);
        }
    }

}
