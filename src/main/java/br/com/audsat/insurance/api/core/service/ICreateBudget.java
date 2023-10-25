package br.com.audsat.insurance.api.core.service;

import br.com.audsat.insurance.api.core.dto.CreateBudgetRequest;
import br.com.audsat.insurance.api.core.dto.CreateBudgetResponse;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;

@FunctionalInterface
public interface ICreateBudget {

  @Transactional
  CreateBudgetResponse execute(@Valid CreateBudgetRequest request);

}
