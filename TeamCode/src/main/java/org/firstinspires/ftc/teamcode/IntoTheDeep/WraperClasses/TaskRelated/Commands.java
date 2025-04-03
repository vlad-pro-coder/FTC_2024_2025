package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.currControllerTask;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.main.docomm;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.main.entercomm;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.main.imu;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.main.outtakedone;

import android.provider.Settings;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Extendo;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.FullLift;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Intake;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Lift;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Outtake;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Wheelie;

public class Commands {

    private boolean callonce = false,startCheckingAngle = false;
    public static double pitchAngle;
    public static double GoForwardAngle = 120;

    Gamepad prevgm1 = new Gamepad();
    Gamepad prevgm2 = new Gamepad();

    FullLift fullLift;
    Extendo extendo;
    Outtake outtake;
    Intake intake;
    Wheelie wheelie;

    private boolean highbasket = true;
    private boolean isSampleCycleActive = true;

    public Commands(HardwareMap hardwareMap){
        fullLift = new FullLift(hardwareMap);
        extendo = new Extendo(hardwareMap);
        outtake = new Outtake(hardwareMap);
        intake = new Intake(hardwareMap,false);
        wheelie = new Wheelie(hardwareMap);
    }

    public void initRobot(){
        if(!callonce) {
            outtake.defaultPosition();
            wheelie.defaultPosition();
            fullLift.defaultPosition();
            callonce = true;
        }
        outtake.runOuttakeContinuos();
        fullLift.runFullLiftContinuos();
    }

