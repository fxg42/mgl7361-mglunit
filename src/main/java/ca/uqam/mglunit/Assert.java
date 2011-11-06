package ca.uqam.mglunit;

public class Assert {
  public static void assertEquals (Object expected, Object actual) throws RuntimeException {
    if (! expected.equals(actual)) throw new RuntimeException();
  }
}
