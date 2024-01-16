package ru.toolkas.cc.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.toolkas.cc.cb.model.ValCurs;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Profile("!test")
@RequiredArgsConstructor
public class CbServiceImpl implements CbService {
  private static final String DATE_FORMAT = "dd/MM/yyyy";

  private final XmlMapper xmlMapper = new XmlMapper();

  {
    xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Value("${cc.cb.url}")
  private String url;

  public ValCurs fetch(LocalDate date) throws IOException {
    String todayUrl = url + "?date_req=" + date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));

    return xmlMapper.readValue(new URL(todayUrl), ValCurs.class);
  }
}
