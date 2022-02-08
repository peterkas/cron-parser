package com.peterkas.cronparser.cron;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CronExpressionTest {

    @Test
    public void emptyExpressionThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> CronExpression.parse(""));
    }

    @Test
    public void expressionShouldContainOnlyExpectedFields() {
        assertThrows(IllegalArgumentException.class, () -> CronExpression.parse("* * *"));
        assertThrows(IllegalArgumentException.class, () -> CronExpression.parse("* * * * *"));
        assertThrows(IllegalArgumentException.class, () -> CronExpression.parse("* * * * * command extraField"));
        assertDoesNotThrow(() -> CronExpression.parse("* * * * * command"));
    }

    @Test
    public void parseMinutesIncremental() {
        CronExpression cronExpression = CronExpression.parse("*/15 * * * * command");
        List<Integer> cronIntValues = convertCronValuesToInt(cronExpression.getMinutes().getValues());
        assertEquals(Arrays.asList(0,15,30,45), cronIntValues);
    }

    @Test
    public void parseHoursCommaSeparatedValues() {
        CronExpression cronExpression = CronExpression.parse("* 4,2,6 * * * command");
        List<Integer> cronIntValues = convertCronValuesToInt(cronExpression.getHours().getValues());
        assertEquals(Arrays.asList(2,4,6), cronIntValues);
    }

    @Test
    public void parseDayOfMonthFixValue() {
        CronExpression cronExpression = CronExpression.parse("* * 15 * * command");
        List<Integer> cronIntValues = convertCronValuesToInt(cronExpression.getDayOfMonths().getValues());
        assertEquals(Arrays.asList(15), cronIntValues);
    }

    @Test
    public void parseMonthFullRangeValue() {
        CronExpression cronExpression = CronExpression.parse("* * * * * command");
        List<Integer> cronIntValues = convertCronValuesToInt(cronExpression.getMonths().getValues());
        assertEquals(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12), cronIntValues);
    }

    @Test
    public void parseDayOfWeekRangeValue() {
        CronExpression cronExpression = CronExpression.parse("* * * * 1-5 command");
        List<Integer> cronIntValues = convertCronValuesToInt(cronExpression.getDayOfWeeks().getValues());
        assertEquals(Arrays.asList(1,2,3,4,5), cronIntValues);
    }

    private List<Integer> convertCronValuesToInt(List<CronValue> cronValues) {
        List<Integer> intValues = new ArrayList<>(cronValues.size());
        cronValues.forEach(x -> intValues.add(x.getVal()));
        return intValues;
    }

}
