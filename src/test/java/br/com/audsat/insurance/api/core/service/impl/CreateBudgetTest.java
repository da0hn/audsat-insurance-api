package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.Car;
import br.com.audsat.insurance.api.core.domain.Customer;
import br.com.audsat.insurance.api.core.domain.Insurance;
import br.com.audsat.insurance.api.core.dto.CreateBudgetRequest;
import br.com.audsat.insurance.api.core.exception.BusinessException;
import br.com.audsat.insurance.api.core.exception.ResourceNotFoundException;
import br.com.audsat.insurance.api.repositories.CustomerRepository;
import br.com.audsat.insurance.api.repositories.InsuranceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@Tags({ @Tag("unit") })
@ExtendWith(MockitoExtension.class)
@DisplayName("Create budget Use Case")
class CreateBudgetTest {

  private CreateBudget createBudget;

  @Mock
  private GetCarById getCarById;

  @Mock
  private GetCustomerById getCustomerById;

  @Mock
  private InsuranceRepository repository;

  @Mock
  private CustomerRepository customerRepository;

  @BeforeEach
  void setUp() {
    this.createBudget = new CreateBudget(
      this.getCustomerById,
      this.getCarById,
      this.customerRepository,
      this.repository
    );
  }

  @Test
  @DisplayName("Should create a budget")
  void test1() {

    Mockito.doReturn(
        Car.builder()
          .id(1L)
          .model("Model Test")
          .build()
      )
      .when(this.getCarById)
      .execute(1L);

    Mockito.doReturn(
        Customer.builder()
          .name("Customer Test")
          .build()
      )
      .when(this.getCustomerById)
      .execute(1L);

    Mockito.doReturn(true)
      .when(this.customerRepository)
      .isDriverOfCar(1L, 1L);

    Mockito.doReturn(
        Insurance.builder()
          .id(1L)
          .build()
      )
      .when(this.repository)
      .save(Mockito.any());

    final var response = this.createBudget.execute(
      new CreateBudgetRequest(
        1L,
        1L,
        true
      )
    );

    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.id()).isNotNull().isEqualTo(1L);
  }

  @Test
  @DisplayName("Should throw exception when car not found")
  void test2() {

    Mockito.doThrow(new ResourceNotFoundException("car.not.found"))
      .when(this.getCarById)
      .execute(1L);

    Assertions.assertThatThrownBy(() -> this.createBudget.execute(new CreateBudgetRequest(1L, 1L, true)))
      .hasMessage("car.not.found")
      .isInstanceOf(ResourceNotFoundException.class);
  }

  @Test
  @DisplayName("Should throw exception when customer not found")
  void test3() {

    Mockito.doReturn(
        Car.builder()
          .id(1L)
          .model("Model Test")
          .build()
      )
      .when(this.getCarById)
      .execute(1L);

    Mockito.doThrow(new ResourceNotFoundException("customer.not.found"))
      .when(this.getCustomerById)
      .execute(1L);

    Assertions.assertThatThrownBy(() -> this.createBudget.execute(new CreateBudgetRequest(1L, 1L, true)))
      .hasMessage("customer.not.found")
      .isInstanceOf(ResourceNotFoundException.class);
  }

  @Test
  @DisplayName("Should throw exception when customer is not driver of car")
  void test4() {

    Mockito.doReturn(
        Car.builder()
          .id(1L)
          .model("Model Test")
          .build()
      )
      .when(this.getCarById)
      .execute(1L);

    Mockito.doReturn(
        Customer.builder()
          .name("Customer Test")
          .build()
      )
      .when(this.getCustomerById)
      .execute(1L);

    Mockito.doReturn(false)
      .when(this.customerRepository)
      .isDriverOfCar(1L, 1L);

    Assertions.assertThatThrownBy(() -> this.createBudget.execute(new CreateBudgetRequest(1L, 1L, true)))
      .hasMessage("car.does.not.belong.to.customer")
      .isInstanceOf(BusinessException.class);

  }

}
