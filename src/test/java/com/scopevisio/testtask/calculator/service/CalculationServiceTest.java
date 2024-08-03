package com.scopevisio.testtask.calculator.service;

import com.scopevisio.testtask.calculator.domain.CalculationRequest;
import com.scopevisio.testtask.calculator.domain.PremiumEntity;
import com.scopevisio.testtask.calculator.exception.NotFoundException;
import com.scopevisio.testtask.calculator.exception.ProcessingException;
import com.scopevisio.testtask.calculator.repository.PremiumRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculationServiceTest {

    @InjectMocks
    private CalculationService calculationService;

    @Mock
    private RegionFactorService regionFactorService;
    @Mock
    private CarTypeFactorService carTypeFactorService;
    @Mock
    private DistanceFactorService distanceFactorService;
    @Mock
    private PremiumRepository premiumRepository;

    private String postcode = "52066";
    private String carType = "LKW";
    private int distance = 1000;
    private String clientId = "clientId";
    private CalculationRequest request = new CalculationRequest(1000, postcode, carType);


    @Test
    public void testCalculatePremiumForUserSuccess() {
        when(regionFactorService.getRegionFactorByPostalCode(postcode)).thenReturn(new BigDecimal("1.0"));
        when(distanceFactorService.getDistanceFactor(distance)).thenReturn(new BigDecimal("0.5"));
        when(carTypeFactorService.getCarTypeFactor(carType)).thenReturn(new BigDecimal("100"));
        when(premiumRepository.savePremium(any(BigDecimal.class), anyString()))
                .thenAnswer(answer -> PremiumEntity.builder().premium(answer.getArgument(0)).clientId(answer.getArgument(1)).build());

        BigDecimal premium = calculationService.calculatePremiumForUser(request, clientId);
        Assertions.assertEquals(new BigDecimal("50.00"), premium);

        verify(regionFactorService, times(1)).getRegionFactorByPostalCode(postcode);
        verify(distanceFactorService, times(1)).getDistanceFactor(distance);
        verify(carTypeFactorService, times(1)).getCarTypeFactor(carType);
        verify(premiumRepository, times(1)).savePremium(any(BigDecimal.class), anyString());
    }

    @Test
    public void testCalculatePremiumForUserWrongPostcode() {
        when(regionFactorService.getRegionFactorByPostalCode(postcode)).thenThrow(new NotFoundException("notFound"));
        when(distanceFactorService.getDistanceFactor(distance)).thenReturn(new BigDecimal("0.5"));
        when(carTypeFactorService.getCarTypeFactor(carType)).thenReturn(new BigDecimal("100"));

       Assertions.assertThrows(NotFoundException.class, () -> {
            calculationService.calculatePremiumForUser(request, clientId);
        }, "notFound");

        verify(regionFactorService, times(1)).getRegionFactorByPostalCode(postcode);
        verify(distanceFactorService, times(1)).getDistanceFactor(distance);
        verify(carTypeFactorService, times(1)).getCarTypeFactor(carType);
        verify(premiumRepository, times(0)).savePremium(any(BigDecimal.class), anyString());
    }

    @Test
    public void testCalculatePremiumForUserWrongCarType() {
        when(distanceFactorService.getDistanceFactor(distance)).thenReturn(new BigDecimal("0.5"));
        when(carTypeFactorService.getCarTypeFactor(carType)).thenThrow(new ProcessingException("wrongCarType"));

        Assertions.assertThrows(ProcessingException.class, () -> {
            calculationService.calculatePremiumForUser(request, clientId);
        }, "wrongCarType");

        verify(regionFactorService, times(0)).getRegionFactorByPostalCode(postcode);
        verify(distanceFactorService, times(1)).getDistanceFactor(distance);
        verify(carTypeFactorService, times(1)).getCarTypeFactor(carType);
        verify(premiumRepository, times(0)).savePremium(any(BigDecimal.class), anyString());
    }

    @Test
    public void testCalculatePremiumForUserWrongDBFailure() {
        when(regionFactorService.getRegionFactorByPostalCode(postcode)).thenReturn(new BigDecimal("1.0"));
        when(distanceFactorService.getDistanceFactor(distance)).thenReturn(new BigDecimal("0.5"));
        when(carTypeFactorService.getCarTypeFactor(carType)).thenReturn(new BigDecimal("100"));
        when(premiumRepository.savePremium(any(BigDecimal.class), anyString())).thenThrow(new ProcessingException("DB error"));

        Assertions.assertThrows(ProcessingException.class, () -> {
            calculationService.calculatePremiumForUser(request, clientId);
        }, "DB error");

        verify(regionFactorService, times(1)).getRegionFactorByPostalCode(postcode);
        verify(distanceFactorService, times(1)).getDistanceFactor(distance);
        verify(carTypeFactorService, times(1)).getCarTypeFactor(carType);
        verify(premiumRepository, times(1)).savePremium(any(BigDecimal.class), anyString());

    }
}
