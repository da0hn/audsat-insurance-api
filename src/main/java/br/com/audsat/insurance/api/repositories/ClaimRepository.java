package br.com.audsat.insurance.api.repositories;

import br.com.audsat.insurance.api.core.domain.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
}
