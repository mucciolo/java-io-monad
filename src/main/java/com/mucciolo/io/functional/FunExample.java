package com.mucciolo.io.functional;

import com.mucciolo.io.common.http.GetAccBalanceHttpRequest;
import com.mucciolo.io.common.http.HttpResponse;
import com.mucciolo.io.IO;

import java.util.function.Function;

public final class FunExample {
  public static void main(final String[] args) {

    final Function<Long, IO<HttpResponse>> program = buildProgram();
    final IO<Long> input = IO.pure(123L);
    final HttpResponse output = input.flatMap(program).run();

    System.out.println(output);

  }

  private static Function<Long, IO<HttpResponse>> buildProgram() {

    final FunAccountRepository accountRepository = new FunAccountRepository();
    final FunController controller = new FunController(accountRepository);

    return input -> {
      final IO<GetAccBalanceHttpRequest> request = IO.pure(new GetAccBalanceHttpRequest(input));
      return request.flatMap(controller::handle);
    };
  }
}
