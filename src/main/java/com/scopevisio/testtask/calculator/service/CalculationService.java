package com.scopevisio.testtask.calculator.service;

import com.scopevisio.testtask.calculator.domain.CalculationRequest;
import com.scopevisio.testtask.calculator.repository.PremiumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CalculationService {

    private final RegionFactorService regionFactorService;
    private final CarTypeFactorService carTypeFactorService;
    private final DistanceFactorService distanceFactorService;
    private final PremiumRepository premiumRepository;

    public BigDecimal calculatePremiumForUser(CalculationRequest calculationRequest, String clientId) {
        BigDecimal premium = calculatePremium(calculationRequest);
        premiumRepository.savePremium(premium, clientId);
        return premium;
    }

    public BigDecimal calculatePremium(CalculationRequest calculationRequest) {
         return distanceFactorService.getDistanceFactor(calculationRequest.distance())
                 .multiply(carTypeFactorService.getCarTypeFactor(calculationRequest.carType()))
                 .multiply(regionFactorService.getRegionFactorByPostalCode(calculationRequest.postCode()))
                 .setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
