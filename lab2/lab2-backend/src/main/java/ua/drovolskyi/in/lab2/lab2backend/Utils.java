package ua.drovolskyi.in.lab2.lab2backend;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Utils {
    private static Clock clock = Clock.systemDefaultZone();

    /**
     * Method to get current year.
     */
    public static int getCurrentYear(){
        return LocalDateTime.now(clock).getYear();
    }

    public static LocalDate getCurrentDate(){
        return LocalDate.now(clock);
    }
}