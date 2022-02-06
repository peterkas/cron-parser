package com.peterkas.cronparser;

import java.time.temporal.ChronoField;

public class CronExpression {

    static final int EXPECTED_FIELDS = 6;

    private final String expr;
    private final TimeField minute;
    private final TimeField hour;
    private final TimeField dayOfMonth;
    private final TimeField month;
    private final TimeField dayOfWeek;
    private final String command;

    private CronExpression(TimeField minute, TimeField hour, TimeField dayOfMonth, TimeField month, TimeField dayOfWeek, String command, String expr) {
        this.minute = minute;
        this.hour = hour;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.command = command;
        this.expr = expr;
    }

    @Override
    public String toString() {
        return this.expr;
    }

    public static CronExpression parse(String expression) {
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException("Expression string must not be empty");
        }

        String[] fields = expression.split("\\s+");
        if (fields.length != EXPECTED_FIELDS) {
            throw new IllegalArgumentException(String.format(
                    "Invalid Cron expression \"%s\", expected %d fields but found %d", expression, EXPECTED_FIELDS, fields.length));
        }
        try {
            TimeField minutes = TimeField.parseField(fields[0], ChronoField.MINUTE_OF_HOUR);
            TimeField hours = TimeField.parseField(fields[1], ChronoField.HOUR_OF_DAY);
            TimeField daysOfMonth = TimeField.parseField(fields[2], ChronoField.DAY_OF_MONTH);
            TimeField months = TimeField.parseField(fields[3], ChronoField.MONTH_OF_YEAR);
            TimeField daysOfWeek = TimeField.parseField(fields[4], ChronoField.DAY_OF_WEEK);
            String command = fields[5];

            return new CronExpression(minutes, hours, daysOfMonth, months, daysOfWeek, expression, command);
        } catch (IllegalArgumentException ex) {
            String msg = ex.getMessage() + " in cron expression \"" + expression + "\"";
            throw new IllegalArgumentException(msg, ex);
        }
    }

    static class TimeField {
        private static TimeField parseField(String value, ChronoField type) {
            throw new UnsupportedOperationException("Method not implemented yet");
        }
    }
}
