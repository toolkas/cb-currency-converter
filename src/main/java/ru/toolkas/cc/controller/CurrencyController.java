package ru.toolkas.cc.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.toolkas.cc.dto.ConvertDto;
import ru.toolkas.cc.dto.CurrencyDto;
import ru.toolkas.cc.mapper.CurrencyMapper;
import ru.toolkas.cc.service.CurrencyService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {
  private final CurrencyService currencyService;
  private final CurrencyMapper currencyMapper;

  @PostMapping(path = "/convert", consumes = MediaType.APPLICATION_JSON_VALUE)
  public BigDecimal convert(@RequestBody @NonNull ConvertDto dto) {
    return currencyService.convert(dto.getFrom(), dto.getAmount(), dto.getTo());
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public CurrencyDto create(@NonNull @RequestBody CurrencyDto dto) {
    return currencyMapper.toDto(currencyService.create(currencyMapper.toModel(dto)));
  }

  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public CurrencyDto update(@NonNull @PathVariable("id") UUID id, @NonNull @RequestBody CurrencyDto dto) {
    dto.setId(id);
    return currencyMapper.toDto(currencyService.update(currencyMapper.toModel(dto)));
  }

  @DeleteMapping(path = "/{id}")
  public void deleteById(@NonNull @PathVariable("id") UUID id) {
    currencyService.deleteById(id);
  }

  @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  public Optional<CurrencyDto> findById(@NonNull @PathVariable("id") UUID id) {
    return currencyService.findById(id).map(currencyMapper::toDto);
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public List<CurrencyDto> findAll() {
    return currencyService.findAll().stream()
            .map(currencyMapper::toDto)
            .collect(Collectors.toList());
  }
}
