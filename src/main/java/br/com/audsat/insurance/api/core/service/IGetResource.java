package br.com.audsat.insurance.api.core.service;

@FunctionalInterface
public interface IGetResource<T> {

  T execute(Long id);

}
