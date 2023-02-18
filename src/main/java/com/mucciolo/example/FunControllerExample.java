package com.mucciolo.example;

import com.mucciolo.common.http.GetAccBalanceHttpRequest;
import com.mucciolo.common.http.HttpResponse;
import com.mucciolo.functional.FunAccountLocalRepository;
import com.mucciolo.functional.FunAccountRepository;
import com.mucciolo.functional.FunController;
import com.mucciolo.functional.IO;

public final class FunControllerExample {
  public static void main(final String[] args) {

    final FunAccountRepository accountRepository = new FunAccountLocalRepository();
    final FunController controller = new FunController(accountRepository);

    final IO<GetAccBalanceHttpRequest> request = IO.pure(new GetAccBalanceHttpRequest(123L));
    final IO<HttpResponse> program = request.flatMap(controller::handle);
    final HttpResponse httpResponse = program.run();

    System.out.println(httpResponse);

  }
}
