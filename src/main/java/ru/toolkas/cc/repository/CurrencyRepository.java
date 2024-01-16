package ru.toolkas.cc.repository;

import org.springframework.data.repository.CrudRepository;
import ru.toolkas.cc.model.Currency;

import java.util.UUID;

public interface CurrencyRepository extends CrudRepository<Currency, UUID> {
  Currency findByCharCode(String code);
}
