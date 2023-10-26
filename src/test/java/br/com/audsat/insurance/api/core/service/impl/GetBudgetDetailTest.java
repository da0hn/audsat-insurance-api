package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.Car;
import br.com.audsat.insurance.api.core.domain.Customer;
import br.com.audsat.insurance.api.core.domain.Driver;
import br.com.audsat.insurance.api.core.domain.Insurance;
import br.com.audsat.insurance.api.core.service.IBudgetCalculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Tags({ @Tag("unit"), @Tag("all") })
@ExtendWith(MockitoExtension.class)
@DisplayName("Get budget detail Use Case")
class GetBudgetDetailTest {

  GetBudgetDetail sut;

  @Mock
  GetInsuranceById getInsuranceById;

  @Mock
  IBudgetCalculator budgetCalculator;

  @BeforeEach
  void setUp() {
    this.sut = new GetBudgetDetail(this.getInsuranceById, this.budgetCalculator);
  }

  @Test
  @DisplayName("Should get budget detail")
  void test1() {

    Mockito.doReturn(
        Insurance.builder()
          .id(1L)
          .creationDate(Instant.now())
          .isActive(true)
          .car(Car.builder()
                 .id(2L)
                 .year("2020")
                 .fipeValue(100_000.00)
                 .model("Model Test")
                 .manufacturer("Manufacturer Test")
                 .build()
          )
          .customer(Customer.builder()
                      .id(3L)
                      .name("Customer Test")
                      .driver(
                        Driver.builder()
                          .id(4L)
                          .birthdate(LocalDate.now().minusYears(20))
                          .document("12345678900")
                          .build()
                      )
                      .build()
          )
          .build()
      )
      .when(this.getInsuranceById)
      .execute(1L);

    Mockito.doReturn(new BigDecimal("10000.00"))
      .when(this.budgetCalculator)
      .execute(Mockito.any());

    final var result = this.sut.execute(1L);

    Assertions.assertThat(result).isNotNull();
    Assertions.assertThat(result.creationDate()).isNotNull();
    Assertions.assertThat(result.updatedDate()).isNull();
    Assertions.assertThat(result.isActive()).isTrue();
    Assertions.assertThat(result.insuranceId()).isEqualTo(1L);
    Assertions.assertThat(result.budget()).isEqualTo(new BigDecimal("10000.00"));
    Assertions.assertThat(result.car().id()).isEqualTo(2L);
    Assertions.assertThat(result.car().year()).isEqualTo("2020");
    Assertions.assertThat(result.car().fipeValue()).isEqualTo(100_000.00);
    Assertions.assertThat(result.car().model()).isEqualTo("Model Test");
    Assertions.assertThat(result.car().manufacturer()).isEqualTo("Manufacturer Test");
    Assertions.assertThat(result.customer().id()).isEqualTo(3L);
    Assertions.assertThat(result.customer().name()).isEqualTo("Customer Test");
    Assertions.assertThat(result.customer().birthdate()).isEqualTo(LocalDate.now().minusYears(20));
    Assertions.assertThat(result.customer().document()).isEqualTo("12345678900");
  }

}
