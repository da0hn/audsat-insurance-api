package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.Customer;
import br.com.audsat.insurance.api.core.exception.ResourceNotFoundException;
import br.com.audsat.insurance.api.core.service.IGetResource;
import br.com.audsat.insurance.api.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetCustomerById implements IGetResource<Customer> {

  private final CustomerRepository repository;

  @Override
  public Customer execute(final Long id) {
    return this.repository.findById(id)
      .orElseThrow(ResourceNotFoundException.error("customer.not.found"));
  }

}
