package ru.duhov.vacationPayCalculator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.duhov.vacationPayCalculator.entity.VacationPayRequest;
import ru.duhov.vacationPayCalculator.exception.CalculatorException;
import ru.duhov.vacationPayCalculator.service.VacationPayService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class VacationPayController {

    private final VacationPayService vacationPayService;

    @GetMapping("/count")
    public double countVacation(@RequestBody VacationPayRequest vacationPayRequest) throws CalculatorException {
        return vacationPayService.countVacation(vacationPayRequest);
    }
}
