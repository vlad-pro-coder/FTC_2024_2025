package org.firstinspires.ftc.teamcode.IntoTheDeep.Tasks;

import static org.firstinspires.ftc.teamcode.IntoTheDeep.StateRoullete.enums.RangLift.FIRSTRANGLIFT;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.StateRoullete.enums.RangLift.SECONDRANGLIFT;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.IntoTheDeep.StateRoullete.BasketSteps;
import org.firstinspires.ftc.teamcode.IntoTheDeep.StateRoullete.SpecimenSteps;
import org.firstinspires.ftc.teamcode.IntoTheDeep.StateRoullete.enums.RangLift;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.OuttakeServoWraper;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Lift;


//to put mutant s1angle = 5
//              s2angle = 315

//to get mutant s1angle = 3.9
//              s2angle = 27

//take sample intake s1angle = 236
//                   s2angle = 264

enum ChainEvents{
    BASKETPLACEMENT,
    SPECIMENPLACEMENT,
    STANDBY
}

enum PTOState{
    CONNECTED,
    DISCONNECTED,
}

@Config
public class OuttakeSpecificTasks {

    public static double s1Angle=0f,s2Angle=0f;
    public static double dpadup=0,dpaddown=0,dpadleft=0,dpadright=0;
    Lift lift;
    private double maxAngle = 355;
    Telemetry telemetrie;
    OuttakeServoWraper outtakeServoWraper;
    ChainEvents ActiveEvent = ChainEvents.STANDBY;
    Gamepad prevpad;
    BasketSteps basketSteps;
    SpecimenSteps specimenSteps;
    RangLift SpecimentRangExtension;
    PTOState statePTO = PTOState.DISCONNECTED;

    public OuttakeSpecificTasks(HardwareMap hardwareMap, Telemetry telemetry,Lift currLift){
        outtakeServoWraper = new OuttakeServoWraper(hardwareMap);

        basketSteps = new BasketSteps();
        specimenSteps = new SpecimenSteps();
        SpecimentRangExtension = FIRSTRANGLIFT;

        telemetrie = telemetry;
        lift = currLift;
    }

    public void OuttakeControls(Gamepad gamepad){

        if(statePTO == PTOState.DISCONNECTED) {
            if (gamepad.triangle != prevpad.triangle && gamepad.triangle && ActiveEvent == ChainEvents.STANDBY) {
                ActiveEvent = ChainEvents.BASKETPLACEMENT;
                basketSteps.startLoop();
            }
            if (gamepad.square != prevpad.square && gamepad.square && ActiveEvent == ChainEvents.STANDBY) {
                ActiveEvent = ChainEvents.SPECIMENPLACEMENT;
                basketSteps.startLoop();
            }

            if (ActiveEvent == ChainEvents.BASKETPLACEMENT) {
                if (gamepad.triangle != prevpad.triangle && gamepad.triangle)
                    basketSteps.advanceStage();
                switch (basketSteps.GetState()) {
                    case ALIGNWITHINTAKE:
                        ///some values to outtake and lift
                        break;
                    case CLOSECLAW:
                        ///some values to outtake and lift
                        break;
                    case LIFTUP:
                        ///some values to outtake and lift
                        break;
                    case OPENCLAW:
                        ///some values to outtake and lift
                        break;
                    case RETRACTNORMAL:
                        ActiveEvent = ChainEvents.STANDBY;
                        break;

                }
            }

            if (ActiveEvent == ChainEvents.BASKETPLACEMENT) {
                if (gamepad.square != prevpad.square && gamepad.square)
                    specimenSteps.advanceStage();
                switch (specimenSteps.GetState()) {
                    case WALLPOSITION:
                        ///some values to outtake and lift
                        break;
                    case OPENCLAW:
                        ///some values to outtake and lift
                        break;
                    case CLOSECLAW:
                        ///some values to outtake and lift
                        break;
                    case ROTATEOVERHEAD:
                        if (gamepad.dpad_up)
                            SpecimentRangExtension = SECONDRANGLIFT;
                        if (gamepad.dpad_down)
                            SpecimentRangExtension = FIRSTRANGLIFT;

                        ///some values to outtake
                        break;
                    case PULLDOWN:
                        ///some values to outtake and lift
                        if (SpecimentRangExtension == FIRSTRANGLIFT) {
                            ///specific value lift
                        } else {
                            ///specific value lift
                        }
                        break;
                    case OPENCLAWAGAIN:
                        ///some claw value
                        break;
                    case RETRACTNORMAL:
                        ActiveEvent = ChainEvents.STANDBY;
                        break;
                }
            }

            if (ActiveEvent == ChainEvents.STANDBY) {
                SpecimentRangExtension = FIRSTRANGLIFT;
                //default values
            }
        }

        prevpad.copy(gamepad);

    }
}
