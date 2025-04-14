package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers;

import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.isPTOActive;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.Task;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.TaskEnums;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.hardwareWrapers.ServoWithMotionProf;

public class Engage {

    private ServoWithMotionProf engage;
    private Task currTask = new Task(0,355, TaskEnums.WHEELIE);

    public Engage(HardwareMap hardwareMap){
        engage = new ServoWithMotionProf(hardwareMap,"PTOengager",2000,2500,2000);
    }

    //engage 195
    //disengage 250

    public void defaultPosition(){
        DisengagePTO();
    }

    public boolean CurrTaskDoneness(){
        return Math.abs(engage.CurrPos - currTask.component1target) <= currTask.Treshhold;
    }

    public void DisengagePTO(){
        currTask.component1target = 250;
        engage.setProfilePosition(250);
        isPTOActive = false;
    }

    public void EngagePTO(){
        currTask.component1target = 190;
        engage.setProfilePosition(190);
        isPTOActive = true;
    }

    public void runEngageContinuos(){
        engage.updProfile();
    }

}
