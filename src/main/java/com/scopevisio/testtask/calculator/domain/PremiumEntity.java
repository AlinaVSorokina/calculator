package com.scopevisio.testtask.calculator.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Builder
public class PremiumEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String clientId;
    private Instant date;
    private BigDecimal premium;
}
