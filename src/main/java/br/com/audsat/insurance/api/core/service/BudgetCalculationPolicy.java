package br.com.audsat.insurance.api.core.service;

import br.com.audsat.insurance.api.core.domain.InsuranceBudgetPolicyData;

import java.math.BigDecimal;
import java.math.RoundingMode;

@FunctionalInterface
public interface BudgetCalculationPolicy {

  BigDecimal execute(InsuranceBudgetPolicyData insuranceBudgetPolicyData);

  default BigDecimal percent(final BigDecimal value, final Double percent) {
    return value.multiply(BigDecimal.valueOf(percent / 100)).setScale(2, RoundingMode.HALF_EVEN);
  }

  default BudgetCalculationPolicy sum(final BudgetCalculationPolicy other) {
    return data -> other.execute(data).add(this.execute(data));
  }

}
