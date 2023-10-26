package br.com.audsat.insurance.api.core.service;

import br.com.audsat.insurance.api.core.domain.InsuranceBudgetPolicyData;

import java.math.BigDecimal;

@FunctionalInterface
public interface IBudgetCalculator {

  BigDecimal execute(InsuranceBudgetPolicyData insuranceBudgetPolicyData);

}
