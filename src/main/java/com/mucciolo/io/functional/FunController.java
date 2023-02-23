package com.mucciolo.io.functional;

import com.mucciolo.io.common.http.GetAccBalanceHttpRequest;
import com.mucciolo.io.common.http.HttpResponse;
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
    // tap
    return IO.delay(() -> {
      System.out.printf("%tT | INFO | %s\n", currentTimeMillis(), getAccBalanceHttpRequest);
      return getAccBalanceHttpRequest;
    });
  }

  private IO<HttpResponse> route(final GetAccBalanceHttpRequest getAccBalanceHttpRequest) {
    return accountRepository
        .getBalance(getAccBalanceHttpRequest.accountId())
        .map(maybeBalance ->
            maybeBalance
                .map(balance -> new HttpResponse(200, balance.toString()))
                .orElse(HttpResponse.NOT_FOUND)
        );
  }

}
