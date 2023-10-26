package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.InsuranceBudgetPolicyData;
import br.com.audsat.insurance.api.core.service.BudgetCalculationPolicy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DriverAgePolicy implements BudgetCalculationPolicy {

  private static final int TWENTY_FIVE_YEARS_OLD = 25;

  private static final int EIGHTEEN_YEARS_OLD = 18;

  @Override
  public BigDecimal execute(final InsuranceBudgetPolicyData insuranceBudgetPolicyData) {
    final var age = insuranceBudgetPolicyData.age();
    if (age < EIGHTEEN_YEARS_OLD || age > TWENTY_FIVE_YEARS_OLD) {
      return BigDecimal.ZERO;
    }
    return this.percent(insuranceBudgetPolicyData.getFipe(), 2.0);
  }

}
