package br.com.audsat.insurance.api.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serial;
import java.time.LocalDate;

@Entity
@Table(name = "CLAIMS")
public class Claim extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 3658248888826601573L;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CAR_ID")
  private Car car;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "DRIVER_ID")
  private Driver driver;

  @Column(name = "EVENT_DATE")
  private LocalDate eventDate;

  public Claim(final Long id, final Car car, final Driver driver, final LocalDate eventDate) {
    super(id);
    this.car = car;
    this.driver = driver;
    this.eventDate = eventDate;
  }

  public Claim() { }

  public Car getCar() { return this.car; }

  public void setCar(final Car car) { this.car = car; }

  public Driver getDriver() { return this.driver; }

  public void setDriver(final Driver driver) { this.driver = driver; }

  public LocalDate getEventDate() { return this.eventDate; }

  public void setEventDate(final LocalDate eventDate) { this.eventDate = eventDate; }

}
