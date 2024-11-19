package org.firstinspires.ftc.teamcode.IntoTheDeep.StateRoullete;

import org.firstinspires.ftc.teamcode.IntoTheDeep.StateRoullete.enums.SpecimentStepsToTake;

import java.util.Arrays;
import java.util.Vector;

public class SpecimenSteps {

    Vector<SpecimentStepsToTake> orderedSteps = new Vector<SpecimentStepsToTake>(Arrays.asList(
            SpecimentStepsToTake.WALLPOSITION,
            SpecimentStepsToTake.OPENCLAW,
            SpecimentStepsToTake.CLOSECLAW,
            SpecimentStepsToTake.ROTATEOVERHEAD,
            SpecimentStepsToTake.PULLDOWN,
            SpecimentStepsToTake.RETRACTNORMAL
    ));
    int index=0;

    public SpecimenSteps(){
        index=0;
    }

    public void startLoop(){
        index=0;
    }
    public SpecimentStepsToTake GetState(){
        return orderedSteps.get(index);
    }
    public void advanceStage(){
        index++;
    }
}
