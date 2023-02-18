package com.mucciolo.example;

import com.mucciolo.functional.IO;

public final class IOExample {

  public static void main(final String[] args) {

    final Integer input = 1;
    final IO<Double> program = IO.pure(input)
        .flatMap(n -> IO.pure(n + 1)) // 2
        .flatMap(n -> IO.delay(() -> 2 * n)) // 4
        .map(Integer::doubleValue); // 4.0
    final Double output = program.run();

    System.out.println(output);
  }
}