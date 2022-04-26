package tripServices;

import dataModels.Status;
import dataModels.Tap;
import dataModels.Trip;
import org.junit.jupiter.api.Test;
import tripServices.TripCalculator;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TripCalculatorTest {
    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    Tap tap1 = new Tap("1", LocalDateTime.parse("22-01-2018 13:00:00", df),  "ON","Stop1", "Company1", "Bus37", "5500005555555559");
    Tap tap2 = new Tap("2", LocalDateTime.parse("22-01-2018 13:05:00", df), "OFF", "Stop2", "Company1", "Bus37", "5500005555555559");
    Tap tap3 = new Tap("3", LocalDateTime.parse("22-01-2018 13:00:00", df), "OFF", "Stop1", "Company1", "Bus37", "5500005555555559");


    @Test
    void tripDuration() throws ParseException {
        assertAll(
                () -> assertEquals(300, TripCalculator.tripDuration(tap1, tap2)), // completed trip
                () -> assertEquals(0, TripCalculator.tripDuration(tap1, tap3)), // cancelled trip
                () -> assertEquals(0, tripServices.TripCalculator.tripDuration(tap1, null)) // incomplete trip
        );
    }

    @Test
    void journeyStatus() {
        assertAll(
                () -> assertEquals(Status.COMPLETE, TripCalculator.journeyStatus(tap1, tap2)),
                () -> assertEquals(Status.INCOMPLETE, TripCalculator.journeyStatus(tap1, null)),
                () -> assertEquals(Status.CANCELLED, TripCalculator.journeyStatus(tap1, tap3))
        );
    }

    @Test
    void tripCost() {
        assertAll(
                () -> assertEquals(3.25, TripCalculator.tripCost(tap1, tap2)),
                () -> assertEquals(7.30, TripCalculator.tripCost(tap1, null)),
                () -> assertEquals(0, TripCalculator.tripCost(tap1, tap3))
        );
    }
}
