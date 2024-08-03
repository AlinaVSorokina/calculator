package com.scopevisio.testtask.calculator.service;

import com.scopevisio.testtask.calculator.config.CalculationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.TreeMap;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DistanceFactorServiceTest {

    @InjectMocks
    private DistanceFactorService distanceFactorService;

    @Mock
    private CalculationProperties calculationProperties;

    private BigDecimal low = new BigDecimal("0.5");
    private BigDecimal normal = new BigDecimal("1.0");
    private BigDecimal high = new BigDecimal("1.5");

    @BeforeEach
    public void setUp() {
        TreeMap<Integer, BigDecimal> distance = new TreeMap<>();
        distance.put(0, new BigDecimal("0.5"));
        distance.put(5000, new BigDecimal("1.0"));
        distance.put(10000, new BigDecimal("1.5"));
        when(calculationProperties.getDistance()).thenReturn(distance);
    }

    @Test
    public void runTest() {
        Assertions.assertEquals(low, distanceFactorService.getDistanceFactor(0));
        Assertions.assertEquals(low, distanceFactorService.getDistanceFactor(1));
        Assertions.assertEquals(low, distanceFactorService.getDistanceFactor(5000));
        Assertions.assertEquals(normal, distanceFactorService.getDistanceFactor(5001));
        Assertions.assertEquals(normal, distanceFactorService.getDistanceFactor(10000));
        Assertions.assertEquals(high, distanceFactorService.getDistanceFactor(10001));
    }



}
