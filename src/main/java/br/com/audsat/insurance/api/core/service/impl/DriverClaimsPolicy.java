package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.InsuranceBudgetPolicyData;
import br.com.audsat.insurance.api.core.service.BudgetCalculationPolicy;
import br.com.audsat.insurance.api.repositories.DriverRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DriverClaimsPolicy implements BudgetCalculationPolicy {

  private final DriverRepository driverRepository;

  public DriverClaimsPolicy(final DriverRepository driverRepository) { this.driverRepository = driverRepository; }

  @Override
  public BigDecimal execute(final InsuranceBudgetPolicyData insuranceBudgetPolicyData) {
    if (!this.driverRepository.hasClaims(insuranceBudgetPolicyData.getDriverId())) {
      return BigDecimal.ZERO;
    }
    return this.percent(insuranceBudgetPolicyData.getFipe(), 2.0);
  }

}
