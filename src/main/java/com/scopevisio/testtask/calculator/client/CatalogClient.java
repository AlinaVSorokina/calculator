package com.scopevisio.testtask.calculator.client;

import com.scopevisio.testtask.calculator.domain.Region;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="CATALOG", configuration = FeignConfig.class)
public interface CatalogClient {

    @RequestMapping(value="/region", method= RequestMethod.GET)
    Region getRegionByPostcode(@RequestParam("postcode") String postcode);
}

