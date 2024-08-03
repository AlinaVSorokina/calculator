package com.scopevisio.testtask.calculator.service;

import com.scopevisio.testtask.calculator.config.CalculationProperties;
import com.scopevisio.testtask.calculator.exception.ProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CarTypeFactorService {

    private final CalculationProperties properties;

    public BigDecimal getCarTypeFactor(final String carType) {
        BigDecimal factorValue = properties.getType().get(carType);
        if (Objects.isNull(factorValue)) {
            throw new ProcessingException("no such car type");
        }
        return factorValue;
    }
}
