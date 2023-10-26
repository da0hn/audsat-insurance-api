package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.Car;
import br.com.audsat.insurance.api.core.domain.Customer;
import br.com.audsat.insurance.api.core.domain.Driver;
import br.com.audsat.insurance.api.core.domain.Insurance;
import br.com.audsat.insurance.api.core.dto.UpdateBudgetRequest;
import br.com.audsat.insurance.api.core.exception.BusinessException;
import br.com.audsat.insurance.api.core.shared.ApplicationMessages;
import br.com.audsat.insurance.api.repositories.CustomerRepository;
import br.com.audsat.insurance.api.repositories.InsuranceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

@Tags({ @Tag("unit"), @Tag("all") })
@ExtendWith(MockitoExtension.class)
@DisplayName("Update budget Use Case")
class UpdateBudgetTest {

  UpdateBudget sut;

  @Mock
  GetCustomerById getCustomerById;

  @Mock
  GetInsuranceById getInsuranceById;

  @Mock
  GetCarById getCarById;

  @Mock
  CustomerRepository customerRepository;

  @Mock
  InsuranceRepository repository;

  @Captor
  ArgumentCaptor<Insurance> insuranceArgumentCaptor;

  @BeforeEach
  void setUp() {
    this.sut = new UpdateBudget(
      this.getCustomerById,
      this.getCarById,
      this.getInsuranceById,
      this.customerRepository,
      this.repository
    );
  }

  @Test
  @DisplayName("Should update fields of budget")
  void test1() {

    Mockito.doReturn(
        Insurance.builder()
          .id(1L)
          .creationDate(Instant.now())
          .isActive(false)
          .car(
            Car.builder()
              .id(2L)
              .year("2020")
              .fipeValue(100_000.00)
              .model("Model Test")
              .manufacturer("Manufacturer Test")
              .build()
          )
          .customer(
            Customer.builder()
              .id(3L)
              .name("Customer Test")
              .driver(
                Driver.builder()
                  .id(4L)
                  .birthdate(LocalDate.now().minusYears(20))
                  .document("12345678900")
                  .build()
              )
              .build()
          )
          .build()
      )
      .when(this.getInsuranceById)
      .execute(1L);

    Mockito.doReturn(true)
      .when(this.customerRepository)
      .isDriverOfCar(Mockito.eq(3L), Mockito.eq(2L));

    this.sut.execute(
      new UpdateBudgetRequest(
        1L,
        Optional.empty(),
        Optional.empty(),
        Optional.of(true)
      )
    );

    Mockito.verify(this.repository, Mockito.times(1)).save(this.insuranceArgumentCaptor.capture());
    Mockito.verify(this.getCarById, Mockito.never()).execute(Mockito.anyLong());
    Mockito.verify(this.getCustomerById, Mockito.never()).execute(Mockito.anyLong());
    Mockito.verify(this.customerRepository, Mockito.times(1)).isDriverOfCar(Mockito.eq(3L), Mockito.eq(2L));
    Assertions.assertThat(this.insuranceArgumentCaptor.getValue()).isNotNull();
    Assertions.assertThat(this.insuranceArgumentCaptor.getValue().getIsActive()).isTrue();
    Assertions.assertThat(this.insuranceArgumentCaptor.getValue().getId()).isEqualTo(1L);
    Assertions.assertThat(this.insuranceArgumentCaptor.getValue().getUpdatedDate()).isNotNull();
  }

  @Test
  @DisplayName("Should throw exception if customer is not driver of car")
  void test2() {
    Mockito.doReturn(
        Insurance.builder()
          .id(1L)
          .creationDate(Instant.now())
          .isActive(false)
          .car(
            Car.builder()
              .id(2L)
              .year("2020")
              .fipeValue(100_000.00)
              .model("Model Test")
              .manufacturer("Manufacturer Test")
              .build()
          )
          .customer(
            Customer.builder()
              .id(3L)
              .name("Customer Test")
              .driver(
                Driver.builder()
                  .id(4L)
                  .birthdate(LocalDate.now().minusYears(20))
                  .document("12345678900")
                  .build()
              )
              .build()
          )
          .build()
      )
      .when(this.getInsuranceById)
      .execute(1L);

    Mockito.doReturn(Customer.builder().id(10L).build())
      .when(this.getCustomerById)
      .execute(10L);

    Mockito.doReturn(false)
      .when(this.customerRepository)
      .isDriverOfCar(Mockito.eq(10L), Mockito.eq(2L));

    Assertions.assertThatThrownBy(() -> this.sut.execute(
        new UpdateBudgetRequest(
          1L,
          Optional.of(10L),
          Optional.empty(),
          Optional.of(true)
        )
      ))
      .hasMessage(ApplicationMessages.CAR_DOES_NOT_BELONG_TO_CUSTOMER)
      .isInstanceOf(BusinessException.class);

    Mockito.verify(this.repository, Mockito.never()).save(Mockito.any());
    Mockito.verify(this.getCustomerById, Mockito.times(1)).execute(Mockito.anyLong());
    Mockito.verify(this.customerRepository, Mockito.times(1)).isDriverOfCar(Mockito.eq(10L), Mockito.eq(2L));
    Mockito.verify(this.getCarById, Mockito.never()).execute(Mockito.anyLong());
  }

}
