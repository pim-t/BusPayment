import dataModels.Journey;
import dataModels.Tap;
import org.junit.jupiter.api.Test;
import tripServices.TripCalculator;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class TripCalculatorTest {
    Tap tap1 = new Tap("1","22-01-2018 13:00:00","Stop1","Company1","Bus37","5500005555555559", "ON");
    Tap tap2 = new Tap("2","22-01-2018 13:05:00", "Stop2", "Company1", "Bus37", "5500005555555559","OFF");
    Tap tap3 = new Tap("1","22-01-2018 13:00:00","Stop1","Company1","Bus37","5500005555555559", "OFF");

    @Test
    void tripDuration() throws ParseException {

        assertAll(() -> assertEquals(300, TripCalculator.tripDuration(tap1, tap2)),
                () -> assertEquals(0, TripCalculator.tripDuration(tap1, tap3))
//                () -> assertEquals(null, tripServices.TripCalculator.tripDuration(null, null)))
        );

    }

    @Test
    void journeyStatus() {

        assertAll( () -> assertEquals(Journey.Status.COMPLETE, TripCalculator.journeyStatus(tap1, tap2)),
                () -> assertEquals(Journey.Status.INCOMPLETE, TripCalculator.journeyStatus(tap1, null)),
                () -> assertEquals(Journey.Status.CANCELLED, TripCalculator.journeyStatus(tap1, tap3))
        );
    }

    @Test
    void tripCost() {
        assertAll( () -> assertEquals(3.35, TripCalculator.tripCost(tap1, tap2)),
                () -> assertEquals(7.30, TripCalculator.tripCost(tap1, null)),
                () -> assertEquals(0, TripCalculator.tripCost(tap1, tap3))
        );
    }
}