package ru.toolkas.cc.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.toolkas.cc.model.Currency;
import ru.toolkas.cc.repository.CurrencyRepository;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CurrencyService {
  private static final String VAL_CURS_DATE_FORMAT = "dd.MM.yyyy";
  private final CurrencyRepository currencyRepository;


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

  public Currency create(@NonNull Currency currency) {
    currency.setId(UUID.randomUUID());
    currency.setNew(true);
    currency.setPublished(LocalDate.now());

    return currencyRepository.save(currency);
  }

  public Currency update(@NonNull Currency currency) {
    return currencyRepository.save(currency);
  }

  public void deleteById(@NonNull UUID id) {
    currencyRepository.deleteById(id);
  }

  public Optional<Currency> findById(@NonNull UUID id) {
    return currencyRepository.findById(id);
  }

  public List<Currency> findAll() {
    List<Currency> list = new ArrayList<>();
    for (Currency currency : currencyRepository.findAll()) {
      list.add(currency);
    }
    return list;
  }
}
