package com.peterkas.cronparser.cron;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CronExpressionTest extends CronBaseTest{

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
    public void parseValidCronExpression() {
        CronExpression cronExpression = CronExpression.parse("*/15 0 1,15 * 1-5 command");

        List<Integer> minuteValues = convertCronValuesToInt(cronExpression.getMinutes().getValues());
        List<Integer> hourValues = convertCronValuesToInt(cronExpression.getHours().getValues());
        List<Integer> dayOfMonthValues = convertCronValuesToInt(cronExpression.getDayOfMonths().getValues());
        List<Integer> monthValues = convertCronValuesToInt(cronExpression.getMonths().getValues());
        List<Integer> dayOfWeekValues = convertCronValuesToInt(cronExpression.getDayOfWeeks().getValues());

        assertEquals(Arrays.asList(0,15,30,45),                 minuteValues);
        assertEquals(Arrays.asList(0),                          hourValues);
        assertEquals(Arrays.asList(1,15),                       dayOfMonthValues);
        assertEquals(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12), monthValues);
        assertEquals(Arrays.asList(1,2,3,4,5),                  dayOfWeekValues);
    }
}
