package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.Insurance;
import br.com.audsat.insurance.api.core.dto.CreateBudgetRequest;
import br.com.audsat.insurance.api.core.dto.CreateBudgetResponse;
import br.com.audsat.insurance.api.core.exception.BusinessException;
import br.com.audsat.insurance.api.core.service.ICreateBudget;
import br.com.audsat.insurance.api.repositories.CustomerRepository;
import br.com.audsat.insurance.api.repositories.InsuranceRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateBudget implements ICreateBudget {

  private final GetCustomerById getCustomerById;

  private final GetCarById getCarById;

  private final CustomerRepository customerRepository;

  private final InsuranceRepository repository;

  public CreateBudget(
    final GetCustomerById getCustomerById,
    final GetCarById getCarById,
    final CustomerRepository customerRepository,
    final InsuranceRepository repository
  ) {
    this.getCustomerById = getCustomerById;
    this.getCarById = getCarById;
    this.customerRepository = customerRepository;
    this.repository = repository;
  }

  @Override
  @Transactional
  public CreateBudgetResponse execute(@Valid final CreateBudgetRequest request) {

    final var insurance = Insurance.createBudget(
      this.getCarById.execute(request.carId()),
      this.getCustomerById.execute(request.customerId()),
      request.isActive()
    );

    this.ensureCustomerIsMainDriver(request);

    final var newInsurance = this.repository.save(insurance);

    return CreateBudgetResponse.of(newInsurance);
  }

  private void ensureCustomerIsMainDriver(CreateBudgetRequest request) {
    if (!this.customerRepository.isDriverOfCar(request.customerId(), request.carId())) {
      throw new BusinessException("car.does.not.belong.to.customer");
    }
  }

}
