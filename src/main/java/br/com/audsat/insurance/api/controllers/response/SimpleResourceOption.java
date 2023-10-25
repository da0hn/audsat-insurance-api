package br.com.audsat.insurance.api.controllers.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SimpleResourceOption implements ResourceOption {

  Long id;
  String name;

  public static ResourceOption of(final Long id, final String name) {
    return new SimpleResourceOption(id, name);
  }
}
