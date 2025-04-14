package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated;

import java.util.LinkedList;
import java.util.Queue;

public class GlobalQueues {

    public static Queue<Task>LiftQueue = new LinkedList<>();
    public static Queue<Task>ExtendoQueue = new LinkedList<>();
    public static Queue<Task>OuttakeQueue = new LinkedList<>();

    public static TaskEnumsOverall currControllerTask = TaskEnumsOverall.SAMPLE_MODE;
    public static boolean isPTOActive = false;

    public static void PutLiftTask(double position,double Threshhold,TaskEnums tasktype){LiftQueue.add(new Task(position,Threshhold,tasktype));}

    public static void PutExtendoTask(double position,double Threshhold,TaskEnums tasktype){ExtendoQueue.add(new Task(position,Threshhold,tasktype));}
    //public static void PutIntakeTask(double dropdownposition,TaskEnums tasktype){IntakeQueue.add(new Task(dropdownposition,tasktype));}
    public static void PutOuttakeTask(double circularservo,double extendoservo,double claw,double Threshhold,TaskEnums tasktype){
        OuttakeQueue.add(new Task(circularservo,extendoservo,claw,Threshhold,tasktype));
    }

    public static void resetOnStartUp(){
        LiftQueue.clear();
        ExtendoQueue.clear();
        OuttakeQueue.clear();

        currControllerTask = TaskEnumsOverall.SAMPLE_MODE;
        isPTOActive = false;
    }

}
