package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses;

import com.qualcomm.hardware.broadcom.BroadcomColorSensor;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchSimple;

import com.qualcomm.robotcore.util.Range;

import org.apache.commons.math3.exception.NotANumberException;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class ColorSensor{

    public class ColorsPacket{
        private double R,G,B,A;
        private double Dist;

        public ColorsPacket(){
            R = G = B = 0;
            Dist = 0;
        }
        public String printColor() {
            return "red color " + Double.toString(R) + '\n' + "green color " + Double.toString(G) + '\n' + "blue color " + Double.toString(B);
        }
    }

    private double timeDistance,timeRGB;
    private double freq = 20;
    private ColorsPacket RGB = new ColorsPacket();//cut resolution for display
    private ColorsPacket lastRead = new ColorsPacket();
    private double lowPassFilter = 1;//pondere pentru noul read fata de vechiul read
                                    //pentru val = 1 noul read este absolut vechiul read neinfluentandu-l deloc
    RevColorSensorV3 sensor;
    DistanceUnit unit;

    public ColorSensor(HardwareMap hardwareMap ,String name) {
        sensor = hardwareMap.get(RevColorSensorV3.class,name);
        timeDistance = System.currentTimeMillis();//last distance read
        timeRGB = System.currentTimeMillis();//last rgb read
        setFreqToUpdate(10);
        ChangeDistanceMeasurements(DistanceUnit.MM);
    }


    private void setFreqToUpdate(double value) {
        this.freq = value;
    }

    public String getRawColors(){
        try {
            if (System.currentTimeMillis() - timeRGB > freq) {
                lastRead.G = (int) (sensor.green() * this.lowPassFilter + lastRead.G * (1 - lowPassFilter));
                lastRead.R = (int) (sensor.red() * this.lowPassFilter + lastRead.R * (1 - lowPassFilter));
                lastRead.B = (int) (sensor.blue() * this.lowPassFilter + lastRead.B * (1 - lowPassFilter));
                lastRead.A = Math.max(lastRead.G, Math.max(lastRead.R, lastRead.B));

                //creare range intre 0-1
                RGB.R = Range.clip(lastRead.R / lastRead.A * 255, 0, 255);
                RGB.G = Range.clip(lastRead.G / lastRead.A * 255, 0, 255);
                RGB.B = Range.clip(lastRead.B / lastRead.A * 255, 0, 255);

                timeRGB = System.currentTimeMillis();
            }
        } catch (Exception ignored){

        }

        return RGB.printColor();
    }

    public void ChangeDistanceMeasurements(DistanceUnit unit){
        this.unit = unit;
    }

    public double getDistance(){
        return sensor.getDistance(this.unit);
    }

    public Colors.ColorType getColorSeenBySensor(){
        try {
            if (System.currentTimeMillis() - timeRGB > freq) {
                lastRead.G = (int) (sensor.green() * this.lowPassFilter + lastRead.G * (1 - lowPassFilter));
                lastRead.R = (int) (sensor.red() * this.lowPassFilter + lastRead.R * (1 - lowPassFilter));
                lastRead.B = (int) (sensor.blue() * this.lowPassFilter + lastRead.B * (1 - lowPassFilter));
                lastRead.A = Math.max(lastRead.G, Math.max(lastRead.R, lastRead.B));

                //creare range intre 0-1
                RGB.R = Range.clip(lastRead.R / lastRead.A * 255, 0, 255);
                RGB.G = Range.clip(lastRead.G / lastRead.A * 255, 0, 255);
                RGB.B = Range.clip(lastRead.B / lastRead.A * 255, 0, 255);

                timeRGB = System.currentTimeMillis();
            }
        } catch (Exception ignored){

        }
        return Colors.getColorFromRGB(new Colors.Color(RGB.R, RGB.G, RGB.B),getDistance());

    }

    public boolean isOperational(){
        return !(Double.isNaN(RGB.R) || Double.isNaN(RGB.G) || Double.isNaN(RGB.B) || (RGB.R == 0 && RGB.G == 0 && RGB.B == 0));
    }
}
