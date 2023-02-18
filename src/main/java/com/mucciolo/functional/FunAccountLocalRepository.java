package com.mucciolo.functional;

import java.util.Map;
import java.util.Optional;

public class FunAccountLocalRepository implements FunAccountRepository {

  private final Map<Long, Double> balanceById = Map.of(123L, 50.0);

  public IO<Optional<Double>> getBalance(final Long accountId) {
    return IO.delay(() -> Optional.of(balanceById.get(accountId)));
  }
}