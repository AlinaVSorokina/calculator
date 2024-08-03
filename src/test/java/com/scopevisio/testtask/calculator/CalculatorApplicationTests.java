package com.scopevisio.testtask.calculator;

import com.scopevisio.testtask.calculator.application.CalculationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

class CalculatorApplicationTests {

//	@Autowired
//	private CalculationController controller;

	@Autowired
	private MockMvc mockMvc;


//    @BeforeEach
//    public void setSession() throws Exception{
//        session = new MockHttpSession();
//    }

//    @Test
//    public void testRun() throws Exception{
//        this.mockMvc.perform(
//                        post("http://localhost:8080/premium")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content("{\n" +
//                                        "\t\"kilometerleistung\": 15000,\n" +
//                                        "\t\"postleitzahl\": \"55555\"\n" +
//                                        "}")
//                                .header("clientId", "test-client"))
//                .andExpect(status().isBadRequest());
//    }

//	@Test
//	void contextLoads() {
//		assertThat(controller).isNotNull();
//	}

}
