package be.tempsdor.tempsdor.utils;

import java.util.Random;

public class Utils {

    public static  int randomIntegerBetweenMinMax(int min, int max) {
        return new Random().nextInt(max-min) + min;
    }
}
