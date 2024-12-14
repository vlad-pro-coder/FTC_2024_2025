package org.firstinspires.ftc.teamcode.Alejandra;

import com.qualcomm.robotcore.hardware.Gamepad;

enum OutinStates{
    PUTCONEACTION,
    DEFAULTACTION,
        }

public class TakeAndPut {

    OutinStates currState = OutinStates.DEFAULTACTION;
    OutInTake currOutinTake;

    public TakeAndPut(OutInTake outInTake){
        currOutinTake = outInTake;
    }

    public void actions(Gamepad gamepad,Gamepad prevpad){

        switch (currState){

            case DEFAULTACTION:
                currOutinTake.setPos(0,0,0);
                if(!prevpad.cross && gamepad.cross) {
                    currState = OutinStates.PUTCONEACTION;
                }
                break;
            case PUTCONEACTION:
                currOutinTake.setPos(1,1,1);
                if(!prevpad.cross && gamepad.cross) {
                    currState = OutinStates.DEFAULTACTION;
                }
                break;
        }
    }

}
