package com.scopevisio.testtask.calculator.application;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class IncomingRequest {
    @Positive(message = "kilometerleistung must be positiv")
    private int kilometerleistung;
    @NotBlank(message = "postleitzahl is a mandatory field")
    private String postleitzahl;
    @NotBlank
    private String fahrzeugtyp;
}
