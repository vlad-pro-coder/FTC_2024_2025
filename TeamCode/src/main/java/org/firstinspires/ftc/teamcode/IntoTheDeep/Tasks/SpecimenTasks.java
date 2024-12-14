package org.firstinspires.ftc.teamcode.IntoTheDeep.Tasks;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.IntoTheDeep.StateRoullete.BasketSteps;
import org.firstinspires.ftc.teamcode.IntoTheDeep.StateRoullete.SpecimenSteps;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.MovingState;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.moduleWrapers.FullLift;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.moduleWrapers.Outtake;

public class SpecimenTasks {

    FullLift currLift;
    Outtake currOuttake;
    SpecimenSteps specimenSteps;
    private final double LowRangLift=400,HighRangLift=800;
    public SpecimenTasks(FullLift lift, Outtake outtake){
        currLift = lift;
        currOuttake = outtake;
    }

    void updCommand(Gamepad gamepad){
        switch (specimenSteps.GetState()) {
            case WALLPOSITION:
                currOuttake.setGoal(0,0,0);
                currLift.setGoal(0);
                if(currOuttake.getState() == MovingState.REACHED&& currLift.getState() == MovingState.REACHED&&gamepad.triangle)
                    specimenSteps.advanceStage();
                break;
            case CLOSECLAW:
                ///some values to outtake and lift
                currOuttake.setGoal(0,0,60);
                if(currOuttake.getState() == MovingState.REACHED)
                    specimenSteps.advanceStage();
                break;
            case ROTATEOVERHEAD:
                ///some values to outtake and lift
                currOuttake.setGoal(0,0,0);
                if(currOuttake.getState() == MovingState.REACHED)
                    specimenSteps.advanceStage();
                break;
            case GETLIFTUP:
                if(gamepad.dpad_right)
                    currLift.setGoal(HighRangLift);
                if(gamepad.dpad_left)
                    currLift.setGoal(LowRangLift);
                if(currLift.getState() == MovingState.REACHED)
                    specimenSteps.advanceStage();
                break;
            case RETRACTNORMALWHILEPULLING:
                currOuttake.setGoal(0,0,0);
                currLift.setGoal(0);
                break;

        }
        currLift.updPos();
        currOuttake.updServos();
    }


}
