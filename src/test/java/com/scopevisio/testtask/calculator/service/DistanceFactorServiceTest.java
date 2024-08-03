package com.scopevisio.testtask.calculator.service;

import com.scopevisio.testtask.calculator.config.CalculationProperties;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
@Import(CalculationProperties.class)
public class DistanceFactorServiceTest {

    @InjectMocks
    private DistanceFactorService distanceFactorService;

    @Spy
    private CalculationProperties calculationProperties;

    @Test
    public void runTest() {

        BigDecimal bd = distanceFactorService.getDistanceFactor(12);

        Assertions.assertEquals(new BigDecimal("1.5"), bd);
    }



}
