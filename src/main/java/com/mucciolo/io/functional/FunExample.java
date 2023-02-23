package com.mucciolo.io.functional;

import com.mucciolo.io.common.http.GetAccBalanceHttpRequest;
import com.mucciolo.io.common.http.HttpResponse;
import com.mucciolo.io.IO;

import java.util.function.Function;

public final class FunExample {
  public static void main(final String[] args) {

    final IO<Long> accId = IO.pure(123L);
    final IO<GetAccBalanceHttpRequest> request = accId.map(GetAccBalanceHttpRequest::new);
    final Function<GetAccBalanceHttpRequest, IO<HttpResponse>> program = buildProgram();
    final IO<HttpResponse> programAppliedToRequest = request.flatMap(program);
    final HttpResponse response = programAppliedToRequest.run();

    System.out.println(response);

  }

  private static Function<GetAccBalanceHttpRequest, IO<HttpResponse>> buildProgram() {

    final FunAccountRepository accountRepository = new FunAccountRepository();
    final FunController controller = new FunController(accountRepository);

    return controller::handle; // closure
  }
}
