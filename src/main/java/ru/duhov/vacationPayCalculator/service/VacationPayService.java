package ru.duhov.vacationPayCalculator.service;

import ru.duhov.vacationPayCalculator.entity.VacationPayRequest;
import ru.duhov.vacationPayCalculator.exception.CalculatorException;

public interface VacationPayService {

    double countVacation(VacationPayRequest vacationPayRequest) throws CalculatorException;
}
