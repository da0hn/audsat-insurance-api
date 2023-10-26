package br.com.audsat.insurance.api.core.domain;

import br.com.audsat.insurance.api.core.shared.ApplicationMessages;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.springframework.util.Assert;

import java.io.Serial;
import java.time.Instant;

@Entity
@Table(name = "INSURANCE")
public class Insurance extends BaseEntity {

  @Serial
  private static final long serialVersionUID = -9184883699982561171L;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CUSTOMER_ID", nullable = false)
  private Customer customer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CAR_ID", nullable = false)
  private Car car;

  @Column(name = "CREATION_DT", nullable = false)
  private Instant creationDate;

  @Column(name = "UPDATED_DT", nullable = false)
  private Instant updatedDate;

  @Column(name = "IS_ACTIVE", nullable = false)
  private Boolean isActive;

  public Insurance(
    final Long id,
    final Customer customer,
    final Car car,
    final Instant creationDate,
    final Instant updatedDate,
    final Boolean isActive
  ) {
    super(id);
    this.customer = customer;
    this.car = car;
    this.creationDate = creationDate;
    this.updatedDate = updatedDate;
    this.isActive = isActive;
  }

  public Insurance() { }

  public static Insurance createBudget(final Car car, final Customer customer, final boolean active) {
    Assert.notNull(car, ApplicationMessages.CAR_CANNOT_BE_NULL);
    Assert.notNull(customer, ApplicationMessages.CUSTOMER_CANNOT_BE_NULL);

    return Insurance.builder()
      .creationDate(Instant.now())
      .customer(customer)
      .isActive(active)
      .car(car)
      .build();
  }

  public static InsuranceBuilder builder() { return new InsuranceBuilder(); }

  public Customer getCustomer() { return this.customer; }

  public void setCustomer(final Customer customer) { this.customer = customer; }

  public Car getCar() { return this.car; }

  public void setCar(final Car car) { this.car = car; }

  public Instant getCreationDate() { return this.creationDate; }

  public void setCreationDate(final Instant creationDate) { this.creationDate = creationDate; }

  public Instant getUpdatedDate() { return this.updatedDate; }

  public void setUpdatedDate(final Instant updatedDate) { this.updatedDate = updatedDate; }

  public Boolean getIsActive() { return this.isActive; }

  public void setIsActive(final Boolean isActive) { this.isActive = isActive; }

  public void updated() {
    this.updatedDate = Instant.now();
  }

  public static class InsuranceBuilder {

    private Long id;

    private Customer customer;

    private Car car;

    private Instant creationDate;

    private Instant updatedDate;

    private Boolean isActive;

    InsuranceBuilder() { }

    public InsuranceBuilder id(final Long id) {
      this.id = id;
      return this;
    }

    public InsuranceBuilder customer(final Customer customer) {
      this.customer = customer;
      return this;
    }

    public InsuranceBuilder car(final Car car) {
      this.car = car;
      return this;
    }

    public InsuranceBuilder creationDate(final Instant creationDate) {
      this.creationDate = creationDate;
      return this;
    }

    public InsuranceBuilder updatedDate(final Instant updatedDate) {
      this.updatedDate = updatedDate;
      return this;
    }

    public InsuranceBuilder isActive(final Boolean isActive) {
      this.isActive = isActive;
      return this;
    }

    public Insurance build() {
      return new Insurance(this.id, this.customer, this.car, this.creationDate, this.updatedDate, this.isActive);
    }

  }

}
