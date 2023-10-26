package br.com.audsat.insurance.api.core.dto;

import br.com.audsat.insurance.api.core.domain.Car;
import br.com.audsat.insurance.api.core.domain.Customer;
import br.com.audsat.insurance.api.core.domain.Insurance;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record BudgetDetail(
  Long insuranceId,
  Instant creationDate,
  Instant updatedDate,
  boolean isActive,
  BigDecimal budget,
  BudgetCarDetail car,
  BudgetCustomerDetail customer
) {

  public static BudgetDetail of(final Insurance insurance, final BigDecimal budget) {
    return new BudgetDetail(
      insurance.getId(),
      insurance.getCreationDate(),
      insurance.getUpdatedDate(),
      insurance.getIsActive(),
      budget,
      BudgetCarDetail.of(insurance.getCar()),
      BudgetCustomerDetail.of(insurance.getCustomer())
    );
  }

  public record BudgetCarDetail(Long id, String model, Double fipeValue, String year, String manufacturer) {

    public static BudgetCarDetail of(final Car car) {
      return new BudgetCarDetail(
        car.getId(),
        car.getModel(),
        car.getFipeValue(),
        car.getYear(),
        car.getManufacturer()
      );
    }

  }

  public record BudgetCustomerDetail(Long id, String name, LocalDate birthdate, String document) {

    public static BudgetCustomerDetail of(final Customer customer) {
      return new BudgetCustomerDetail(
        customer.getId(),
        customer.getName(),
        customer.getBirthdate(),
        customer.getDocument()
      );
    }

  }

}
