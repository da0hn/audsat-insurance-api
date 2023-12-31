package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.Customer;
import br.com.audsat.insurance.api.core.exception.ResourceNotFoundException;
import br.com.audsat.insurance.api.core.service.IGetResource;
import br.com.audsat.insurance.api.core.shared.ApplicationMessages;
import br.com.audsat.insurance.api.repositories.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class GetCustomerById implements IGetResource<Customer> {

  private final CustomerRepository repository;

  public GetCustomerById(final CustomerRepository repository) {
    this.repository = repository;
  }

  @Override
  public Customer execute(final Long id) {
    return this.repository.findById(id)
      .orElseThrow(ResourceNotFoundException.error(ApplicationMessages.CUSTOMER_NOT_FOUND));
  }

}
