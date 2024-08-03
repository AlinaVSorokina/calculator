package com.scopevisio.testtask.calculator.service;

import com.scopevisio.testtask.calculator.client.CatalogClient;
import com.scopevisio.testtask.calculator.domain.Region;
import com.scopevisio.testtask.calculator.exception.NotFoundException;
import com.scopevisio.testtask.calculator.exception.ProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatalogService {

    private final CatalogClient catalogClient;

    public Region getRegion(final String postCode) {
        try {
            return catalogClient.getRegionByPostcode(postCode);
        } catch (NotFoundException ex) {
            log.error("region for postacode = " + postCode + " not found");
            throw new NotFoundException("region for postacode = " + postCode + " not found");
        } catch (Exception e) {
            log.error("catalog problem");
            throw new ProcessingException("try later");
        }
    }
}
