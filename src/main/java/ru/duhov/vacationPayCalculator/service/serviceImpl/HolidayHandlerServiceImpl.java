package ru.duhov.vacationPayCalculator.service.serviceImpl;

import org.springframework.stereotype.Service;
import ru.duhov.vacationPayCalculator.entity.HolidayHandler;
import ru.duhov.vacationPayCalculator.service.HolidayHandlerService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;

@Service
public class HolidayHandlerServiceImpl implements HolidayHandlerService {

    @Override
    public boolean isHoliday(LocalDate date) {
        MonthDay md = MonthDay.from(date);
        for (HolidayHandler h : HolidayHandler.values()) {
            if (h.getMonthDay().equals(md)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isWeekend(LocalDate date) {
        DayOfWeek dow = date.getDayOfWeek();
        return dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY;
    }
}

