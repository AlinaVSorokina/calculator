package com.scopevisio.testtask.calculator.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Region {
    private String bundesland;
    private String postCode;
}
