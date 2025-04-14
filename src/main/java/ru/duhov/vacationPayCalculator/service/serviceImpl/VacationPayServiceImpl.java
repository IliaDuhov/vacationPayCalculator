package ru.duhov.vacationPayCalculator.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.duhov.vacationPayCalculator.entity.VacationPayRequest;
import ru.duhov.vacationPayCalculator.exception.CalculatorException;
import ru.duhov.vacationPayCalculator.service.HolidayHandlerService;
import ru.duhov.vacationPayCalculator.service.VacationPayService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class VacationPayServiceImpl implements VacationPayService {

    private static final double AVG_MONTH_DAYS = 29.3;
    private final HolidayHandlerService holidayService;

    public double countVacation(VacationPayRequest vacationPayRequest) throws CalculatorException {
        validate(vacationPayRequest);
        if (vacationPayRequest.getStartDate() == null && vacationPayRequest.getEndDate() == null) {
            return calculateWithoutDate(vacationPayRequest);
        }

        if (vacationPayRequest.getStartDate() != null &&
                vacationPayRequest.getEndDate() == null &&
                vacationPayRequest.getVacationDays() > 0) {
            return calculateWithStartDate(vacationPayRequest);
        }

        if (vacationPayRequest.getStartDate() != null &&
                vacationPayRequest.getEndDate() != null &&
                vacationPayRequest.getVacationDays() == 0) {
            return calculateWithExactDates(vacationPayRequest);
        }

        throw new CalculatorException("Invalid combination of fields in request");
    }

    private double calculateWithoutDate(VacationPayRequest vacationPayRequest) {
        return vacationPayRequest.getAvgSalary() / AVG_MONTH_DAYS * vacationPayRequest.getVacationDays();
    }

    private double calculateWithExactDates(VacationPayRequest vacationPayRequest){
        long daysInInclusive = ChronoUnit.DAYS.between(vacationPayRequest.getStartDate(),
                vacationPayRequest.getEndDate()) + 1;
        vacationPayRequest.setVacationDays((int) daysInInclusive);
        return daysInInclusive * vacationPayRequest.getAvgSalary() / AVG_MONTH_DAYS;
    }

    private double calculateWithStartDate(VacationPayRequest vacationPayRequest) {
        LocalDate startDate = vacationPayRequest.getStartDate();
        int vacationDays = vacationPayRequest.getVacationDays();

        for (int i = 0; i < vacationPayRequest.getVacationDays(); i++) {
            if (isHolidayOrWeekendDay(startDate)) {
                vacationDays--;
            }
            startDate = startDate.plusDays(1);
        }

        return vacationDays * vacationPayRequest.getAvgSalary() / AVG_MONTH_DAYS;
    }


    private boolean isHolidayOrWeekendDay(LocalDate date) {
        return holidayService.isWeekend(date) || holidayService.isHoliday(date);
    }

    private void validate(VacationPayRequest vacationPayRequest) throws CalculatorException {
        if (vacationPayRequest.getAvgSalary() == null || vacationPayRequest.getAvgSalary() < 0) {
            throw new CalculatorException("Salary must be provided and non-negative");
        }

        if (vacationPayRequest.getVacationDays() < 0) {
            throw new CalculatorException("Vacation days must be non-negative");
        }

        if (vacationPayRequest.getStartDate() != null && vacationPayRequest.getEndDate() != null &&
                vacationPayRequest.getStartDate().isAfter(vacationPayRequest.getEndDate())) {
            throw new CalculatorException("Start date must not be after end date");
        }
    }
}
