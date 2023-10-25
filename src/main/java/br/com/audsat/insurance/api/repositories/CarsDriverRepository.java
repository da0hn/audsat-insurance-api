package br.com.audsat.insurance.api.repositories;

import br.com.audsat.insurance.api.core.domain.CarsDriver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsDriverRepository extends JpaRepository<CarsDriver, Long> {
}
