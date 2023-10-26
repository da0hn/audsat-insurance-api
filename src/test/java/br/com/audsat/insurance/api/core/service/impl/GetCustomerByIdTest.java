package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.Customer;
import br.com.audsat.insurance.api.core.exception.ResourceNotFoundException;
import br.com.audsat.insurance.api.core.shared.ApplicationMessages;
import br.com.audsat.insurance.api.repositories.CustomerRepository;
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

import java.util.Optional;

@Tags({ @Tag("unit"), @Tag("all") })
@ExtendWith(MockitoExtension.class)
@DisplayName("Test get customer component")
class GetCustomerByIdTest {

  GetCustomerById sut;

  @Mock
  CustomerRepository repository;

  @BeforeEach
  void setUp() {
    this.sut = new GetCustomerById(this.repository);
  }

  @Test
  @DisplayName("Should get customer by id")
  void test1() {
    Mockito.doReturn(Optional.ofNullable(Customer.builder().id(1L).build()))
      .when(this.repository)
      .findById(1L);

    final var result = this.sut.execute(1L);

    Assertions.assertThat(result).isNotNull();
    Assertions.assertThat(result.getId()).isEqualTo(1L);
  }

  @Test
  @DisplayName("Should throw exception when customer not found")
  void test2() {

    Mockito.doReturn(Optional.empty())
      .when(this.repository)
      .findById(1L);

    Assertions.assertThatThrownBy(() -> this.sut.execute(1L))
      .isInstanceOf(ResourceNotFoundException.class)
      .hasMessage(ApplicationMessages.CUSTOMER_NOT_FOUND);
  }

}
