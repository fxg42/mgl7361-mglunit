package ca.uqam.mglunit;

import org.junit.*;
import static org.junit.Assert.*; 

public class TestSimpleTestCase {
  
  @org.junit.Test public void it_finds_all_individual_test_methods () {
    TestRunner runner = new TestRunner();
    int retval = runner.run("samples.SimpleTest");

    assertEquals(0, retval);
    assertEquals(3, runner.getTotalNumberOfTests());
  }

}
