package br.com.audsat.insurance.api.controllers;

import br.com.audsat.insurance.api.controllers.response.ApiResponse;
import br.com.audsat.insurance.api.core.dto.BudgetDetail;
import br.com.audsat.insurance.api.core.dto.CreateBudgetRequest;
import br.com.audsat.insurance.api.core.dto.CreateBudgetResponse;
import br.com.audsat.insurance.api.core.service.ICreateBudget;
import br.com.audsat.insurance.api.core.service.IGetBudgetDetail;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/budget")
public class InsuranceController {

  private final ICreateBudget createBudget;

  private final IGetBudgetDetail getBudgetDetail;

  public InsuranceController(final ICreateBudget createBudget, final IGetBudgetDetail getBudgetDetail) {
    this.createBudget = createBudget;
    this.getBudgetDetail = getBudgetDetail;
  }

  @PostMapping
  public ResponseEntity<ApiResponse<CreateBudgetResponse>> createBudget(@Valid @RequestBody final CreateBudgetRequest request) {
    final var response = this.createBudget.execute(request);
    final var location = ServletUriComponentsBuilder.fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(response.id())
      .toUri();
    return ResponseEntity.created(location).body(ApiResponse.of(response));
  }

  @GetMapping("/{insuranceId}")
  public ResponseEntity<ApiResponse<BudgetDetail>> getBudgetDetail(@PathVariable final Long insuranceId) {
    final var response = this.getBudgetDetail.execute(insuranceId);
    return ResponseEntity.ok(ApiResponse.of(response));
  }

}
