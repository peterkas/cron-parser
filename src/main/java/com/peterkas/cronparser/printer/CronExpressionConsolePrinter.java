package com.peterkas.cronparser.printer;

import com.peterkas.cronparser.CronExpression;
import org.springframework.stereotype.Component;

@Component
public class CronExpressionConsolePrinter implements  CronExpressionPrinter{

    @Override
    public void print(CronExpression cronExpression) {
        System.out.println(cronExpression.toString());
    }
}
