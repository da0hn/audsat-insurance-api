package br.com.audsat.insurance.api.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serial;

@Entity
@Table(name = "CARS")
public class Car extends BaseEntity {

  @Serial
  private static final long serialVersionUID = -554101408535038647L;

  @Column(name = "MODEL", nullable = false, length = 50)
  private String model;

  @Column(name = "MANUFACTURER", nullable = false, length = 50)
  private String manufacturer;

  @Column(name = "MODEL_YEAR", nullable = false, length = 10)
  private String year;

  @Column(name = "FIPE_VALUE")
  private Double fipeValue;

  public Car(final Long id, final String model, final String manufacturer, final String year, final Double fipeValue) {
    super(id);
    this.model = model;
    this.manufacturer = manufacturer;
    this.year = year;
    this.fipeValue = fipeValue;
  }

  public Car() { }

  public static CarBuilder builder() { return new CarBuilder(); }

  public String getModel() { return this.model; }

  public void setModel(final String model) { this.model = model; }

  public String getManufacturer() { return this.manufacturer; }

  public void setManufacturer(final String manufacturer) { this.manufacturer = manufacturer; }

  public String getYear() { return this.year; }

  public void setYear(final String year) { this.year = year; }

  public Double getFipeValue() { return this.fipeValue; }

  public void setFipeValue(final Double fipeValue) { this.fipeValue = fipeValue; }

  public static class CarBuilder {

    private Long id;

    private String model;

    private String manufacturer;

    private String year;

    private Double fipeValue;

    CarBuilder() { }

    public CarBuilder model(final String model) {
      this.model = model;
      return this;
    }

    public CarBuilder manufacturer(final String manufacturer) {
      this.manufacturer = manufacturer;
      return this;
    }

    public CarBuilder year(final String year) {
      this.year = year;
      return this;
    }

    public CarBuilder fipeValue(final Double fipeValue) {
      this.fipeValue = fipeValue;
      return this;
    }

    public CarBuilder id(final Long id) {
      this.id = id;
      return this;
    }

    public Car build() {
      return new Car(this.id, this.model, this.manufacturer, this.year, this.fipeValue);
    }

  }

}
