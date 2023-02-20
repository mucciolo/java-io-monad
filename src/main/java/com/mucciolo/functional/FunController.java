package com.mucciolo.functional;

import com.mucciolo.common.http.GetAccBalanceHttpRequest;
import com.mucciolo.common.http.HttpResponse;
import com.mucciolo.io.IO;

import static java.lang.System.currentTimeMillis;

public class FunController {

  private final FunAccountRepository accountRepository;

  public FunController(final FunAccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public IO<HttpResponse> handle(final GetAccBalanceHttpRequest getAccBalanceHttpRequest) {
    return log(getAccBalanceHttpRequest).flatMap(this::route);
  }

  private IO<GetAccBalanceHttpRequest> log(final GetAccBalanceHttpRequest getAccBalanceHttpRequest) {
    return IO.delay(() -> {
      System.out.printf("%tT | INFO | %s\n", currentTimeMillis(), getAccBalanceHttpRequest);
      return getAccBalanceHttpRequest;
    });
  }

  private IO<HttpResponse> route(final GetAccBalanceHttpRequest getAccBalanceHttpRequest) {
    return accountRepository
        .getBalance(getAccBalanceHttpRequest.accountId())
        .flatMap(maybeBalance -> IO.pure(
                maybeBalance
                    .map(balance -> new HttpResponse(200, balance.toString()))
                    .orElse(HttpResponse.NOT_FOUND)
            )
        );
  }

}
