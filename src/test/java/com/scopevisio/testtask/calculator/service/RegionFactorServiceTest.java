package com.scopevisio.testtask.calculator.service;

import com.scopevisio.testtask.calculator.config.CalculationProperties;
import com.scopevisio.testtask.calculator.domain.Region;
import com.scopevisio.testtask.calculator.exception.NotFoundException;
import com.scopevisio.testtask.calculator.exception.ProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.Map;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RegionFactorServiceTest {

    @InjectMocks
    private RegionFactorService regionFactorService;

    @Mock
    private CatalogService catalogService;

    @Mock
    private CalculationProperties properties;

    private String postcode = "52066";

    @Test
    public void testGetRegionFactorByPostalCodeSuccess() {
        when(catalogService.getRegion(postcode)).thenReturn(Region.builder()
                .bundesland("Nordrhein-Westfalen-test")
                .postCode(postcode)
                .build());
        when(properties.getRegion()).thenReturn(Map.of("Nordrhein-Westfalen-test", new BigDecimal("1.5")));
        BigDecimal factor = regionFactorService.getRegionFactorByPostalCode(postcode);

        Assertions.assertEquals(new BigDecimal("1.5"), factor);
    }

    @Test
    void testRegionNotReceived() {
        when(catalogService.getRegion(postcode)).thenThrow(new NotFoundException("not found"));

        NotFoundException thrown = Assertions.assertThrows(NotFoundException.class, () -> {
            regionFactorService.getRegionFactorByPostalCode(postcode);
        }, "NotFoundException was expected");

        Assertions.assertEquals("not found", thrown.getMessage());
    }

    @Test
    void testRegionNotFoundINProperties() {
        when(catalogService.getRegion(postcode)).thenReturn(Region.builder()
                .bundesland("Nordrhein-Westfalen-test")
                .postCode(postcode)
                .build());
        when(properties.getRegion()).thenReturn(Map.of("another region", new BigDecimal("1.5")));

        ProcessingException thrown = Assertions.assertThrows(ProcessingException.class, () -> {
            regionFactorService.getRegionFactorByPostalCode(postcode);
        }, "ProcessingException was expected");

        Assertions.assertEquals("can not calculate for postcode = 52066", thrown.getMessage());
    }
}
