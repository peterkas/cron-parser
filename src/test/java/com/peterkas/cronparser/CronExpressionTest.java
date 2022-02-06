package com.peterkas.cronparser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CronExpressionTest {

    @Test
    public void emptyExpressionThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> CronExpression.parse(""));
    }

    @Test
    public void expressionShouldContainExpectedFields() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> CronExpression.parse("* * *"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> CronExpression.parse("* * * * *"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> CronExpression.parse("* * * * * command extraField"));
        Assertions.assertDoesNotThrow(() -> CronExpression.parse("* * * * * command"));
    }
}
