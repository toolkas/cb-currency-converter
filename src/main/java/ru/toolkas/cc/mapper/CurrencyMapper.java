package ru.toolkas.cc.mapper;

import lombok.NonNull;
import org.mapstruct.Mapper;
import ru.toolkas.cc.dto.CurrencyDto;
import ru.toolkas.cc.model.Currency;

@Mapper
public interface CurrencyMapper {
  CurrencyDto toDto(@NonNull Currency currency);

  Currency toModel(@NonNull CurrencyDto dto);
}
