import dataModels.Tap;
import io.CsvIO;
import tripServices.AllTrips;

import java.io.File;
import java.util.List;

public class Application {
    public static void main(String[] args) throws Exception {
        File inputFile = new File("BusPayment/input.csv");
        File outputFile = new File("BusPayment/output.csv");

        List<Tap> taps = CsvIO.readTapsFromCsv(inputFile);
        var trips = AllTrips.convertTapsToTrips(taps);
        CsvIO.writeTripsToCsv(outputFile, trips);
    }
}
