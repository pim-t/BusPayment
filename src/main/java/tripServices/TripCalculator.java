package tripServices;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dataModels.Journey;
import dataModels.Tap;
import org.jetbrains.annotations.Nullable;


public class TripCalculator {


    public static long tripDuration(Tap tapOn, @Nullable Tap tapOff) throws ParseException {
        if (tapOff == null) {
            return 0;
        }
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date tap1 = format.parse(tapOn.getDate());
        Date tap2 = format.parse(tapOff.getDate());
        return (tap2.getTime() - tap1.getTime())/1000;
    }

    public static Journey.Status journeyStatus(Tap tapOn, @Nullable Tap tapOff) {
        if (tapOff == null || tapOn == null) {
            return  Journey.Status.INCOMPLETE;
        }
        if (tapOn.getId() == tapOff.getId()) {
            return Journey.Status.CANCELLED;
        }
        return Journey.Status.COMPLETE;
    }


    public static double tripCost(Tap tapOne, @Nullable Tap tapTwo) {

        if (tapTwo == null) {
            if (tapOne.getStopId().equals("Stop1") || tapOne.getStopId().equals("Stop3")) {
                return 7.30;
            }
            if (tapOne.getStopId().equals("Stop2")) {
                return 5.50;
            }

        } else {

            if (tapTwo.getStopId().equals("Stop2") && tapOne.getStopId().equals("Stop1")) {
                return 3.35;
            }
            if (tapTwo.getStopId().equals("Stop3") && tapOne.getStopId().equals("Stop1")) {
                return 7.30;
            }
            if (tapTwo.getStopId().equals("Stop2") && tapOne.getStopId().equals("Stop3")) {
                return 5.50;
            }
        }
        return 0;
    }




}
