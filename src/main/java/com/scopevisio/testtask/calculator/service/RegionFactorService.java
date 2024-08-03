package com.scopevisio.testtask.calculator.service;

import com.scopevisio.testtask.calculator.config.CalculationProperties;
import com.scopevisio.testtask.calculator.exception.ProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RegionFactorService {

    private final CatalogService catalogService;
    private final CalculationProperties properties;

    public BigDecimal getRegionFactorByPostalCode(final String postcode) {
        String region = catalogService.getRegion(postcode).getBundesland();
        if (properties.getRegion().containsKey(region)) {
            return properties.getRegion().get(region);
        } else {
            throw new ProcessingException("can not calculate for postcode = " + postcode);
        }
    }
}
