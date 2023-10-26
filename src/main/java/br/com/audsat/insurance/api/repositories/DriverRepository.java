package br.com.audsat.insurance.api.repositories;

import br.com.audsat.insurance.api.core.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DriverRepository extends JpaRepository<Driver, Long> {

  @Query("""
         select
         case
           when count(driver) > 0 then true
           else false
         end
         from Driver driver
         join driver.claims claims
         where driver.id = :driverId
         """
  )
  boolean hasClaims(Long driverId);

}
