package org.firstinspires.ftc.teamcode.IntoTheDeep.WraperClasses.HelperClasses;

import android.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

public class Colors {

    public enum ColorType{
        BLUE,
        RED,
        YELLOW,
        NONE
    }

    public static class Color{
        double R,G,B;
        public Color(double R,double G,double B){
            this.R = R;
            this.G = G;
            this.B = B;
        }

        public double getDistance(Color a){
            return Math.sqrt((a.R - this.R)*(a.R - this.R)+
                    (a.G - this.G)*(a.G - this.G)+
                    (a.B - this.B)*(a.B - this.B));
        }
    }

    private static final Vector<Pair<Color,ColorType>> Colors = new Vector<Pair<Color,ColorType>>(Arrays.asList(
            new Pair<>(new Color(65.9,124.75,255),ColorType.BLUE),
            new Pair<>(new Color(255,133.06,58.17),ColorType.RED),
            new Pair<>(new Color(187.5,255,57.65),ColorType.YELLOW)
    ));

    public static double color_error_threshold = 50;
    public static double AcceptedDistance = 35;

    public static ColorType getColorFromRGB(Color detectedColor,double DetectedDistance){

        if(DetectedDistance > AcceptedDistance)
            return ColorType.NONE;

        for(int i=0;i<Colors.size();i++){
            Pair<Color,ColorType> color = Colors.get(i);
            if(color.first.getDistance(detectedColor) <= color_error_threshold)
                return color.second;
        }

        return ColorType.NONE;
    }

}
