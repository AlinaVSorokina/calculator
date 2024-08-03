package com.scopevisio.testtask.calculator.application;

import com.scopevisio.testtask.calculator.service.CalculationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CalculationController {

    private final CalculationService calculationService;
    private final ObjectMapper mapper;

    @PostMapping("/premium")
    public CalculationResponse calculatePremium(@RequestBody @Valid IncomingRequest request,
                                                @RequestHeader(value = "clientId") String clientId) {
        return new CalculationResponse(clientId, calculationService.calculatePremiumForUser(mapper.getInnerRequest(request), clientId).toPlainString());
    }

}
