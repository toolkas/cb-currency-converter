package ru.toolkas.cc.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class CurrencyDto {
  private UUID id;
  private String name;
  private String charCode;
  private int numCode;
  private int nominal;
  private BigDecimal value;
  private String cbId;
  private LocalDate published;
}
