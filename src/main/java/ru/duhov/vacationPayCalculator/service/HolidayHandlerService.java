package ru.duhov.vacationPayCalculator.service;

import java.time.LocalDate;

public interface HolidayHandlerService {

    boolean isWeekend(LocalDate date);
    boolean isHoliday(LocalDate date);
}
