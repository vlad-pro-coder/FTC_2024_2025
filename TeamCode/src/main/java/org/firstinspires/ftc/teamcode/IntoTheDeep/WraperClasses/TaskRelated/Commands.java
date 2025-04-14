package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.currControllerTask;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.TaskRelated.GlobalQueues.resetOnStartUp;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.main.docomm;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.main.entercomm;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.main.imu;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.main.outtakedone;
import static org.firstinspires.ftc.teamcode.IntoTheDeep.main.pinpoint;

import android.provider.Settings;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Engage;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Extendo;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.FullLift;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Intake;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Outtake;
import org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.softwareWrapers.Wheelie;

public class Commands {

    private boolean callonce = false,startCheckingAngle = false;
    public static double pitchAngle=180;
    public static double GoForwardAngle = 96;

    Gamepad prevgm1 = new Gamepad();
    Gamepad prevgm2 = new Gamepad();

    FullLift fullLift;
    Extendo extendo;
    Outtake outtake;
    Intake intake;
    Wheelie wheelie;
    Engage engage;

    private boolean highbasket = true, dpads_pressed = false;
    private boolean isSampleCycleActive = true,usePinpoint = false;
    private final Pose2D lastPos = new Pose2D(DistanceUnit.MM,0,0, AngleUnit.DEGREES,0);
    public static boolean wheeliedone = false;

    public static boolean isExtendoReady = false;

    public Commands(HardwareMap hardwareMap){
        fullLift = new FullLift(hardwareMap);
        extendo = new Extendo(hardwareMap);
        outtake = new Outtake(hardwareMap);
        intake = new Intake(hardwareMap,false);
        wheelie = new Wheelie(hardwareMap);
        engage = new Engage(hardwareMap);
    }

    public void initRobot(){
        if(!callonce) {
            resetOnStartUp();
            outtake.defaultPosition();
            wheelie.defaultPosition();
            fullLift.defaultPosition();
            engage.defaultPosition();
            callonce = true;
        }
        outtake.runOuttakeContinuos();
        fullLift.runFullLiftContinuos();
        wheelie.runWheelieContinuos();
        engage.runEngageContinuos();
    }

