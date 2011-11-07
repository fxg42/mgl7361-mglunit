package ca.uqam.mglunit;

import org.junit.*;
import static org.junit.Assert.*;

public class TestCliParsing {

  @org.junit.Test public void it_prints_the_help () throws Exception {
    String[] args = { "--help" };
    Runner.main(args);
  }

  @org.junit.Test public void it_runs_with_defaults () throws Exception {
    String[] args = { "samples.SimpleTestWithAssert" };
    Runner.main(args);
  }
}
