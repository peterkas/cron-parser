package com.peterkas.cronparser.cron;

import java.time.temporal.ChronoField;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CronField {

    private final List<CronValue> values = new ArrayList<>();

    public void addValueRange(ValueRange range, int delta) {
        for (int i = (int) range.getMinimum(); i <= range.getMaximum(); i += delta) {
            values.add(new CronValue(i));
        }
        Collections.sort(values, Comparator.comparingInt(CronValue::getVal));
    }

    public List<CronValue> getValues(){
        return Collections.unmodifiableList(values);
    }

    public static CronField parse(String value, ChronoField type) {
        CronField cronField = new CronField();
        String[] rangeParts = value.split(",");
        for (String rangePart : rangeParts) {
            int slashPos = rangePart.indexOf('/');
            if (slashPos == -1) {
                cronField.addValueRange(parseRange(rangePart, type), 1);
            } else {
                String rangeStr = rangePart.substring(0, slashPos);
                String deltaStr = rangePart.substring(slashPos + 1);
                ValueRange range = parseRange(rangeStr, type);
                int delta = Integer.parseInt(deltaStr);
                if (delta <= 0) {
                    throw new IllegalArgumentException("Invalid value");
                }

                cronField.addValueRange(range, delta);
            }
        }
        return cronField;
    }

    private static ValueRange parseRange(String value, ChronoField type) {
        if (value.equals("*")) {
            return type.range();
        }
        int min, max;
        int hyphenPos = value.indexOf('-');
        if (hyphenPos == -1) {
            min = type.checkValidIntValue(Integer.parseInt(value));
            max = min;
        } else {
            min = type.checkValidIntValue(Integer.parseInt(value.substring(0, hyphenPos)));
            max = type.checkValidIntValue(Integer.parseInt(value.substring(hyphenPos + 1)));
        }
        return ValueRange.of(min, max);
    }
}
