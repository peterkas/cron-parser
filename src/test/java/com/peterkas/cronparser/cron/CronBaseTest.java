package com.peterkas.cronparser.cron;

import java.util.ArrayList;
import java.util.List;

abstract class CronBaseTest {
    protected List<Integer> convertCronValuesToInt(List<CronValue> cronValues) {
        List<Integer> intValues = new ArrayList<>(cronValues.size());
        cronValues.forEach(x -> intValues.add(x.getVal()));
        return intValues;
    }
}
