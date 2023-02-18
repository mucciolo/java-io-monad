package com.mucciolo.imperative;

import java.util.Optional;

public interface AccountRepository {
  Optional<Double> getBalance(final Long accountId);
}

