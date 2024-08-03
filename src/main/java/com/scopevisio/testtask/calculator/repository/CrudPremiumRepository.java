package com.scopevisio.testtask.calculator.repository;

import com.scopevisio.testtask.calculator.domain.PremiumEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudPremiumRepository extends CrudRepository<PremiumEntity, Long> {
}
