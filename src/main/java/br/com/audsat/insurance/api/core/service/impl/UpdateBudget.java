package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.Insurance;
import br.com.audsat.insurance.api.core.dto.UpdateBudgetRequest;
import br.com.audsat.insurance.api.core.exception.BusinessException;
import br.com.audsat.insurance.api.core.service.IUpdateBudget;
import br.com.audsat.insurance.api.repositories.CustomerRepository;
import br.com.audsat.insurance.api.repositories.InsuranceRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateBudget implements IUpdateBudget {

  private final GetCustomerById getCustomerById;

  private final GetCarById getCarById;

  private final GetInsuranceById getInsuranceById;

  private final CustomerRepository customerRepository;

  private final InsuranceRepository repository;

  public UpdateBudget(
    final GetCustomerById getCustomerById,
    final GetCarById getCarById,
    final GetInsuranceById getInsuranceById,
    final CustomerRepository customerRepository,
    final InsuranceRepository repository
  ) {
    this.getCustomerById = getCustomerById;
    this.getCarById = getCarById;
    this.getInsuranceById = getInsuranceById;
    this.customerRepository = customerRepository;
    this.repository = repository;
  }

  @Override
  @Transactional
  public void execute(@Valid final UpdateBudgetRequest request) {
    final var insurance = this.getInsuranceById.execute(request.insuranceId());

    request.customerId().map(this.getCustomerById::execute).ifPresent(insurance::setCustomer);
    request.carId().map(this.getCarById::execute).ifPresent(insurance::setCar);
    request.isActive().ifPresent(insurance::setIsActive);

    insurance.updated();

    this.ensureCustomerIsMainDriver(insurance);

    this.repository.save(insurance);
  }

  private void ensureCustomerIsMainDriver(final Insurance request) {
    if (!this.customerRepository.isDriverOfCar(request.getCustomer().getId(), request.getCar().getId())) {
      throw new BusinessException("car.does.not.belong.to.customer");
    }
  }

}
