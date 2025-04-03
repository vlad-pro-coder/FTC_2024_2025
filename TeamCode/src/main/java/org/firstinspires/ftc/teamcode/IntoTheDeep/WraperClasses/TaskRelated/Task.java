package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated;

public class Task {

    public double component1target=0,component2target=0,component3target=0;
    public double Treshhold = 0;
    public TaskEnums TaskState;

    public Task(double componentTarget1,double threshhold,TaskEnums tasktype)
    {
        this.component1target = componentTarget1;
        Treshhold = threshhold;
        TaskState = tasktype;
    }
    public Task(double componentTarget1,double componentTarget2,double threshhold,TaskEnums tasktype)
    {
        this.component1target = componentTarget1;
        this.component2target = componentTarget2;
        Treshhold = threshhold;
        TaskState = tasktype;
    }
    public Task(double componentTarget1,double componentTarget2,double componentTarget3,double threshhold,TaskEnums tasktype)
    {
        this.component1target = componentTarget1;
        this.component2target = componentTarget2;
        this.component3target = componentTarget3;
        Treshhold = threshhold;
        TaskState = tasktype;
    }
}
