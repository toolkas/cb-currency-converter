package ru.toolkas.cc.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Table("currency")
public class Currency implements Persistable<UUID> {
  @Id
  @Column("id")
  private UUID id;
  @Column("name")
  private String name;
  @Column("char_code")
  private String charCode;
  @Column("num_code")
  private int numCode;
  @Column("nominal")
  private int nominal;
  @Column("value")
  private BigDecimal value;
  @Column("cb_id")
  private String cbId;
  @Column("published_at")
  private LocalDate published;

  @Transient
  private boolean isNew;

  public boolean isNew() {
    return isNew;
  }
}
