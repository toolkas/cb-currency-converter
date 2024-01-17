package ru.toolkas.cc.util;

import ru.toolkas.cc.model.Currency;

import java.math.BigDecimal;
import java.math.MathContext;

public class CurrencyUtils {
  private CurrencyUtils() {
  }

  public static BigDecimal convert(Currency from, Currency to, BigDecimal amount) {
    return amount.multiply(from.getValue())
            .multiply(BigDecimal.valueOf(to.getNominal()))
            .divide(to.getValue().multiply(BigDecimal.valueOf(from.getNominal())), MathContext.DECIMAL128);
  }
}
