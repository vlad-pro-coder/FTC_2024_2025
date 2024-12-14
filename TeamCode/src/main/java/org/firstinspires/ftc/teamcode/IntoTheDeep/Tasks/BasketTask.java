package org.firstinspires.ftc.teamcode.IntoTheDeep.Tasks;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.IntoTheDeep.StateRoullete.BasketSteps;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.MovingState;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.moduleWrapers.FullLift;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.moduleWrapers.Outtake;

public class BasketTask {

    FullLift currLift;
    Outtake currOuttake;
    BasketSteps basketSteps;
    public BasketTask(FullLift lift, Outtake outtake){
        currLift = lift;
        currOuttake = outtake;
    }

    public void updCommand(Gamepad gamepad){
            switch (basketSteps.GetState()) {
                case ALIGNWITHINTAKE:
                    currOuttake.setGoal(0,0,0);
                    currLift.setGoal(0);
                    if(currOuttake.getState() == MovingState.REACHED&& currLift.getState() == MovingState.REACHED&&gamepad.triangle)
                        basketSteps.advanceStage();
                    break;
                case CLOSECLAW:
                    ///some values to outtake and lift
                    currOuttake.setGoal(0,0,60);
                    if(currOuttake.getState() == MovingState.REACHED)
                        basketSteps.advanceStage();
                    break;
                case LIFTUP:
                    ///some values to outtake and lift
                    currLift.setGoal(1000);
                    if(currLift.getState() == MovingState.REACHED)
                        basketSteps.advanceStage();
                    break;
                case ROTATEARM:
                    currOuttake.setGoal(200,0,60);
                    if(currOuttake.getState() == MovingState.REACHED)
                        basketSteps.advanceStage();
                    break;
                case OPENCLAW:
                    currOuttake.setGoal(200,0,0);
                    if(currOuttake.getState() == MovingState.REACHED)
                        basketSteps.advanceStage();
                    break;
                case RETRACTNORMAL:
                    currOuttake.setGoal(0,0,0);
                    currLift.setGoal(0);
                    break;

            }
            currOuttake.updServos();
            currLift.updPos();
        }


}
