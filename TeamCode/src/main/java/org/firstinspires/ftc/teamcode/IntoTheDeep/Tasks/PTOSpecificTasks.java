package org.firstinspires.ftc.teamcode.IntoTheDeep.Tasks;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.IntoTheDeep.StateRoullete.PTOSteps;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.MovingState;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Intake;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Lift;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.WheelieServosWraper;

public class PTOSpecificTasks {
    PTOSteps ptoSteps = new PTOSteps();
    Lift currLift;
    Intake currIntake;
    WheelieServosWraper wheelie;
    PIDCoefficients pidcoefs = new PIDCoefficients(0,0,0);

    public PTOSpecificTasks(HardwareMap hardwareMap,Lift lift, Intake intake){
        currLift = lift;
        currIntake = intake;
        wheelie = new WheelieServosWraper(hardwareMap);
    }

    public void updcommands(){
        currLift.setGoalPos(0);
        switch(ptoSteps.GetState()){
            case EXTENDLIFTFIRSTRANG:
                if(currLift.getPos() != 0)
                    break;
                currLift.setGoalPos(200);
                if(currLift.getPos() == 200) {
                    currLift.setGoalPos(0);
                    ptoSteps.advanceStage();
                }
                break;
            case WHEELIE:
                if(currLift.getPos() != 0)
                    break;
                wheelie.setPos(200);
                if(wheelie.getState() == MovingState.REACHED) {
                    ptoSteps.advanceStage();
                }
                break;
            case CLAMPDOWNONBARFIRST:
                currLift.setGoalPos(0);
                if(currLift.getPos() == 0) {
                    ptoSteps.advanceStage();
                }
                break;
            case EXTENDLIFTTOSECONDBAR:
                if(currLift.getPos() != 0)
                    break;
                currLift.setGoalPos(200);
                if(currLift.getPos() == 200) {
                    currLift.setGoalPos(0);
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
                currLift.setGoalPos(0);
                if(currLift.getPos() == 0) {
                    currIntake.setGoalExtendoMotor(0);
                    if (currIntake.getPos() == 0)
                        ptoSteps.advanceStage();
                }
                break;
            case STANDSTILL:
                currLift.setGoalPos(0);
                currIntake.setGoalExtendoMotor(0);
                break;
        }
        currLift.updPos();
        currIntake.GoalUpd();
    }
}
