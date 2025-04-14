package ru.duhov.vacationPayCalculator.entity;

import lombok.Getter;

import java.time.MonthDay;

@Getter
public enum HolidayHandler {
    NEW_YEAR(MonthDay.of(1, 1)),
    WOMENS_DAY(MonthDay.of(3, 8)),
    LABOUR_DAY(MonthDay.of(5, 1)),
    VICTORY_DAY(MonthDay.of(5, 9)),
    RUSSIA_DAY(MonthDay.of(6, 12));

    private final MonthDay monthDay;

    HolidayHandler(MonthDay monthDay) {
        this.monthDay = monthDay;
    }

    public MonthDay getMonthDay() {
        return monthDay;
    }
}
