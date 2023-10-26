package br.com.audsat.insurance.api.repositories;

import br.com.audsat.insurance.api.core.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Car, Long> {

  @Query(
    """
    select
    case
      when count(car) > 0 then true
      else false
    end
    from Car car
    join car.claims claims
    where car.id = :id
    """
  )
  boolean hasClaims(Long id);

}
