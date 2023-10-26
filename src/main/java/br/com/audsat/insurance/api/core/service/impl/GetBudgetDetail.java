package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.InsuranceBudgetPolicyData;
import br.com.audsat.insurance.api.core.dto.BudgetDetail;
import br.com.audsat.insurance.api.core.service.IBudgetCalculator;
import br.com.audsat.insurance.api.core.service.IGetBudgetDetail;
import org.springframework.stereotype.Component;

@Component
public class GetBudgetDetail implements IGetBudgetDetail {

  private final GetInsuranceById getInsuranceById;

  private final IBudgetCalculator budgetCalculator;

  public GetBudgetDetail(final GetInsuranceById getInsuranceById, final IBudgetCalculator budgetCalculator) {
    this.getInsuranceById = getInsuranceById;
    this.budgetCalculator = budgetCalculator;
  }

  @Override
  public BudgetDetail execute(final Long insuranceId) {
    final var insurance = this.getInsuranceById.execute(insuranceId);
    return BudgetDetail.of(
      insurance,
      this.budgetCalculator.execute(InsuranceBudgetPolicyData.of(insurance))
    );
  }

}
