package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.InsuranceBudgetPolicyData;
import br.com.audsat.insurance.api.repositories.DriverRepository;
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
@DisplayName("Test driver claims policy")
class DriverClaimsPolicyTest {

  DriverClaimsPolicy sut;

  @Mock
  DriverRepository driverRepository;

  @BeforeEach
  void setUp() {
    this.sut = new DriverClaimsPolicy(this.driverRepository);
  }

  @Test
  @DisplayName("Should return zero when driver has no claims")
  void test1() {

    Mockito.doReturn(false)
      .when(this.driverRepository)
      .hasClaims(1L);

    final var result = this.sut.execute(
      InsuranceBudgetPolicyData.builder()
        .cardId(1L)
        .fipe(BigDecimal.valueOf(1000))
        .driverId(1L)
        .cardId(1L)
        .today(LocalDate.now())
        .birthDate(LocalDate.now().minusYears(20))
        .build()
    );

    Assertions.assertThat(result).isZero();
  }

  @Test
  @DisplayName("Should return 2% of fipe when driver has claims")
  void test2() {
    Mockito.doReturn(true)
      .when(this.driverRepository)
      .hasClaims(1L);

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
