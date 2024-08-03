package com.scopevisio.testtask.calculator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Configuration
public class CalculationProperties {
    @Value("#{${calculation.distance}}")
    private TreeMap<Integer, BigDecimal> distance;
    @Value("#{${calculation.type}}")
    private Map<String, BigDecimal> type;
    @Value("#{${calculation.region}}")
    private Map<String, BigDecimal> region;
}
