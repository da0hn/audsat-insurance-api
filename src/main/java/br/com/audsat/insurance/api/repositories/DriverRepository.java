package br.com.audsat.insurance.api.repositories;

import br.com.audsat.insurance.api.core.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
