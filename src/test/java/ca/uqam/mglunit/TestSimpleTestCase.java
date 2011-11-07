package ca.uqam.mglunit;

import java.io.*;
import org.junit.*;
import static org.junit.Assert.*; 

public class TestSimpleTestCase {
  private TestRunner runner;
  private TestResultLogger results;
  private OutputStream outputStream;

  @org.junit.Before public void setup () {
    runner = new TestRunner();
    results = new TestResultLogger();
    outputStream = new ByteArrayOutputStream();
    results.setOutputStream(outputStream);
    runner.setTestResultLogger(results);
  }

  @org.junit.Test public void it_finds_all_individual_test_methods () {
    int retval = runner.run("samples.SimpleTest");

    assertEquals(0, retval);
    assertEquals(3, results.getTotalNumberOfTests());
  }

  @org.junit.Test public void it_executes_all_test_methods () {
    int retval = runner.run("samples.SimpleTest");

    assertEquals(1, results.getNumberOfFailedTests());
    assertEquals(2, results.getNumberOfPassedTests());
  }

  @org.junit.Test public void it_uses_the_assert_methods () {
    int retval = runner.run("samples.SimpleTestWithAssert");

    assertEquals(2, results.getNumberOfFailedTests());
    assertEquals(1, results.getNumberOfPassedTests());
  }

  @org.junit.Test public void it_records_the_results_and_prints_summary () {
    int retval = runner.run("samples.SimpleTest");

    String expected =
      "Test samples.SimpleTest FAILED\n" +
      "3 tests completed, 1 failure";
    assertEquals(expected, results.getSummary());
  }

  @org.junit.Test public void it_records_the_results_and_prints_details () throws Exception {
    int retval = runner.run("samples.SimpleTestWithAssert");

    String actual = ((ByteArrayOutputStream) outputStream).toString("UTF-8");
    assertTrue(actual.contains("expected:<1> but was:<2>"));
    assertTrue(actual.contains("expected:<foo> but was:<bar>"));
  }
}
