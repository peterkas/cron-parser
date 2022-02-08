package com.peterkas.cronparser.cron;

import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CronFieldTest extends CronBaseTest{

    @Test
    public void parseMinutesIncremental() {
        CronField cronField = CronField.parse("*/9", ChronoField.MINUTE_OF_HOUR);
        List<Integer> cronIntValues = convertCronValuesToInt(cronField.getValues());
        assertEquals(Arrays.asList(0,9,18,27,36,45,54), cronIntValues);
    }

    @Test
    public void parseHoursCommaSeparatedValues() {
        CronField cronField = CronField.parse("4,2,6,13", ChronoField.HOUR_OF_DAY);
        List<Integer> cronIntValues = convertCronValuesToInt(cronField.getValues());
        assertEquals(Arrays.asList(2,4,6,13), cronIntValues);
    }

    @Test
    public void parseDayOfMonthFixValue() {
        CronField cronField = CronField.parse("15", ChronoField.DAY_OF_MONTH);
        List<Integer> cronIntValues = convertCronValuesToInt(cronField.getValues());
        assertEquals(Arrays.asList(15), cronIntValues);
    }

    @Test
    public void parseMonthFullRangeValue() {
        CronField cronField = CronField.parse("*", ChronoField.MONTH_OF_YEAR);
        List<Integer> cronIntValues = convertCronValuesToInt(cronField.getValues());
        assertEquals(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12), cronIntValues);
    }

    @Test
    public void parseDayOfWeekRangeValue() {
        CronField cronField = CronField.parse("1-5", ChronoField.DAY_OF_WEEK);
        List<Integer> cronIntValues = convertCronValuesToInt(cronField.getValues());
        assertEquals(Arrays.asList(1,2,3,4,5), cronIntValues);
    }

    @Test
    public void parseInvalidRangeValue() {
        assertThrows(IllegalArgumentException.class, () -> CronField.parse("*/-1", ChronoField.HOUR_OF_DAY));
        assertThrows(IllegalArgumentException.class, () -> CronField.parse("-2-2", ChronoField.HOUR_OF_DAY));
        assertThrows(IllegalArgumentException.class, () -> CronField.parse("-1", ChronoField.HOUR_OF_DAY));
    }
}
