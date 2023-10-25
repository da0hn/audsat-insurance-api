package br.com.audsat.insurance.api.controllers;

import br.com.audsat.insurance.api.controllers.response.ApiResponse;
import br.com.audsat.insurance.api.core.dto.CreateBudgetRequest;
import br.com.audsat.insurance.api.core.dto.CreateBudgetResponse;
import br.com.audsat.insurance.api.core.service.ICreateBudget;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/budget")
public class InsuranceController {

  private final ICreateBudget createBudget;

  public InsuranceController(final ICreateBudget createBudget) { this.createBudget = createBudget; }

  @PostMapping
  public ResponseEntity<ApiResponse<CreateBudgetResponse>> createBudget(@Valid @RequestBody final CreateBudgetRequest request) {
    final var response = this.createBudget.execute(request);
    final var location = ServletUriComponentsBuilder.fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(response.id())
      .toUri();
    return ResponseEntity.created(location).body(ApiResponse.of(response));
  }

}
