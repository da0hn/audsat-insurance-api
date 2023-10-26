package br.com.audsat.insurance.api.core.service;

import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;

@FunctionalInterface
public interface IDeleteBudget {

  @Transactional
  void execute(@Valid Long insuranceId);

}
