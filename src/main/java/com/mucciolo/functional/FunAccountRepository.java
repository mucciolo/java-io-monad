package com.mucciolo.functional;

import java.util.Optional;

public interface FunAccountRepository {
  IO<Optional<Double>> getBalance(final Long accountId);
}

