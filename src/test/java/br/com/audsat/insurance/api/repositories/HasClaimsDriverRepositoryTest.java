package br.com.audsat.insurance.api.repositories;

import br.com.audsat.insurance.api.core.domain.Car;
import br.com.audsat.insurance.api.core.domain.Claim;
import br.com.audsat.insurance.api.core.domain.Customer;
import br.com.audsat.insurance.api.core.domain.Driver;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
@Tags({ @Tag("repository"), @Tag("all") })
@DisplayName("Tests hasClaims query from DriverRepository")
class HasClaimsDriverRepositoryTest {

  @Autowired
  CarRepository carRepository;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  DriverRepository repository;

  @Autowired
  ClaimRepository claimRepository;

  @AfterEach
  void tearDown() {
    this.carRepository.deleteAll();
    this.customerRepository.deleteAll();
    this.repository.deleteAll();
    this.claimRepository.deleteAll();
  }

  @Test
  @DisplayName("Should return true when driver has claims")
  void test1() {

    final var car = Car.builder()
      .model("model")
      .fipeValue(100_000.0)
      .year("2023")
      .manufacturer("manufacturer")
      .build();
    this.carRepository.save(car);

    final var driver = Driver.builder()
      .birthdate(LocalDate.now().minusYears(23))
      .document("12345678900")
      .build();

    this.repository.save(driver);

    this.customerRepository.save(
      Customer.builder()
        .name("customer")
        .driver(driver)
        .build());

    final var today = LocalDate.now();
    final var claim = new Claim(null, car, driver, today);

    this.claimRepository.save(claim);

    final var hasClaims = this.repository.hasClaims(driver.getId());

    Assertions.assertThat(hasClaims).isTrue();
  }

  @Test
  @DisplayName("Should return false when the driver has no claims")
  void test2() {

    final var car = Car.builder()
      .model("model")
      .fipeValue(100_000.0)
      .year("2023")
      .manufacturer("manufacturer")
      .build();
    this.carRepository.save(car);

    final var driver = Driver.builder()
      .birthdate(LocalDate.now().minusYears(23))
      .document("12345678900")
      .build();

    this.repository.save(driver);

    this.customerRepository.save(
      Customer.builder()
        .name("customer")
        .driver(driver)
        .build());

    final var hasClaims = this.repository.hasClaims(driver.getId());

    Assertions.assertThat(hasClaims).isFalse();
  }

  @Test
  @DisplayName("Should return false when the driver not exists")
  void test3() {
    final var hasClaims = this.repository.hasClaims(999L);
    Assertions.assertThat(hasClaims).isFalse();
  }

}
