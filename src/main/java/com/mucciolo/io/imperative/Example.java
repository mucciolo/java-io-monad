package com.mucciolo.io.imperative;

import com.mucciolo.io.common.http.GetAccBalanceHttpRequest;
import com.mucciolo.io.common.http.HttpResponse;

public final class Example {
  public static void main(final String[] args) {

    final AccountRepository accountRepository = new AccountRepository();
    final Controller controller = new Controller(accountRepository);

    final Long accountId = 123L;
    final GetAccBalanceHttpRequest getAccBalanceHttpRequest = new GetAccBalanceHttpRequest(accountId);
    final HttpResponse httpResponse = controller.handle(getAccBalanceHttpRequest);

    System.out.println(httpResponse);

  }
}