    public void robotcommands(Gamepad gm1,Gamepad gm2){

        outtake.runOuttakeContinuos();
        fullLift.runFullLiftContinuos();
        extendo.runExtendoContinuos(gm1);
        intake.runIntakeContinuos(gm1);
        if(usePinpoint)
            pinpoint.update();
        wheelie.runWheelieContinuos();
        engage.runEngageContinuos();
        wheeliedone = wheelie.CurrTaskDoneness();

        isExtendoReady = extendo.CurrTaskDoneness();

        if(gm1.dpad_up) {
            highbasket = true;
            dpads_pressed = true;
        }
        if(gm1.dpad_down) {
            highbasket = false;
            dpads_pressed = true;
        }

        //schimba modul de scorare continua;

        if(currControllerTask == TaskEnumsOverall.WAIT_TO_DRAG_DOWN_LIFT && outtake.CurrTaskDoneness()){
            GlobalQueues.PutLiftTask(0,30,TaskEnums.SPECIMEN_WALL_HEIGHT);

            currControllerTask = TaskEnumsOverall.SPECIMEN_MODE;
        }

        if(gm1.cross != prevgm1.cross&&gm1.cross){
            isSampleCycleActive = !isSampleCycleActive;
            if(isSampleCycleActive){//inseamna ca vino din pozitia de scorare continua de specimene
                GlobalQueues.PutOuttakeTask(15,245,50,10,TaskEnums.SPECIMEN_WALL_POSITION);
                GlobalQueues.PutOuttakeTask(340,240,85,1,TaskEnums.SAMPLE_STORAGE_POSITION);
                GlobalQueues.PutLiftTask(100,30,TaskEnums.GIVE_LIFT_POS);

                currControllerTask = TaskEnumsOverall.SAMPLE_MODE;
            }

            if(!isSampleCycleActive){
                GlobalQueues.PutLiftTask(100,30,TaskEnums.SPECIMEN_WALL_HEIGHT);
                GlobalQueues.PutOuttakeTask(15,245,85,1,TaskEnums.SPECIMEN_WALL_POSITION);

                currControllerTask = TaskEnumsOverall.WAIT_TO_DRAG_DOWN_LIFT;
            }
        }

        //specimen

        if(!isSampleCycleActive) {

            if (currControllerTask == TaskEnumsOverall.CLOSE_CLAW_ON_SPECIMEN && outtake.CurrTaskDoneness()) {
                //urm va fi bar position
                GlobalQueues.PutOuttakeTask(246, 100, 50, 0, TaskEnums.SPECIMEN_BAR_POSITION);
                GlobalQueues.PutLiftTask(320, 30, TaskEnums.SPECIMEN_BAR_HEIGHT);
                currControllerTask = TaskEnumsOverall.NEUTRE_POSITION;
            }

            if(currControllerTask == TaskEnumsOverall.DRAG_LIFT_DOWN_SAFELY_AFTER_PUT_SPECIMEN && outtake.CurrTaskDoneness()){
                GlobalQueues.PutLiftTask(0, 30, TaskEnums.SPECIMEN_WALL_HEIGHT);

                currControllerTask = TaskEnumsOverall.WALL_POSITION;
            }

            if (gm1.square != prevgm1.square && gm1.square) {
                ///specimen
                if (currControllerTask == TaskEnumsOverall.WALL_POSITION) {
                    GlobalQueues.PutOuttakeTask(15, 245, 50, 0, TaskEnums.SPECIMEN_BAR_POSITION);
                    currControllerTask = TaskEnumsOverall.CLOSE_CLAW_ON_SPECIMEN;
                } else {
                    GlobalQueues.PutOuttakeTask(15, 245, 85, 50, TaskEnums.SPECIMEN_WALL_POSITION);

                    currControllerTask = TaskEnumsOverall.DRAG_LIFT_DOWN_SAFELY_AFTER_PUT_SPECIMEN;
                }
            }
        }
        else {
            //sample

            if(currControllerTask == TaskEnumsOverall.SWITCH_BASKET_OUTTAKE_POS && ((prevgm1.dpad_up != gm1.dpad_up && gm1.dpad_up) || (prevgm1.dpad_down != gm1.dpad_down && gm1.dpad_down))){
                GlobalQueues.PutOuttakeTask(170, 280, 50, 0, TaskEnums.SAMPLE_OVER_BASKET_POSITION);
                dpads_pressed = true;
                outtake.runOuttakeContinuos();
                currControllerTask = TaskEnumsOverall.BASKET_POS;
            }

            if(currControllerTask == TaskEnumsOverall.OVER_BASKET_OUTTAKE && fullLift.CurrTaskDoneness()){
                GlobalQueues.PutOuttakeTask(80, 280, 50, 1, TaskEnums.SAMPLE_OVER_BASKET_POSITION);
                currControllerTask = TaskEnumsOverall.SWITCH_BASKET_OUTTAKE_POS;
            }

            if (currControllerTask == TaskEnumsOverall.BASKET_POS && !highbasket && outtake.CurrTaskDoneness()) {
                GlobalQueues.PutLiftTask(320, 30, TaskEnums.SAMPLE_LOW_BASKET_HEIGHT);
                currControllerTask = TaskEnumsOverall.OVER_BASKET_OUTTAKE;
            }

            if (currControllerTask == TaskEnumsOverall.BASKET_POS && highbasket && outtake.CurrTaskDoneness()) {
                GlobalQueues.PutLiftTask(800, 30, TaskEnums.SAMPLE_HIGH_BASKET_HEIGHT);
                currControllerTask = TaskEnumsOverall.OVER_BASKET_OUTTAKE;
            }

            if (currControllerTask == TaskEnumsOverall.LET_GO_SAMPLE && outtake.CurrTaskDoneness()) {
                GlobalQueues.PutOuttakeTask(345, 200, 85, 70, TaskEnums.SAMPLE_STORAGE_POSITION);
                pinpoint.resetPosition();
                usePinpoint = true;
                currControllerTask = TaskEnumsOverall.NEUTRE_POSITION_OUTTAKE;
                outtake.runOuttakeContinuos();
            }

            if (currControllerTask == TaskEnumsOverall.NEUTRE_POSITION_OUTTAKE && outtake.CurrTaskDoneness() && pinpoint.DistFromPoint(lastPos) > 110 ) {
                GlobalQueues.PutLiftTask(100, 30, TaskEnums.SAMPLE_STORAGE_HEIGHT);
                usePinpoint = false;
                currControllerTask = TaskEnumsOverall.SAFELY_DRAG_LIFT_DOWN;
            }

            if(currControllerTask == TaskEnumsOverall.GET_EXTENDO_BACK && extendo.CurrTaskDoneness()){
                GlobalQueues.PutLiftTask(0,30,TaskEnums.GIVE_LIFT_POS);

                currControllerTask = TaskEnumsOverall.DRAG_LIFT_DOWN;
                fullLift.runFullLiftContinuos();
            }


            if(currControllerTask == TaskEnumsOverall.READY_FOR_TRANSFER_SAMPLE && outtake.CurrTaskDoneness()){
                GlobalQueues.PutOuttakeTask(170, 280, 50, 0, TaskEnums.SAMPLE_OVER_BASKET_POSITION);
                GlobalQueues.PutLiftTask(100,30,TaskEnums.GIVE_LIFT_POS);
                currControllerTask = TaskEnumsOverall.SWITCH_BASKET_OUTTAKE_POS;
            }

            if(currControllerTask == TaskEnumsOverall.DRAG_LIFT_DOWN && fullLift.CurrTaskDoneness()){
                GlobalQueues.PutOuttakeTask(345, 200, 50,0,TaskEnums.CLOSE_CLAW);
                currControllerTask = TaskEnumsOverall.READY_FOR_TRANSFER_SAMPLE;
            }


            if (gm1.square != prevgm1.square && gm1.square) {
                ///sample
                if (currControllerTask == TaskEnumsOverall.SWITCH_BASKET_OUTTAKE_POS) {
                    GlobalQueues.PutOuttakeTask(80, 280, 85, 1, TaskEnums.SAMPLE_OVER_BASKET_LET_GO);
                    currControllerTask = TaskEnumsOverall.LET_GO_SAMPLE;
                }
                else if(currControllerTask == TaskEnumsOverall.SAMPLE_MODE || currControllerTask == TaskEnumsOverall.SAFELY_DRAG_LIFT_DOWN || (currControllerTask == TaskEnumsOverall.NEUTRE_POSITION_OUTTAKE && !dpads_pressed)){
                    GlobalQueues.PutExtendoTask(0, 30, TaskEnums.RETRACT_EXTENDO_FOR_CYCLE);
                    dpads_pressed = false;
                    currControllerTask = TaskEnumsOverall.GET_EXTENDO_BACK;
                }
            }
        }

        //climb

        if(startCheckingAngle){
            pitchAngle = imu.getPitch();
        }

        if(currControllerTask == TaskEnumsOverall.ENGAGE_AND_DRAGDOWN_TILL_POS_ZERO_2 && fullLift.CurrTaskDoneness() &&  pitchAngle >= 90&& pitchAngle <= GoForwardAngle){
            startCheckingAngle = false;

            engage.EngagePTO();
            GlobalQueues.PutLiftTask(0,30,TaskEnums.OVER_BAR_HEIGHT);
            GlobalQueues.PutOuttakeTask(45,280,50,355,TaskEnums.SAMPLE_STORAGE_POSITION);

            currControllerTask = TaskEnumsOverall.NEUTRE_POSITION;
        }
        //de adaugat un unghi sa nu traga in jos degeaba
        if(currControllerTask == TaskEnumsOverall.DISENGAGE_AND_SET_LIFT_TO_POS_OVER_SECOND_BAR_WHEELIE_RETRACT && fullLift.CurrTaskDoneness()){
            startCheckingAngle = true;
            engage.DisengagePTO();
            GlobalQueues.PutLiftTask(650,30,TaskEnums.OVER_BAR_HEIGHT);
            wheelie.CloseWheelie();

            currControllerTask = TaskEnumsOverall.ENGAGE_AND_DRAGDOWN_TILL_POS_ZERO_2;
        }
        if(currControllerTask == TaskEnumsOverall.ENGAGE_AND_DRAGDOWN_TILL_POS_ZERO_1 && wheelie.CurrTaskDoneness() && fullLift.CurrTaskDoneness()){
            engage.EngagePTO();
            GlobalQueues.PutLiftTask(-20,30,TaskEnums.PULL_DOWN);

            currControllerTask = TaskEnumsOverall.DISENGAGE_AND_SET_LIFT_TO_POS_OVER_SECOND_BAR_WHEELIE_RETRACT;
        }

        if(prevgm1.triangle != gm1.triangle && gm1.triangle){

            currControllerTask = TaskEnumsOverall.WHEELIE_LIFT_TO_FIRST_BAR;
            wheelie.OpenWheelie();
            GlobalQueues.PutLiftTask(400,30,TaskEnums.OVER_BAR_HEIGHT);
            GlobalQueues.PutOuttakeTask(340,280,50,355,TaskEnums.SAMPLE_STORAGE_POSITION);

            currControllerTask = TaskEnumsOverall.ENGAGE_AND_DRAGDOWN_TILL_POS_ZERO_1;
        }

        prevgm1.copy(gm1);
        prevgm2.copy(gm2);
    }
}
