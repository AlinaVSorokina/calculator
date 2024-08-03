package com.scopevisio.testtask.calculator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

@Configuration
@ConfigurationProperties(prefix = "calculation")
@Getter
@Setter
public class CalculationProperties {
    private TreeMap<Integer, BigDecimal> distance;
    private Map<String, BigDecimal> type;
    private Map<String, BigDecimal> region;
}
