package br.com.audsat.insurance.api.repositories;

import br.com.audsat.insurance.api.core.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @Query(
    """
    select
      case
        when count(customer) > 0 then true
        else false
      end
    from Customer customer
    join customer.driver driver
    join driver.carsDriver carsDriver
    join carsDriver.car car
    where
      customer.id = :customerId and car.id = :carId
    """
  )
  boolean isDriverOfCar(Long customerId, Long carId);

}
