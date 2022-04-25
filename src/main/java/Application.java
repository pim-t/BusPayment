import csvReader.CsvReader;
import dataModels.Tap;

import java.io.File;
import java.util.List;
import java.util.Map;

import static csvReader.CsvReader.writeCSV;
import static tripServices.AllTrips.allTrips;
import static tripServices.AllTrips.tripCreator;

public class Application {

    public static void main(String[] args) throws Exception {
        File input = new File("/home/pim/Documents/GitHub/BusPayment/BusPayment/src/main/java/input.csv");

        Map<String, List<Tap>> allCustomers = allTrips(input);
//        tripCreator(allCustomers);
        writeCSV(tripCreator(allCustomers));

    }
}
