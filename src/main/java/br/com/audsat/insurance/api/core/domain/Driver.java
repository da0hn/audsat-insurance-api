package br.com.audsat.insurance.api.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "DRIVERS")
public class Driver extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 2251674197185372491L;

  @Column(name = "DOCUMENT")
  private String document;

  @Column(name = "BIRTHDATE")
  private LocalDate birthdate;

  @OneToMany(mappedBy = "driver")
  private Set<CarDrivers> carDrivers;

  public Driver(final Long id, final String document, final LocalDate birthdate, final Set<CarDrivers> carDrivers) {
    super(id);
    this.document = document;
    this.birthdate = birthdate;
    this.carDrivers = carDrivers;
  }

  public Driver() { }

  public static DriverBuilder builder() { return new DriverBuilder(); }

  public String getDocument() { return this.document; }

  public void setDocument(final String document) { this.document = document; }

  public LocalDate getBirthdate() { return this.birthdate; }

  public void setBirthdate(final LocalDate birthdate) { this.birthdate = birthdate; }

  public Set<CarDrivers> getCarDrivers() {
    return this.carDrivers;
  }

  public void setCarDrivers(final Set<CarDrivers> carDrivers) {
    this.carDrivers = carDrivers;
  }

  public static class DriverBuilder {

    private Long id;

    private String document;

    private LocalDate birthdate;

    private Set<CarDrivers> carsDriver;

    DriverBuilder() { }

    public DriverBuilder id(final Long id) {
      this.id = id;
      return this;
    }

    public DriverBuilder document(final String document) {
      this.document = document;
      return this;
    }

    public DriverBuilder birthdate(final LocalDate birthdate) {
      this.birthdate = birthdate;
      return this;
    }

    public DriverBuilder carsDriver(final Set<CarDrivers> carsDriver) {
      this.carsDriver = carsDriver;
      return this;
    }

    public Driver build() {
      return new Driver(this.id, this.document, this.birthdate, this.carsDriver);
    }

  }

}
