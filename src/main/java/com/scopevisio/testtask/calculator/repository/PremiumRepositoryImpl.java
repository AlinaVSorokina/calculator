package com.scopevisio.testtask.calculator.repository;

import com.scopevisio.testtask.calculator.domain.PremiumEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;

@Repository
@RequiredArgsConstructor
public class PremiumRepositoryImpl implements PremiumRepository {

    private final CrudPremiumRepository repository;

    @Override
    public PremiumEntity savePremium(BigDecimal premium, String clientId) {
        PremiumEntity entity = PremiumEntity.builder()
                .premium(premium)
                .date(Instant.now())
                .clientId(clientId)
                .build();
        return repository.save(entity);
    }
}
