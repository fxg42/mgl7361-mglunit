package ca.uqam.mglunit;

public class Assert {
  public static void assertEquals (Object expected, Object actual) {
    if (! expected.equals(actual))
      throw new AssertionError(
          String.format("expected:<%s> but was:<%s>", expected, actual));
  }
}
