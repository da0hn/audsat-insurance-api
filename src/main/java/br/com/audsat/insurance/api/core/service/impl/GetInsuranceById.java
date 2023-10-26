package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.Insurance;
import br.com.audsat.insurance.api.core.exception.ResourceNotFoundException;
import br.com.audsat.insurance.api.core.service.IGetResource;
import br.com.audsat.insurance.api.core.shared.ApplicationMessages;
import br.com.audsat.insurance.api.repositories.InsuranceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetInsuranceById implements IGetResource<Insurance> {

  private final InsuranceRepository repository;

  @Override
  public Insurance execute(final Long id) {
    return this.repository.findById(id)
      .orElseThrow(ResourceNotFoundException.error(ApplicationMessages.INSURANCE_NOT_FOUND));
  }

}
