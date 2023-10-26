package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.InsuranceBudgetPolicyData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

@Tags({ @Tag("unit"), @Tag("all") })
@ExtendWith(MockitoExtension.class)
@DisplayName("Test driver age policy")
class DriverAgePolicyTest {

  private DriverAgePolicy sut;

  @BeforeEach
  void setUp() {
    this.sut = new DriverAgePolicy();
  }

  @Test
  @DisplayName("Should return zero when driver is under 18 years old")
  void test1() {

    final var result = this.sut.execute(
      InsuranceBudgetPolicyData.builder()
        .cardId(1L)
        .fipe(BigDecimal.valueOf(1000))
        .driverId(1L)
        .cardId(1L)
        .today(LocalDate.now())
        .birthDate(LocalDate.now().minusYears(17))
        .build()
    );

    Assertions.assertThat(result).isZero();
  }

  @Test
  @DisplayName("Should return zero when driver is over 25 years old")
  void test2() {

    final var result = this.sut.execute(
      InsuranceBudgetPolicyData.builder()
        .cardId(1L)
        .fipe(BigDecimal.valueOf(1000))
        .driverId(1L)
        .cardId(1L)
        .today(LocalDate.now())
        .birthDate(LocalDate.now().minusYears(26))
        .build()
    );
    Assertions.assertThat(result).isZero();
  }

  @Test
  @DisplayName("Should return 2% of fipe when driver is between 18 and 25 years old")
  void test3() {
    final var result = this.sut.execute(
      InsuranceBudgetPolicyData.builder()
        .cardId(1L)
        .fipe(BigDecimal.valueOf(100_000))
        .driverId(1L)
        .cardId(1L)
        .today(LocalDate.now())
        .birthDate(LocalDate.now().minusYears(20))
        .build()
    );
    Assertions.assertThat(result).isEqualTo(new BigDecimal("2000.00"));
  }

}
