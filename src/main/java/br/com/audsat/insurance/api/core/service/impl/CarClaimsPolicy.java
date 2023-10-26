package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.InsuranceBudgetPolicyData;
import br.com.audsat.insurance.api.core.service.BudgetCalculationPolicy;
import br.com.audsat.insurance.api.repositories.CarRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CarClaimsPolicy implements BudgetCalculationPolicy {

  private final CarRepository carRepository;

  public CarClaimsPolicy(final CarRepository carRepository) { this.carRepository = carRepository; }

  @Override
  public BigDecimal execute(final InsuranceBudgetPolicyData insuranceBudgetPolicyData) {
    if (!this.carRepository.hasClaims(insuranceBudgetPolicyData.getCardId())) {
      return BigDecimal.ZERO;
    }
    return this.percent(insuranceBudgetPolicyData.getFipe(), 2.0);
  }

}
