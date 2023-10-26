package br.com.audsat.insurance.api.core.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public class InsuranceBudgetPolicyData {

  private final BigDecimal fipe;

  private final LocalDate today;

  private final LocalDate birthDate;

  private final Long cardId;

  private final Long driverId;

  public InsuranceBudgetPolicyData(
    final BigDecimal fipe,
    final LocalDate today,
    final LocalDate birthDate,
    final Long cardId,
    final Long driverId
  ) {
    this.fipe = Objects.requireNonNull(fipe).setScale(2, RoundingMode.HALF_EVEN);
    this.today = Objects.requireNonNull(today);
    this.birthDate = Objects.requireNonNull(birthDate);
    this.cardId = Objects.requireNonNull(cardId);
    this.driverId = Objects.requireNonNull(driverId);
  }

  public static InsuranceBudgetPolicyData of(final Insurance insurance) {
    return new InsuranceBudgetPolicyData(
      BigDecimal.valueOf(insurance.getCar().getFipeValue()),
      LocalDate.now(),
      insurance.getCustomer().getBirthdate(),
      insurance.getCar().getId(),
      insurance.getCustomer().getDriver().getId()
    );
  }

  public static InsuranceBudgetPolicyDataBuilder builder() { return new InsuranceBudgetPolicyDataBuilder(); }

  public Integer age() {
    return this.today.getYear() - this.birthDate.getYear();
  }

  public BigDecimal getFipe() {
    return this.fipe;
  }

  public Long getCardId() {
    return this.cardId;
  }

  public Long getDriverId() {
    return this.driverId;
  }

  public static class InsuranceBudgetPolicyDataBuilder {

    private BigDecimal fipe;

    private LocalDate today;

    private LocalDate birthDate;

    private Long cardId;

    private Long driverId;

    InsuranceBudgetPolicyDataBuilder() { }

    public InsuranceBudgetPolicyDataBuilder fipe(final BigDecimal fipe) {
      this.fipe = fipe;
      return this;
    }

    public InsuranceBudgetPolicyDataBuilder today(final LocalDate today) {
      this.today = today;
      return this;
    }

    public InsuranceBudgetPolicyDataBuilder birthDate(final LocalDate birthDate) {
      this.birthDate = birthDate;
      return this;
    }

    public InsuranceBudgetPolicyDataBuilder cardId(final Long cardId) {
      this.cardId = cardId;
      return this;
    }

    public InsuranceBudgetPolicyDataBuilder driverId(final Long driverId) {
      this.driverId = driverId;
      return this;
    }

    public InsuranceBudgetPolicyData build() {
      return new InsuranceBudgetPolicyData(this.fipe, this.today, this.birthDate, this.cardId, this.driverId);
    }

  }

}
