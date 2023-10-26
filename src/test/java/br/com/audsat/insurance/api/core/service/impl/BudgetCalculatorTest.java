package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.InsuranceBudgetPolicyData;
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
import java.time.LocalDate;

@Tags({ @Tag("unit"), @Tag("all") })
@ExtendWith(MockitoExtension.class)
@DisplayName("Test budget calculator")
class BudgetCalculatorTest {

  BudgetCalculator sut;

  @Mock
  CarClaimsPolicy carClaimsPolicy;

  @Mock
  DriverAgePolicy driverAgePolicy;

  @Mock
  DriverClaimsPolicy driverClaimsPolicy;

  @BeforeEach
  void setUp() {
    this.sut = new BudgetCalculator(
      this.driverAgePolicy,
      this.carClaimsPolicy,
      this.driverClaimsPolicy
    );
  }

  @Test
  @DisplayName("Should apply all policies and return 12% of fipe")
  void test1() {

    Mockito.doReturn(BigDecimal.valueOf(2_000))
      .when(this.carClaimsPolicy)
      .execute(Mockito.any(InsuranceBudgetPolicyData.class));

    Mockito.doReturn(BigDecimal.valueOf(2_000))
      .when(this.driverClaimsPolicy)
      .execute(Mockito.any(InsuranceBudgetPolicyData.class));

    Mockito.doReturn(BigDecimal.valueOf(2_000))
      .when(this.driverAgePolicy)
      .execute(Mockito.any(InsuranceBudgetPolicyData.class));

    final var result = this.sut.execute(
      InsuranceBudgetPolicyData.builder()
        .fipe(BigDecimal.valueOf(100_000))
        .driverId(1L)
        .cardId(1L)
        .today(LocalDate.now())
        .birthDate(LocalDate.now().minusYears(20))
        .build()
    );

    Assertions.assertThat(result).isEqualTo(new BigDecimal("12000.00"));
  }

  @Test
  @DisplayName("Should apply two policies and return 10% of fipe")
  void test2() {
    Mockito.doReturn(BigDecimal.ZERO)
      .when(this.carClaimsPolicy)
      .execute(Mockito.any(InsuranceBudgetPolicyData.class));

    Mockito.doReturn(BigDecimal.valueOf(2_000))
      .when(this.driverClaimsPolicy)
      .execute(Mockito.any(InsuranceBudgetPolicyData.class));

    Mockito.doReturn(BigDecimal.valueOf(2_000))
      .when(this.driverAgePolicy)
      .execute(Mockito.any(InsuranceBudgetPolicyData.class));

    final var result = this.sut.execute(
      InsuranceBudgetPolicyData.builder()
        .fipe(BigDecimal.valueOf(100_000))
        .driverId(1L)
        .cardId(1L)
        .today(LocalDate.now())
        .birthDate(LocalDate.now().minusYears(20))
        .build()
    );

    Assertions.assertThat(result).isEqualTo(new BigDecimal("10000.00"));
  }

  @Test
  @DisplayName("Should apply one policy and return 8% of fipe")
  void test3() {

    Mockito.doReturn(BigDecimal.ZERO)
      .when(this.carClaimsPolicy)
      .execute(Mockito.any(InsuranceBudgetPolicyData.class));

    Mockito.doReturn(BigDecimal.ZERO)
      .when(this.driverClaimsPolicy)
      .execute(Mockito.any(InsuranceBudgetPolicyData.class));

    Mockito.doReturn(BigDecimal.valueOf(2_000))
      .when(this.driverAgePolicy)
      .execute(Mockito.any(InsuranceBudgetPolicyData.class));

    final var result = this.sut.execute(
      InsuranceBudgetPolicyData.builder()
        .fipe(BigDecimal.valueOf(100_000))
        .driverId(1L)
        .cardId(1L)
        .today(LocalDate.now())
        .birthDate(LocalDate.now().minusYears(20))
        .build()
    );

    Assertions.assertThat(result).isEqualTo(new BigDecimal("8000.00"));
  }

  @Test
  @DisplayName("Should not apply extra policy and return 6% of fipe")
  void test4() {

    Mockito.doReturn(BigDecimal.ZERO)
      .when(this.carClaimsPolicy)
      .execute(Mockito.any(InsuranceBudgetPolicyData.class));

    Mockito.doReturn(BigDecimal.ZERO)
      .when(this.driverClaimsPolicy)
      .execute(Mockito.any(InsuranceBudgetPolicyData.class));

    Mockito.doReturn(BigDecimal.ZERO)
      .when(this.driverAgePolicy)
      .execute(Mockito.any(InsuranceBudgetPolicyData.class));

    final var result = this.sut.execute(
      InsuranceBudgetPolicyData.builder()
        .fipe(BigDecimal.valueOf(100_000))
        .driverId(1L)
        .cardId(1L)
        .today(LocalDate.now())
        .birthDate(LocalDate.now().minusYears(20))
        .build()
    );

    Assertions.assertThat(result).isEqualTo(new BigDecimal("6000.00"));
  }

}
