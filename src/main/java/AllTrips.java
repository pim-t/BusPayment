import csvReader.CsvReader;
import dataModels.Journey;
import dataModels.Tap;
import dataModels.TripDetail;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllTrips {

    // Read the csv
    private static final CsvReader csvReader = null;

    //write the csv file

    public AllTrips(CsvReader csvReader) {

    }

    public static void main(String[] args) throws Exception {
        File input = new File("src/main/java/input.csv");
        File output = new File("output.json");

        allTrips();

    }

    public static void allTrips() throws IOException, ParseException {
        File input = new File("src/main/java/input.csv");

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


            // read through each element of the trip, now calculate the cost of the trip



            for (String key: allCustomers.keySet()) {
                for (int i = 0; i < allCustomers.get(key).toArray().length -1 ; i++  ) {
                    if (tapPair(allCustomers.get(key).get(i), allCustomers.get(key).get(i + 1))) {
                        // this determines a tap pair.

                        BigDecimal cost = TripCalculator.tripCost(allCustomers.get(key).get(i), allCustomers.get(key).get(i+1));
//                        System.out.println(cost);

                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                        Date tap1 = format.parse(allCustomers.get(key).get(i).getDate());
                        Date tap2 = format.parse(allCustomers.get(key).get(i+1).getDate());

                        long duration = TripCalculator.tripDuration(tap1, tap2);

                        String startTime = allCustomers.get(key).get(i).getDate();
                        String endTime = allCustomers.get(key).get(i+1).getDate();
                        long durationSecs = duration;
                        String fromStopId = allCustomers.get(key).get(i).getStopId();
                        String toStopId = allCustomers.get(key).get(i+1).getStopId();
                        BigDecimal chargeAmount = cost;
                        String companyId = allCustomers.get(key).get(i).getCompanyId();
                        String busId = allCustomers.get(key).get(i).getBusId();
                        String PAN = key;
                        Journey.Status status = Journey.Status.COMPLETE;

                        TripDetail trip = new TripDetail(startTime,endTime,durationSecs,fromStopId,toStopId, chargeAmount, companyId, busId, companyId, status);


                    }
                }
            }






    }

    public static boolean tapPair(Tap tapOn, Tap tapOff) {

        if (tapOn.getTapType().equals("ON") & tapOff.getTapType().equals("OFF")) {
            return true;
        }
        return false;

    }



}
