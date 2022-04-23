import java.math.BigDecimal;
import java.util.Date;

import dataModels.Journey;
import dataModels.Tap;


public class TripCalculator {


    public static long tripDuration(Date tapOnTime, Date tapOffTime) {
        return (tapOffTime.getTime() - tapOnTime.getTime())/1000;
    }

    public Journey.Status journeyStatus(Tap tapOn, Tap tapOff) {
        if (tapOn.getId() == tapOff.getId()) {
            return Journey.Status.CANCELLED;
        }
        if (tapOff.getId() == null) {
            return  Journey.Status.INCOMPLETE;
        }
        return Journey.Status.COMPLETE;
    }


    public static BigDecimal tripCost(Tap tapOne, Tap tapTwo) {

        if (tapTwo.getStopId().equals("Stop2") & tapOne.getStopId().equals("Stop1")) {
            return BigDecimal.valueOf(3.35);
        }
        if (tapTwo.getStopId().equals("Stop3") & tapOne.getStopId().equals("Stop1")) {
            return BigDecimal.valueOf(7.30);
        }
        if (tapTwo.getStopId().equals("Stop2") & tapOne.getStopId().equals("Stop3")) {
            return BigDecimal.valueOf(5.50);
        }
        return null;
    }






}
