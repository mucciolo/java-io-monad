package com.mucciolo.io.properties;

import com.mucciolo.io.IO;

import java.util.function.Function;

public interface IOMonadLaws {

  static <A, B> boolean pureIsFlatMapLeftIdentity(final A a, final Function<A, IO<B>> f) {

    final IO<B> pureBeforeFlatMap = IO.pure(a).flatMap(f);
    final IO<B> straightFunctionApplication = f.apply(a);

    return pureBeforeFlatMap.equals(straightFunctionApplication);
  }

  static <A> boolean pureIsFlatMapRightIdentity(final IO<A> ma) {

    final IO<A> flatMapBeforePure = ma.flatMap(IO::pure);
    final IO<A> sameMonad = ma;

    return flatMapBeforePure.equals(sameMonad);
  }

  static <A, B, C> boolean flatMapAssociativity(
      final IO<A> ma, final Function<A, IO<B>> f, final Function<B, IO<C>> g
  ) {

    final IO<C> fBeforeG = ma.flatMap(f).flatMap(g); // (ma f) g
    final Function<A, IO<C>> fAndThenG = a -> f.apply(a).flatMap(g);  // (f g)
    final IO<C> fTogetherWithG = ma.flatMap(fAndThenG); // ma (f g)

    return fBeforeG.equals(fTogetherWithG);

  }

}
