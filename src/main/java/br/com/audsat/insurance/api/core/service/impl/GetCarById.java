package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.Car;
import br.com.audsat.insurance.api.core.exception.ResourceNotFoundException;
import br.com.audsat.insurance.api.core.service.IGetResource;
import br.com.audsat.insurance.api.repositories.CarRepository;
import org.springframework.stereotype.Component;

@Component
public class GetCarById implements IGetResource<Car> {

  private final CarRepository repository;

  public GetCarById(final CarRepository repository) { this.repository = repository; }

  @Override
  public Car execute(final Long id) {
    return this.repository.findById(id)
      .orElseThrow(ResourceNotFoundException.error("car.not.found"));
  }

}
