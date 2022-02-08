package com.peterkas.cronparser.printer;

import com.peterkas.cronparser.cron.CronExpression;
import com.peterkas.cronparser.cron.CronValue;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CronExpressionConsolePrinter implements  CronExpressionPrinter{

    @Override
    public void print(CronExpression cronExpression) {
        System.out.println();
        System.out.println(String.format("%-14s%s", "expression", cronExpression.getExpr()));
        System.out.println(String.format("%-14s%s", "minute", formatValues(cronExpression.getMinutes().getValues())));
        System.out.println(String.format("%-14s%s", "hour", formatValues(cronExpression.getHours().getValues())));
        System.out.println(String.format("%-14s%s", "day of month", formatValues(cronExpression.getDayOfMonths().getValues())));
        System.out.println(String.format("%-14s%s", "month", formatValues(cronExpression.getMonths().getValues())));
        System.out.println(String.format("%-14s%s", "day of week", formatValues(cronExpression.getDayOfWeeks().getValues())));
        System.out.println(String.format("%-14s%s", "command", cronExpression.getCommand()));
        System.out.println();
    }

    public String formatValues(List<CronValue> values) {
        StringBuilder sb = new StringBuilder();
        values.forEach(x -> sb.append(String.format("%s ", x.getVal())));
        return sb.toString().trim();
    }
}
