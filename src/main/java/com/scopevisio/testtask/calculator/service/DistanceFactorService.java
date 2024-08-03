package com.scopevisio.testtask.calculator.service;

import com.scopevisio.testtask.calculator.config.CalculationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DistanceFactorService {

    private final CalculationProperties properties;

    public BigDecimal getDistanceFactor(final int distance) {
        int key = properties.getDistance()
                .descendingKeySet().stream()
                .filter(currentKey -> currentKey < distance)
                .findFirst()
                .orElse(0);
        return properties.getDistance().get(key);
    }
}
