package br.com.audsat.insurance.api.repositories;

import br.com.audsat.insurance.api.core.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
