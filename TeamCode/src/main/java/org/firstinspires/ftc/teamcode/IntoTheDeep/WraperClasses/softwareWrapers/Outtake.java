package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.OuttakeQueue;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.PutOuttakeTask;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.main.qouttakelength;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.Task;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.TaskEnums;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWithMotionProf;

public class Outtake {

    private ServoWithMotionProf overheads1,overheads2;
    private ServoWithMotionProf extendos1;
    private ServoWithMotionProf claws1;
    private Task currTask;

    public Outtake(HardwareMap hardwareMap){
        overheads1 = new ServoWithMotionProf(hardwareMap,"overhead1",6000,4500,3000);
        overheads2 = new ServoWithMotionProf(hardwareMap,"overhead2",6000,4500,3000);
        extendos1 = new ServoWithMotionProf(hardwareMap,"extendos1",4000,6000,4000);
        claws1 = new ServoWithMotionProf(hardwareMap,"claw",2000,2500,2000);
    }

    public void defaultPosition(){

        PutOuttakeTask(345, 200,85,10,TaskEnums.NEUTRE_POSITION_GENERAL);
    }

    public boolean CurrTaskDoneness(){
        if(currTask == null)
            return true;
        return Math.max(
                Math.max(Math.abs(overheads1.CurrPos - currTask.component1target),
                        Math.abs(extendos1.CurrPos - currTask.component2target)),
                Math.abs(claws1.CurrPos - currTask.component3target)) <= currTask.Treshhold;
    }

    public void  runOuttakeContinuos()
    {
        extendos1.updProfile();
        overheads2.updProfile();
        overheads1.updProfile();
        claws1.updProfile();

        qouttakelength = OuttakeQueue.size();

        if(OuttakeQueue.isEmpty())
            return;
        if(currTask != null && !CurrTaskDoneness())
            return;
        if(CurrTaskDoneness()) {
            currTask = OuttakeQueue.poll();
            overheads1.setProfilePosition(currTask.component1target);
            overheads2.setProfilePosition(currTask.component1target);
            extendos1.setProfilePosition(currTask.component2target);
            claws1.setProfilePosition(currTask.component3target);
        }

    }

}
