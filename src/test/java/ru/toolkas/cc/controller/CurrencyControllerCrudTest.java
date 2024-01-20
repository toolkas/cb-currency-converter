package ru.toolkas.cc.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.toolkas.cc.model.Currency;
import ru.toolkas.cc.service.CurrencyService;
import ru.toolkas.cc.util.TestUtils;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CurrencyControllerCrudTest {


        @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyService currencyService;

    @Test
    void testConvert() throws Exception {
        mockMvc.perform(post("/currency/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"from\": \"USD\",\"to\": \"EUR\",\"amount\": 1}"))

                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(currencyService, times(1)).convert("USD", BigDecimal.ONE, "EUR");
    }
    @Test
    void testCreate() throws Exception {
        when(currencyService.create(any(Currency.class))).thenReturn(new Currency());
        mockMvc.perform(post("/currency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"USD\",\"charCode\": \"USD\",\"nominal\": 1,\"value\": 1}"))

                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(currencyService, times(1)).create(any(Currency.class));
    }
    @Test
    void testUpdate() throws Exception {
        when(currencyService.update(any(Currency.class))).thenReturn(new Currency());
        mockMvc.perform(post("/currency/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"1\",\"name\": \"USD\",\"charCode\": \"USD\",\"nominal\": 1,\"value\": 1}"))

                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(currencyService, times(1)).update(any(Currency.class));
    }
    @Test
    void testDelete() throws Exception {
        mockMvc.perform(post("/currency/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"1\",\"name\": \"USD\",\"charCode\": \"USD\",\"nominal\": 1,\"value\": 1}"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(currencyService, times(1)).deleteById(any());
    }

}