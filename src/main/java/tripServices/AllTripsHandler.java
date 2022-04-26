package tripServices;

import dataModels.Status;
import dataModels.Tap;
import dataModels.Trip;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllTripsHandler {
    public static List<Trip> convertTapsToTrips(List<Tap> taps) {
        // map of open trips by PAN
        HashMap<String, Tap> mapOfPanToOpenTrip = new HashMap<>();
        // completed and cancelled trips
        List<Trip> allTripTypes = new ArrayList<>();
        // go through all the taps
        for (Tap currentTap : taps) {
            // if the map contains the PAN of this tap
            if (mapOfPanToOpenTrip.containsKey(currentTap.pan())) {
                // find the matching tap pair, if it exists
                Tap currentTapOn = mapOfPanToOpenTrip.get(currentTap.pan());
                if (isTapPair(currentTapOn, currentTap)) {
                    // and add current tap to complete trips
                    allTripTypes.add(calculateTripFromTapPair(currentTapOn, currentTap));
                    // if it exists, remove the current tap from open trips
                    mapOfPanToOpenTrip.remove(currentTap.pan());
                } else {
                    mapOfPanToOpenTrip.put(currentTap.pan(), currentTap);
                }

            } else {
                mapOfPanToOpenTrip.put(currentTap.pan(), currentTap);
            }

        }
        for (Tap incompleteTap : mapOfPanToOpenTrip.values()) {
            if (incompleteTap != null) {
                allTripTypes.add(calculateTripFromTapPair(incompleteTap, null));
            }
        }

        return allTripTypes;
    }

    public static Trip calculateTripFromTapPair(Tap tapOn, @Nullable Tap tapOff) {
        Status status = TripCalculator.journeyStatus(tapOn, tapOff);
        double cost = TripCalculator.tripCost(tapOn, tapOff);
        long durationSecs = TripCalculator.tripDuration(tapOn, tapOff);
        LocalDateTime startTime = tapOn.dateTime();
        LocalDateTime endTime = null;
        String toStopId = "INVALID";
        String busId = tapOn.busId();
        if (tapOff != null) {
            endTime = tapOff.dateTime();
            toStopId = tapOff.stopId();
            busId = tapOff.busId();
        }
        String fromStopId = tapOn.stopId();
        String companyId = tapOn.companyId();
        return new Trip(startTime, endTime, durationSecs, fromStopId, toStopId, cost, companyId, busId, tapOn.pan(), status);
    }

    public static boolean isTapPair(Tap tapOn, Tap tapOff) {
        return tapOn.tapType().equals("ON") & tapOff.tapType().equals("OFF");
    }
}
