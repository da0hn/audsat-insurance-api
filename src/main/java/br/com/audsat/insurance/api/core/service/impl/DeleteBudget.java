package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.service.IDeleteBudget;
import br.com.audsat.insurance.api.repositories.InsuranceRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteBudget implements IDeleteBudget {

  private final GetInsuranceById getInsuranceById;

  private final InsuranceRepository repository;

  public DeleteBudget(

    final GetInsuranceById getInsuranceById,
    final InsuranceRepository repository
  ) {
    this.getInsuranceById = getInsuranceById;
    this.repository = repository;
  }

  @Override
  @Transactional
  public void execute(final Long insuranceId) {
    final var insurance = this.getInsuranceById.execute(insuranceId);
    this.repository.delete(insurance);
  }

}
