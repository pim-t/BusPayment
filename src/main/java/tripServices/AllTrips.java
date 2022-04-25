package tripServices;

import csvReader.CsvReader;
import dataModels.Journey;
import dataModels.Tap;
import dataModels.TripDetail;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllTrips {


    public static Map<String, List<Tap>>   allTrips(File input) throws IOException, ParseException {
        CsvReader csvReader = null;
        List<Map<?, ?>> inputData = csvReader.readObjectsFromCsv(input);


        // a list of taps
        Map<String, List<Tap>> allCustomers = new HashMap<>();

        for (Map<?, ?> inputD : inputData) {
            String id = (String) inputD.get("ID");
            String date = (String) inputD.get("DateTimeUTC");
            String tapType = (String) inputD.get("TapType");
            String stopId = (String) inputD.get("StopID");
            String companyId = (String) inputD.get("CompanyID");
            String busId = (String) inputD.get("BusID");
            String PAN = (String) inputD.get("PAN");

            Tap initTap = new Tap(id, date, stopId, companyId, busId, PAN, tapType);

            if (allCustomers.get(PAN) == null) {
                allCustomers.put(PAN, new ArrayList<>());
            }


            allCustomers.get(PAN).add(initTap);

        }
        return allCustomers;
    }


    public static void tripCreator(Map<String, List<Tap>> allCustomers) throws ParseException, IOException {
            File tempFile = new File("trips.csv");
            for (String key: allCustomers.keySet()) {
                TripDetail currentTrip = null;
                for (int i = 0; i < allCustomers.get(key).toArray().length-1 ; i=+2  ) {
                    if ((i+1) < allCustomers.get(key).toArray().length ) {
                        if (tapPair(allCustomers.get(key).get(i), allCustomers.get(key).get(i + 1))) {
                           currentTrip = tripPair(allCustomers.get(key).get(i), allCustomers.get(key).get(i + 1), key);
                        }
                    } else {
                        currentTrip = tripPair(allCustomers.get(key).get(i), null, key);


                    }

                    CsvReader.writeCSV(currentTrip,tempFile);
                    // if false, then there is no tap pair


                }
            }

    }


    public static TripDetail tripPair(Tap tapOn, @Nullable Tap tapOff, String key) throws ParseException {
        Journey.Status status = TripCalculator.journeyStatus(tapOn, tapOff);
        double cost = TripCalculator.tripCost(tapOn, tapOff);

        long duration = TripCalculator.tripDuration(tapOn, tapOff);

        String startTime = tapOn.getDate();
        String endTime = "0";
        String toStopId = "INVALID";
        String busId = tapOn.getBusId();

        if (tapOff != null) {
            endTime = tapOff.getDate();
            toStopId = tapOff.getStopId();
            busId = tapOff.getBusId();
        }
        long durationSecs = duration;
        String fromStopId = tapOn.getStopId();

        double chargeAmount = cost;
        String companyId = tapOn.getCompanyId();

        String PAN = key;

        TripDetail trip = new TripDetail(startTime, endTime, durationSecs, fromStopId, toStopId, chargeAmount, companyId, busId, PAN, status);

        return trip;

    }

    public static boolean tapPair(Tap tapOn, Tap tapOff) {

        if (tapOn.getTapType().equals("ON") & tapOff.getTapType().equals("OFF")) {
            return true;
        }
        return false;

    }



}
