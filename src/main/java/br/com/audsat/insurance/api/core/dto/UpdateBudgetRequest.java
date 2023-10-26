package br.com.audsat.insurance.api.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Optional;

public record UpdateBudgetRequest(

  @NotNull
  @Positive
  @JsonIgnore
  Long insuranceId,
  @Positive
  Optional<Long> customerId,
  @NotNull
  @Positive
  Optional<Long> carId,
  Optional<Boolean> isActive
) {

  public UpdateBudgetRequest withInsuranceId(final Long insuranceId) {
    return new UpdateBudgetRequest(insuranceId, this.customerId, this.carId, this.isActive);
  }

}
