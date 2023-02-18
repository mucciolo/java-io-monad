package com.mucciolo.example;

import com.mucciolo.imperative.AccountRepository;
import com.mucciolo.common.http.GetAccBalanceHttpRequest;
import com.mucciolo.common.http.HttpResponse;
import com.mucciolo.imperative.AccountLocalRepository;
import com.mucciolo.imperative.Controller;

public final class ControllerExample {
  public static void main(final String[] args) {

    final AccountRepository accountRepository = new AccountLocalRepository();
    final Controller controller = new Controller(accountRepository);

    final GetAccBalanceHttpRequest getAccBalanceHttpRequest = new GetAccBalanceHttpRequest(123L);
    final HttpResponse httpResponse = controller.handle(getAccBalanceHttpRequest);

    System.out.println(httpResponse);

  }
}
