package ru.toolkas.cc.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.toolkas.cc.cb.model.ValCurs;
import ru.toolkas.cc.cb.model.Valute;
import ru.toolkas.cc.service.CbService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ConvertControllerTest {
    @Autowired

    private MockMvc mockMvc;
    @MockBean
    private CbService cbService;

    @Test
    void testConvert() throws Exception {
        List<Valute> valutes = new ArrayList<>();
        Valute valute = new Valute();
        valute.setCharCode("USD");
        valute.setNominal(1);
        valute.setValue("100");
        valutes.add(valute);

        ValCurs valCurs = new ValCurs();
        valCurs.setDate("01.01.2021");
        valCurs.setName("Foreign Currency Market");
        valCurs.setValutes(valutes);
        Mockito.when(cbService.fetch(Mockito.any()))
                .thenReturn(valCurs);


        mockMvc.perform(post("/api/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"from\":\"USD\",\"to\":\"EUR\",\"amount\":100}"))
                .andExpect(status().isOk())
                .andExpect(content().string("10000.00"));



    }
}
