package com.mucciolo.imperative;

import com.mucciolo.common.http.GetAccBalanceHttpRequest;
import com.mucciolo.common.http.HttpResponse;

import static java.lang.System.currentTimeMillis;

public class Controller {

  private final AccountRepository accountRepository;

  public Controller(final AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public HttpResponse handle(final GetAccBalanceHttpRequest getAccBalanceHttpRequest) {
    log(getAccBalanceHttpRequest);
    return route(getAccBalanceHttpRequest);
  }

  private void log(final GetAccBalanceHttpRequest getAccBalanceHttpRequest) {
    System.out.printf("%tT | INFO | %s\n", currentTimeMillis(), getAccBalanceHttpRequest);
  }

  private HttpResponse route(final GetAccBalanceHttpRequest getAccBalanceHttpRequest) {
    return accountRepository.getBalance(getAccBalanceHttpRequest.accountId())
        .map(balance -> new HttpResponse(200, balance.toString()))
        .orElse(HttpResponse.NOT_FOUND);
  }

}
