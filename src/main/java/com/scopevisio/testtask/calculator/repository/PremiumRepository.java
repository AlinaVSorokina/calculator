package com.scopevisio.testtask.calculator.repository;

import com.scopevisio.testtask.calculator.domain.PremiumEntity;

import java.math.BigDecimal;

public interface PremiumRepository {

    PremiumEntity savePremium(BigDecimal premium, String clientId);
}
