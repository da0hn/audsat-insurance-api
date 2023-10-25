package br.com.audsat.insurance.api.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serial;

@Entity
@Table(name = "CAR_DRIVERS")
public class CarDrivers extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 7110210646855622467L;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CAR_ID")
  private Car car;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "DRIVER_ID")
  private Driver driver;

  @Column(name = "IS_MAIN_DRIVER")
  private Boolean isMainDriver;

  public CarDrivers(final Long id, final Car car, final Driver driver, final Boolean isMainDriver) {
    super(id);
    this.car = car;
    this.driver = driver;
    this.isMainDriver = isMainDriver;
  }

  public CarDrivers() { }

  public static CarsDriverBuilder builder() { return new CarsDriverBuilder(); }

  public Car getCar() { return this.car; }

  public void setCar(final Car car) { this.car = car; }

  public Driver getDriver() { return this.driver; }

  public void setDriver(final Driver driver) { this.driver = driver; }

  public Boolean getIsMainDriver() { return this.isMainDriver; }

  public void setIsMainDriver(final Boolean isMainDriver) { this.isMainDriver = isMainDriver; }

  public static class CarsDriverBuilder {

    Long id;

    private Car car;

    private Driver driver;

    private Boolean isMainDriver;

    CarsDriverBuilder() { }

    public CarsDriverBuilder id(final Long id) {
      this.id = id;
      return this;
    }

    public CarsDriverBuilder car(final Car car) {
      this.car = car;
      return this;
    }

    public CarsDriverBuilder driver(final Driver driver) {
      this.driver = driver;
      return this;
    }

    public CarsDriverBuilder isMainDriver(final Boolean isMainDriver) {
      this.isMainDriver = isMainDriver;
      return this;
    }

    public CarDrivers build() {
      return new CarDrivers(this.id, this.car, this.driver, this.isMainDriver);
    }

  }

}
