package ru.toolkas.cc.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.toolkas.cc.dto.ConvertDto;
import ru.toolkas.cc.service.ConversionService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ConvertController {
  private final ConversionService conversionService;

  @PostMapping(path = "/convert", consumes = MediaType.APPLICATION_JSON_VALUE)
  public BigDecimal convert(@RequestBody @NonNull ConvertDto dto) {
    return conversionService.convert(dto.getFrom(), dto.getAmount(), dto.getTo());
  }
}
