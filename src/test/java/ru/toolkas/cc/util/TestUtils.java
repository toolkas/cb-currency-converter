package ru.toolkas.cc.util;

import ru.toolkas.cc.model.Currency;

import java.math.BigDecimal;

public class TestUtils {
  private TestUtils() {
  }

  public static Currency createUsdCurrency() {
    Currency usd = new Currency();
    usd.setCbId("R01235");
    usd.setNumCode(840);
    usd.setCharCode("USD");
    usd.setNominal(1);
    usd.setName("Доллар США");
    usd.setValue(BigDecimal.valueOf(88.3540));
    return usd;
  }

  public static Currency createEurCurrency() {
    Currency euro = new Currency();
    euro.setCbId("R01239");
    euro.setNumCode(978);
    euro.setCharCode("EUR");
    euro.setNominal(1);
    euro.setName("Евро");
    euro.setValue(BigDecimal.valueOf(96.0531));
    return euro;
  }
}
