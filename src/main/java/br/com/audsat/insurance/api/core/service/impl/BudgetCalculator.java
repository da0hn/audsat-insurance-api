package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.InsuranceBudgetPolicyData;
import br.com.audsat.insurance.api.core.service.BudgetCalculationPolicy;
import br.com.audsat.insurance.api.core.service.IBudgetCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class BudgetCalculator implements IBudgetCalculator {

  private final DriverAgePolicy driverAgePolicy;

  private final CarClaimsPolicy carClaimsPolicy;

  private final DriverClaimsPolicy driverClaimsPolicy;

  public BudgetCalculator(
    final DriverAgePolicy driverAgePolicy,
    final CarClaimsPolicy carClaimsPolicy,
    final DriverClaimsPolicy driverClaimsPolicy
  ) {
    this.driverAgePolicy = driverAgePolicy;
    this.carClaimsPolicy = carClaimsPolicy;
    this.driverClaimsPolicy = driverClaimsPolicy;
  }

  @Override
  public BigDecimal execute(final InsuranceBudgetPolicyData insuranceBudgetPolicyData) {

    final BudgetCalculationPolicy initialBudget = (data) -> this.percent(data.getFipe(), 6.0);

    return initialBudget
      .sum(this.driverAgePolicy)
      .sum(this.driverClaimsPolicy)
      .sum(this.carClaimsPolicy)
      .execute(insuranceBudgetPolicyData)
      .setScale(2, RoundingMode.HALF_EVEN);
  }

  private BigDecimal percent(final BigDecimal value, final Double percent) {
    return value.multiply(BigDecimal.valueOf(percent / 100));
  }

}
