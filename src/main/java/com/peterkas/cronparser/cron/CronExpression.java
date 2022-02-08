package com.peterkas.cronparser.cron;

import java.time.temporal.ChronoField;

public class CronExpression {

    static final int NUM_OF_FIELDS = 6;

    private final String expr;
    private final CronField minutes;
    private final CronField hours;
    private final CronField dayOfMonths;
    private final CronField months;
    private final CronField dayOfWeeks;
    private final String command;

    private CronExpression(CronField minutes, CronField hours, CronField dayOfMonths, CronField months, CronField dayOfWeeks, String command, String expr) {
        this.minutes = minutes;
        this.hours = hours;
        this.dayOfMonths = dayOfMonths;
        this.months = months;
        this.dayOfWeeks = dayOfWeeks;
        this.command = command;
        this.expr = expr;
    }

    public String getExpr() {
        return expr;
    }

    public CronField getMinutes() {
        return minutes;
    }

    public CronField getHours() {
        return hours;
    }

    public CronField getDayOfMonths() {
        return dayOfMonths;
    }

    public CronField getMonths() {
        return months;
    }

    public CronField getDayOfWeeks() {
        return dayOfWeeks;
    }

    public String getCommand() {
        return command;
    }

    public static CronExpression parse(String expression) {
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException("Expression string must not be empty");
        }

        String[] fields = expression.split("\\s+");
        if (fields.length != NUM_OF_FIELDS) {
            throw new IllegalArgumentException(String.format(
                    "Invalid Cron expression \"%s\", expected %d fields but found %d", expression, NUM_OF_FIELDS, fields.length));
        }
        try {
            CronField minutes = CronField.parse(fields[0], ChronoField.MINUTE_OF_HOUR);
            CronField hours = CronField.parse(fields[1], ChronoField.HOUR_OF_DAY);
            CronField daysOfMonth = CronField.parse(fields[2], ChronoField.DAY_OF_MONTH);
            CronField months = CronField.parse(fields[3], ChronoField.MONTH_OF_YEAR);
            CronField daysOfWeek = CronField.parse(fields[4], ChronoField.DAY_OF_WEEK);
            String command = fields[5];

            return new CronExpression(minutes, hours, daysOfMonth, months, daysOfWeek, command, expression);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage() + " in cron expression \"" + expression + "\"", ex);
        }
    }

    @Override
    public String toString() {
        return this.expr;
    }
}
