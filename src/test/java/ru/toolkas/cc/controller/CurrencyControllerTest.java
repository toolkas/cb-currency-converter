package ru.toolkas.cc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CurrencyControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void testConvert() throws Exception {
    mockMvc.perform(post("/currency/convert")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"from\": \"USD\",\"to\": \"EUR\",\"amount\": 1}"))
            .andExpect(status().isOk())
            .andExpect(content().string("0.9198453771924071164803634656247430"));
  }
}
