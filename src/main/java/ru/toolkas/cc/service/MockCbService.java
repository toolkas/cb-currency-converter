package ru.toolkas.cc.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.toolkas.cc.cb.model.ValCurs;
import ru.toolkas.cc.cb.model.Valute;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Profile("test")
@Service
public class MockCbService implements CbService {
  @Override
  public ValCurs fetch(LocalDate date) throws IOException {
    List<Valute> valutes = new ArrayList<>();
    Valute valute = new Valute();
    valute.setId("R01235");
    valute.setName("Доллар США");
    valute.setNumCode(840);
    valute.setCharCode("USD");
    valute.setNominal(1);
    valute.setValue("100");
    valutes.add(valute);

    ValCurs valCurs = new ValCurs();
    valCurs.setDate("01.01.2021");
    valCurs.setName("Foreign Currency Market");
    valCurs.setValutes(valutes);
    return valCurs;
  }
}
