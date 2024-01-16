package ru.toolkas.cc.dto;

import jakarta.annotation.Nonnull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConvertDto {
  @Nonnull
  private String from;
  @Nonnull
  private String to;
  @Nonnull
  private BigDecimal amount;
}
