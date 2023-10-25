package br.com.audsat.insurance.api.repositories;

import br.com.audsat.insurance.api.core.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
