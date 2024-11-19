package org.firstinspires.ftc.teamcode.IntoTheDeep.StateRoullete;

import org.firstinspires.ftc.teamcode.IntoTheDeep.StateRoullete.enums.PTOStepsToTake;

import java.util.Arrays;
import java.util.Vector;

public class PTOSteps {
    Vector<PTOStepsToTake> orderedSteps = new Vector<PTOStepsToTake>(Arrays.asList(
            PTOStepsToTake.EXTENDLIFTFIRSTRANG,
            PTOStepsToTake.WHEELIE,
            PTOStepsToTake.CLAMPDOWNONBARFIRST,
            PTOStepsToTake.EXTENDLIFTTOSECONDBAR,
            PTOStepsToTake.EXTENDOBALANCEANDCLOSEWHEELIE,
            PTOStepsToTake.CLAMPDOWNONBARSECONDANDRETRACTEXTENDO,
            PTOStepsToTake.STANDSTILL
    ));
    int index=0;

    public PTOSteps(){
        index=0;
    }

    public void startLoop(){
        index=0;
    }
    public PTOStepsToTake GetState(){
        return orderedSteps.get(index);
    }
    public void advanceStage(){
        index++;
    }
}
