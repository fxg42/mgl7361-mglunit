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

  @org.junit.Test public void it_executes_all_test_methods () {
    TestRunner runner = new TestRunner();
    int retval = runner.run("samples.SimpleTest");

    assertEquals(1, runner.getNumberOfFailedTests());
    assertEquals(2, runner.getNumberOfPassedTests());
  }

  @org.junit.Test public void it_uses_the_assert_methods () {
    TestRunner runner = new TestRunner();
    int retval = runner.run("samples.SimpleTestWithAssert");

    assertEquals(1, runner.getNumberOfFailedTests());
    assertEquals(1, runner.getNumberOfPassedTests());
  }

  @org.junit.Test public void it_records_the_results () {
    TestRunner runner = new TestRunner();
    int retval = runner.run("samples.SimpleTest");

    String expected =
      "Test samples.SimpleTest FAILED\n" +
      "3 tests completed, 1 failure";
    assertEquals(expected, runner.getSummary());
  }
}
