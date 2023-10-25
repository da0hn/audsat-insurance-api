package br.com.audsat.insurance.api.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false)
  private Integer id;

  protected BaseEntity(final Long id) {
    this.id = id;
  }

  protected BaseEntity() { }

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (!(o instanceof final BaseEntity that)) return false;
    return this.id.equals(that.id);
  }

  public Long getId() { return this.id; }

  public void setId(final Long id) {
    this.id = id;
  }

}
