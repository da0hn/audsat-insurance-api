package br.com.audsat.insurance.api.repositories;

import br.com.audsat.insurance.api.core.domain.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}
