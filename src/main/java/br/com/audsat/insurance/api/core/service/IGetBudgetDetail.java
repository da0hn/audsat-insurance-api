package br.com.audsat.insurance.api.core.service;

import br.com.audsat.insurance.api.core.dto.BudgetDetail;

@FunctionalInterface
public interface IGetBudgetDetail {

  BudgetDetail execute(Long insuranceId);

}
