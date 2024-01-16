package ru.toolkas.cc.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.toolkas.cc.cb.model.ValCurs;
import ru.toolkas.cc.cb.model.Valute;
import ru.toolkas.cc.model.Currency;
import ru.toolkas.cc.repository.CurrencyRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConversionService {
  private static final String VAL_CURS_DATE_FORMAT = "dd.MM.yyyy";
  private final CbService cbService;
  private final CurrencyRepository currencyRepository;

  @Transactional
  @PostConstruct
  public void publishToday() throws IOException {
    ValCurs valCurs = cbService.fetch(LocalDate.now());
    LocalDate published = LocalDate.parse(valCurs.getDate(), DateTimeFormatter.ofPattern(VAL_CURS_DATE_FORMAT));

    for (Valute valute: valCurs.getValutes()) {
      Currency currency = new Currency();
      currency.setId(UUID.randomUUID());
      currency.setCbId(valute.getId());
      currency.setCharCode(valute.getCharCode());
      currency.setNumCode(valute.getNumCode());
      currency.setName(valute.getName());
      currency.setValue(new BigDecimal(valute.getValue().replaceAll(",", ".")));
      currency.setNominal(valute.getNominal());
      currency.setPublished(published);
      currency.setNew(true);

      currencyRepository.save(currency);
    }
  }

  @Transactional
  public BigDecimal convert(String fromCode, BigDecimal fromAmount, String toCode) {
    Currency from = currencyRepository.findByCharCode(fromCode);
    Currency to = currencyRepository.findByCharCode(toCode);

    return fromAmount
            .multiply(from.getValue())
            .multiply(BigDecimal.valueOf(to.getNominal()))
            .divide(to.getValue().multiply(BigDecimal.valueOf(from.getNominal())),
                    MathContext.DECIMAL128
            );
  }
}
