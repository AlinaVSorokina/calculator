package com.scopevisio.testtask.calculator.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CalculationController {


//    @Autowired
//    protected MockMvc mockMvc;
//
//    private MockHttpSession session;
//
//    @BeforeEach
//    public void setSession() throws Exception{
//        session = new MockHttpSession();
//    }
//
//    @Test
//    public void testRun() throws Exception{
//        this.mockMvc.perform(
//                        post("/premium").session(session)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content("{\n" +
//                                        "\t\"kilometerleistung\": 15000,\n" +
//                                        "\t\"postleitzahl\": \"55555\"\n" +
//                                        "}").session(session)
//                                .header("clientId", "test-client"))
//                .andExpect(status().isBadRequest());
//    }
}
