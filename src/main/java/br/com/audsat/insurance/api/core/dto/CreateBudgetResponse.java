package br.com.audsat.insurance.api.core.dto;

import br.com.audsat.insurance.api.core.domain.Insurance;

public record CreateBudgetResponse(
  Long id
) {

  public static CreateBudgetResponse of(final Insurance insurance) {
    return new CreateBudgetResponse(insurance.getId());
  }

}
