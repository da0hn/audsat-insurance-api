package br.com.audsat.insurance.api.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serial;

@Entity
@Table(name = "CUSTOMER")
public class Customer extends BaseEntity {

  @Serial
  private static final long serialVersionUID = -1769001412550631862L;

  @Column(name = "NAME", nullable = false, length = 50)
  private String name;

  @ManyToOne
  @JoinColumn(name = "DRIVER_ID")
  private Driver driver;

  public Customer(final Long id, final String name, final Driver driver) {
    super(id);
    this.name = name;
    this.driver = driver;
  }

  public static CustomerBuilder builder() { return new CustomerBuilder(); }

  public String getName() { return this.name; }

  public void setName(final String name) { this.name = name; }

  public Driver getDriver() { return this.driver; }

  public void setDriver(final Driver driver) { this.driver = driver; }

  public static class CustomerBuilder {

    private Long id;

    private String name;

    private Driver driver;

    CustomerBuilder() { }

    public CustomerBuilder name(final String name) {
      this.name = name;
      return this;
    }

    public CustomerBuilder driver(final Driver driver) {
      this.driver = driver;
      return this;
    }

    public CustomerBuilder id(final Long id) {
      this.id = id;
      return this;
    }

    public Customer build() {
      return new Customer(this.id, this.name, this.driver);
    }

  }

}
