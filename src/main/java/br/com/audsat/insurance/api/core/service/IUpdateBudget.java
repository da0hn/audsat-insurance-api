package br.com.audsat.insurance.api.core.service;

import br.com.audsat.insurance.api.core.dto.UpdateBudgetRequest;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;

@FunctionalInterface
public interface IUpdateBudget {

  @Transactional
  void execute(@Valid UpdateBudgetRequest request);

}