    public void robotcommands(Gamepad gm1,Gamepad gm2){

        outtake.runOuttakeContinuos();
        fullLift.runFullLiftContinuos();
        extendo.runExtendoContinuos(gm1);
        intake.runIntakeContinuos(gm1);
        //wheelie.runWheelieContinuos();

        outtakedone = outtake.CurrTaskDoneness();

        if(gm1.dpad_up)
            highbasket = true;
        if(gm1.dpad_down)
            highbasket = false;

        if(startCheckingAngle){
            pitchAngle = imu.getPitch();
        }

        //schimba modul de scorare continua;

        if(gm1.cross != prevgm1.cross&&gm1.cross){
            isSampleCycleActive = !isSampleCycleActive;
            if(isSampleCycleActive){//inseamna ca vino din pozitia de scorare continua de specimene
                GlobalQueues.PutOuttakeTask(340,240,85,1,TaskEnums.SAMPLE_STORAGE_POSITION);
                GlobalQueues.PutLiftTask(100,30,TaskEnums.GIVE_LIFT_POS);

                currControllerTask = TaskEnumsOverall.PULL_DOWN_IF_OUTTAKE_DONE;
            }

            if(!isSampleCycleActive){
                GlobalQueues.PutLiftTask(100,30,TaskEnums.SPECIMEN_WALL_HEIGHT);
                GlobalQueues.PutOuttakeTask(15,250,85,1,TaskEnums.SPECIMEN_WALL_POSITION);
                GlobalQueues.PutLiftTask(0,30,TaskEnums.SPECIMEN_WALL_HEIGHT);

                currControllerTask = TaskEnumsOverall.SPECIMEN_MODE;
            }
        }

        //specimen

        if(!isSampleCycleActive) {

            if (currControllerTask == TaskEnumsOverall.CLOSE_CLAW_ON_SPECIMEN && outtake.CurrTaskDoneness()) {
                //urm va fi bar position
                GlobalQueues.PutLiftTask(320, 30, TaskEnums.SPECIMEN_BAR_HEIGHT);
                currControllerTask = TaskEnumsOverall.NEUTRE_POSITION;
            }

            if (gm1.square != prevgm1.square && gm1.square) {
                ///specimen
                if (currControllerTask == TaskEnumsOverall.WALL_POSITION) {
                    GlobalQueues.PutOuttakeTask(246, 100, 50, 10, TaskEnums.SPECIMEN_BAR_POSITION);
                    currControllerTask = TaskEnumsOverall.CLOSE_CLAW_ON_SPECIMEN;
                } else {
                    GlobalQueues.PutOuttakeTask(15, 250, 85, 1, TaskEnums.SPECIMEN_WALL_POSITION);
                    GlobalQueues.PutLiftTask(0, 30, TaskEnums.SPECIMEN_WALL_HEIGHT);
                    currControllerTask = TaskEnumsOverall.WALL_POSITION;
                }
            }
        }
        else {
            //sample

            if (currControllerTask == TaskEnumsOverall.HOLD_TO_LOW_BASKET && !highbasket) {
                GlobalQueues.PutLiftTask(320, 30, TaskEnums.SAMPLE_LOW_BASKET_HEIGHT);
                currControllerTask = TaskEnumsOverall.HOLD_TO_HIGH_BASKET;
            }

            if (currControllerTask == TaskEnumsOverall.HOLD_TO_HIGH_BASKET && highbasket) {
                GlobalQueues.PutLiftTask(820, 30, TaskEnums.SAMPLE_HIGH_BASKET_HEIGHT);
                currControllerTask = TaskEnumsOverall.HOLD_TO_LOW_BASKET;
            }

            if (currControllerTask == TaskEnumsOverall.CLOSE_CLAW_ON_SAMPLE && outtake.CurrTaskDoneness()) {

                GlobalQueues.PutOuttakeTask(80, 280, 50, 1, TaskEnums.SAMPLE_OVER_BASKET_POSITION);
                currControllerTask = TaskEnumsOverall.HOLD_TO_HIGH_BASKET;
            }

            if (currControllerTask == TaskEnumsOverall.LET_GO_SAMPLE && outtake.CurrTaskDoneness()) {
                GlobalQueues.PutOuttakeTask(345, 200, 85, 70, TaskEnums.SAMPLE_STORAGE_POSITION);
                currControllerTask = TaskEnumsOverall.NEUTRE_POSITION_OUTTAKE;
                outtake.runOuttakeContinuos();
            }

            if (currControllerTask == TaskEnumsOverall.NEUTRE_POSITION_OUTTAKE && outtake.CurrTaskDoneness()) {
                GlobalQueues.PutLiftTask(100, 30, TaskEnums.SAMPLE_STORAGE_HEIGHT);
                currControllerTask = TaskEnumsOverall.SAFELY_DRAG_LIFT_DOWN;
            }


            if(currControllerTask == TaskEnumsOverall.GET_EXTENDO_BACK && extendo.CurrTaskDoneness()){
                GlobalQueues.PutLiftTask(0,30,TaskEnums.GIVE_LIFT_POS);

                currControllerTask = TaskEnumsOverall.DRAG_LIFT_DOWN;
                fullLift.runFullLiftContinuos();
            }

            if(currControllerTask == TaskEnumsOverall.DRAG_LIFT_DOWN && fullLift.CurrTaskDoneness()){
                GlobalQueues.PutOuttakeTask(345, 200, 50,1,TaskEnums.CLOSE_CLAW);

                currControllerTask = TaskEnumsOverall.CLOSE_CLAW_ON_SAMPLE;
            }

            if (gm1.square != prevgm1.square && gm1.square) {
                ///sample
                if (currControllerTask == TaskEnumsOverall.HOLD_TO_LOW_BASKET || currControllerTask == TaskEnumsOverall.HOLD_TO_HIGH_BASKET) {
                    GlobalQueues.PutOuttakeTask(80, 280, 85, 1, TaskEnums.SAMPLE_OVER_BASKET_LET_GO);
                    currControllerTask = TaskEnumsOverall.LET_GO_SAMPLE;
                }
                else {
                    GlobalQueues.PutExtendoTask(0, 30, TaskEnums.EXTEND_TO_POS);
                    highbasket = true;
                    currControllerTask = TaskEnumsOverall.GET_EXTENDO_BACK;
                }
            }
        }

        //climb

        if(currControllerTask == TaskEnumsOverall.ENGAGE_AND_DRAGDOWN_TILL_POS_ZERO_1 && fullLift.CurrTaskDoneness() && wheelie.CurrTaskDoneness()){
            GlobalQueues.PutLiftTask(195,10,TaskEnums.ENGAGEPTO);
            GlobalQueues.PutLiftTask(0,30,TaskEnums.PULL_DOWN);

            currControllerTask = TaskEnumsOverall.DISENGAGE_AND_SET_LIFT_TO_POS_OVER_SECOND_BAR_WHEELIE_RETRACT;
            startCheckingAngle = true;
        }
        //de adaugat un unghi sa nu traga in jos degeaba
        else if(currControllerTask == TaskEnumsOverall.DISENGAGE_AND_SET_LIFT_TO_POS_OVER_SECOND_BAR_WHEELIE_RETRACT && fullLift.CurrTaskDoneness() &&  pitchAngle > GoForwardAngle){
            startCheckingAngle = false;
            GlobalQueues.PutWheelieTask(0,355,TaskEnums.WHEELIE);
            GlobalQueues.PutLiftTask(250,10,TaskEnums.DISENGAGEPTO);
            GlobalQueues.PutLiftTask(0,30,TaskEnums.OVER_BAR_HEIGHT);

            currControllerTask = TaskEnumsOverall.ENGAGE_AND_DRAGDOWN_TILL_POS_ZERO_2;
        }
        else if(currControllerTask == TaskEnumsOverall.ENGAGE_AND_DRAGDOWN_TILL_POS_ZERO_2 && fullLift.CurrTaskDoneness()){
            GlobalQueues.PutLiftTask(195,355,TaskEnums.ENGAGEPTO);
            GlobalQueues.PutLiftTask(0,30,TaskEnums.OVER_BAR_HEIGHT);

            currControllerTask = TaskEnumsOverall.NEUTRE_POSITION;
        }

        if(prevgm1.triangle != gm1.triangle && gm1.triangle){

            currControllerTask = TaskEnumsOverall.WHEELIE_LIFT_TO_FIRST_BAR;
            GlobalQueues.PutWheelieTask(120,1,TaskEnums.WHEELIE);
            GlobalQueues.PutLiftTask(0,30,TaskEnums.OVER_BAR_HEIGHT);

            currControllerTask = TaskEnumsOverall.ENGAGE_AND_DRAGDOWN_TILL_POS_ZERO_1;
        }

        prevgm1.copy(gm1);
        prevgm2.copy(gm2);
    }
}
