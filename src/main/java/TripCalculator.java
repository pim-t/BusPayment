import java.math.BigDecimal;
import java.util.Date;

import dataModels.Journey;
import dataModels.Tap;
import org.jetbrains.annotations.Nullable;


public class TripCalculator {


    public static long tripDuration(Date tapOnTime, Date tapOffTime) {
        return (tapOffTime.getTime() - tapOnTime.getTime())/1000;
    }

    public static Journey.Status journeyStatus(Tap tapOn, Tap tapOff) {
        if (tapOn.getId() == tapOff.getId()) {
            return Journey.Status.CANCELLED;
        }
        if (tapOff.getId() == null) {
            return  Journey.Status.INCOMPLETE;
        }
        return Journey.Status.COMPLETE;
    }


    public static BigDecimal tripCost(Tap tapOne, @Nullable Tap tapTwo) {

        if (tapTwo == null) {
            if (tapOne.getStopId().equals("Stop1") || tapOne.getStopId().equals("Stop3")) {
                return BigDecimal.valueOf(7.30);
            }
            if (tapOne.getStopId().equals("Stop2")) {
                return BigDecimal.valueOf(5.50);
            }

        } else {

            if (tapTwo.getStopId().equals("Stop2") && tapOne.getStopId().equals("Stop1")) {
                return BigDecimal.valueOf(3.35);
            }
            if (tapTwo.getStopId().equals("Stop3") && tapOne.getStopId().equals("Stop1")) {
                return BigDecimal.valueOf(7.30);
            }
            if (tapTwo.getStopId().equals("Stop2") && tapOne.getStopId().equals("Stop3")) {
                return BigDecimal.valueOf(5.50);
            }
        }
        return null;
    }




}
