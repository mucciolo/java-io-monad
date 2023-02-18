package com.mucciolo.imperative;

import java.util.Map;
import java.util.Optional;

public class AccountLocalRepository implements AccountRepository {

  private final Map<Long, Double> balanceById = Map.of(123L, 50.0);

  public Optional<Double> getBalance(final Long accountId) {
    return Optional.of(balanceById.get(accountId));
  }
}
