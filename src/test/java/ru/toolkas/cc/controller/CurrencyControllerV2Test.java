package ru.toolkas.cc.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.toolkas.cc.mapper.CurrencyMapperImpl;
import ru.toolkas.cc.repository.CurrencyRepository;
import ru.toolkas.cc.service.CurrencyService;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.toolkas.cc.util.TestUtils.*;

@WebMvcTest(CurrencyController.class)
@Import({CurrencyService.class, CurrencyMapperImpl.class})
class CurrencyControllerV2Test {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CurrencyRepository currencyRepository;

  @Test
  void testConvert() throws Exception {
    when(currencyRepository.findByCharCode("USD")).thenReturn(createUsdCurrency());
    when(currencyRepository.findByCharCode("EUR")).thenReturn(createEurCurrency());

    mockMvc.perform(post("/currency/convert")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"from\": \"USD\",\"to\": \"EUR\",\"amount\": 1}"))
            .andExpect(status().isOk())
            .andExpect(content().string("0.9198453771924071164803634656247430"));

    Mockito.verify(currencyRepository).findByCharCode("USD");
    Mockito.verify(currencyRepository).findByCharCode("EUR");
  }
}
