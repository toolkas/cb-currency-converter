package ru.toolkas.cc.cb.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "ValCurs")
public class ValCurs {
  @JacksonXmlProperty(localName = "name", isAttribute = true)
  private String name;

  @JacksonXmlProperty(localName = "Date", isAttribute = true)
  private String date;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Valute")
  private List<Valute> valutes;
}
