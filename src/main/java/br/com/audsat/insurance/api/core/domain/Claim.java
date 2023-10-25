package br.com.audsat.insurance.api.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLAIMS")
public class Claim extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 3658248888826601573L;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "CAR_ID", nullable = false)
  private Car car;

  @Column(name = "EVENT_DATE")
  private LocalDate eventDate;

}
