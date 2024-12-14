package org.firstinspires.ftc.teamcode.IntoTheDeep.Tasks;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.IntoTheDeep.StateRoullete.PTOSteps;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.MovingState;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.moduleWrapers.FullLift;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.moduleWrapers.Intake;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Lift;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.WheelieServosWraper;

public class PTOSpecificTasks {
    PTOSteps ptoSteps = new PTOSteps();
    FullLift currLift;
    Intake currIntake;
    WheelieServosWraper wheelie;
    PIDCoefficients pidcoefs = new PIDCoefficients(0,0,0);

    public PTOSpecificTasks(HardwareMap hardwareMap, FullLift lift, Intake intake){
        currLift = lift;
        currIntake = intake;
        wheelie = new WheelieServosWraper(hardwareMap);
    }

    public void updcommands(){
        currLift.setGoal(0);
        switch(ptoSteps.GetState()){
            case EXTENDLIFTFIRSTRANG:
                currLift.setGoal(200);
                if(currLift.getState() == MovingState.REACHED) {
                    currLift.setGoal(0);
                    ptoSteps.advanceStage();
                }
                break;
            case WHEELIE:
                wheelie.setPos(200);
                if(wheelie.getState() == MovingState.REACHED) {
                    ptoSteps.advanceStage();
                }
                break;
            case CLAMPDOWNONBARFIRST:
                currLift.setGoal(0);
                if(currLift.getState() == MovingState.REACHED) {
                    ptoSteps.advanceStage();
                }
                break;
            case EXTENDLIFTTOSECONDBAR:
                currLift.setGoal(200);
                if(currLift.getState() == MovingState.REACHED) {
                    currLift.setGoal(0);
                    ptoSteps.advanceStage();
                }
                break;
            case EXTENDOBALANCEANDCLOSEWHEELIE:
                wheelie.setPos(0);
                currIntake.setGoalExtendoMotor(200);
                if(currIntake.getPos() == 200) {
                    ptoSteps.advanceStage();
                }
                break;
            case CLAMPDOWNONBARSECONDANDRETRACTEXTENDO:
                currLift.setGoal(0);
                if(currLift.getState() == MovingState.REACHED) {
                    currIntake.setGoalExtendoMotor(0);
                    if (currIntake.getPos() == 0)
                        ptoSteps.advanceStage();
                }
                break;
            case STANDSTILL:
                currLift.setGoal(0);
                currIntake.setGoalExtendoMotor(0);
                break;
        }
        currLift.updPos();
        currIntake.GoalUpd();
        wheelie.updServos();
    }
}
