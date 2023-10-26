package br.com.audsat.insurance.api.core.service.impl;

import br.com.audsat.insurance.api.core.domain.Insurance;
import br.com.audsat.insurance.api.repositories.InsuranceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@Tags({ @Tag("unit"), @Tag("all") })
@ExtendWith(MockitoExtension.class)
@DisplayName("Delete budget Use Case")
class DeleteBudgetTest {

  @Mock
  InsuranceRepository insuranceRepository;

  @Mock
  GetInsuranceById getInsuranceById;

  @Captor
  ArgumentCaptor<Insurance> insuranceArgumentCaptor;

  private DeleteBudget sut;

  @BeforeEach
  void setUp() {
    this.sut = new DeleteBudget(
      this.getInsuranceById,
      this.insuranceRepository
    );
  }

  @Test
  @DisplayName("Should delete a budget")
  void test1() {

    final var insurance = Insurance.builder().id(1L).build();

    Mockito.doReturn(insurance)
      .when(this.getInsuranceById)
      .execute(1L);

    Mockito.doNothing()
      .when(this.insuranceRepository)
      .delete(Mockito.any(Insurance.class));

    this.sut.execute(1L);

    Mockito.verify(this.insuranceRepository, Mockito.times(1))
      .delete(this.insuranceArgumentCaptor.capture());
    Mockito.verify(this.getInsuranceById, Mockito.times(1))
      .execute(Mockito.eq(1L));
    Assertions.assertThat(this.insuranceArgumentCaptor.getValue())
      .isEqualTo(insurance);
  }

}
