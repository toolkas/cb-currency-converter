package ru.toolkas.cc.service;

import ru.toolkas.cc.cb.model.ValCurs;

import java.io.IOException;
import java.time.LocalDate;

public interface CbService {
  ValCurs fetch(LocalDate date) throws IOException;
}
