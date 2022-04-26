package tripServices;

import dataModels.Status;
import dataModels.Tap;
import org.jetbrains.annotations.Nullable;

import java.time.temporal.ChronoUnit;

public class TripCalculator {

    public static long tripDuration(Tap tapOn, @Nullable Tap tapOff) {
        if (tapOff == null) {
            return 0;
        }
        return ChronoUnit.SECONDS.between(tapOn.dateTime(), tapOff.dateTime());
    }

    public static Status journeyStatus(Tap tapOn, @Nullable Tap tapOff) {
        if (tapOff == null || tapOn == null) {
            return Status.INCOMPLETE;
        }
        if (tapOn.stopId().equals(tapOff.stopId())) {
            return Status.CANCELLED;
        }
        return Status.COMPLETE;
    }

    public static double tripCost(Tap tapOne, @Nullable Tap tapTwo) {

        if (tapTwo == null) {
            if (tapOne.stopId().equals("Stop1") || tapOne.stopId().equals("Stop3")) {
                return 7.30;
            }
            if (tapOne.stopId().equals("Stop2")) {
                return 5.50;
            }

        } else {

            if (tapTwo.stopId().equals("Stop2") && tapOne.stopId().equals("Stop1")) {
                return 3.25;
            }
            if (tapTwo.stopId().equals("Stop3") && tapOne.stopId().equals("Stop1")) {
                return 7.30;
            }
            if (tapTwo.stopId().equals("Stop2") && tapOne.stopId().equals("Stop3")) {
                return 5.50;
            }
        }
        return 0;
    }
}
