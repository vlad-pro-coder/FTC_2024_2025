package org.firstinspires.ftc.teamcode.IntoTheDeep.StateRoullete;

import org.firstinspires.ftc.teamcode.IntoTheDeep.StateRoullete.enums.BasketStepsToTake;

import java.util.Arrays;
import java.util.Vector;

public class BasketSteps {

    Vector<BasketStepsToTake> orderedSteps = new Vector<BasketStepsToTake>(Arrays.asList(
            BasketStepsToTake.ALIGNWITHINTAKE,
            BasketStepsToTake.CLOSECLAW,
            BasketStepsToTake.LIFTUP,
            BasketStepsToTake.OPENCLAW,
            BasketStepsToTake.RETRACTNORMAL
    ));
    int index=0;

    public BasketSteps(){
        index=0;
    }

    public void startLoop(){
        index=0;
    }
    public BasketStepsToTake GetState(){
        return orderedSteps.get(index);
    }
    public void advanceStage(){
        index++;
    }
}
