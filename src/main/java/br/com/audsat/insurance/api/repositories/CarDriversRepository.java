package br.com.audsat.insurance.api.repositories;

import br.com.audsat.insurance.api.core.domain.CarDrivers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDriversRepository extends JpaRepository<CarDrivers, Long> {
}
