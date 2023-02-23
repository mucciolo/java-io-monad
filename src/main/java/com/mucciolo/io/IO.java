package com.mucciolo.io;

import com.mucciolo.io.common.eval.Lazy;

import java.util.function.Function;

public class IO<A> {

  private final Lazy<A> value;

  private IO(final Lazy<A> value) {
    this.value = value;
  }

  public static <A> IO<A> delay(final Lazy<A> value) {
    return new IO<>(value);
  }

  public A run() {
    return value.eval();
  }

  // monad constructor (aka unit)
  public static <A> IO<A> pure(final A a) {
    return IO.delay(() -> a);
  }

  // monad bind (aka chain or join-map)
  public <B> IO<B> flatMap(final Function<A, IO<B>> f) {
    return IO.delay(value.andThen(f).andThen(IO::run));
  }

  public <B> IO<B> map(final Function<A, B> f) {
    return flatMap(f.andThen(IO::pure));
  }

}
