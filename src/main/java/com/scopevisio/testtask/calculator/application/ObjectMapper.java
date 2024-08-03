package com.scopevisio.testtask.calculator.application;

import com.scopevisio.testtask.calculator.domain.CalculationRequest;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapper {

    public CalculationRequest getInnerRequest(IncomingRequest request) {
        return new CalculationRequest(request.getKilometerleistung(),
                request.getPostleitzahl(),
                request.getFahrzeugtyp());
    }
}
