package br.com.audsat.insurance.api.core.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateBudgetRequest(

  @NotNull
  @Positive
  Long customerId,
  @NotNull
  @Positive
  Long carId,
  boolean isActive
) { }
