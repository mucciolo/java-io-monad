package com.mucciolo.io.common.eval;

import java.util.function.Function;

@FunctionalInterface
public interface Lazy<T> {

    T eval();

    default <R> Lazy<R> andThen(final Function<? super T, ? extends R> after) {
      return () -> after.apply(eval());
    }

}
