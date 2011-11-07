package ca.uqam.mglunit;

import java.io.*;
import org.junit.*;
import static org.junit.Assert.*; 

public class TestSimpleTestSuite {
  private Runner runner;
  private TestResultLogger results;
  private OutputStream outputStream;

  @org.junit.Before public void setup () {
    runner = new Runner();
    results = new TestResultLogger();
    outputStream = new ByteArrayOutputStream();
    results.addOutputStream(outputStream);
    runner.setTestResultLogger(results);
  }

  @org.junit.Test public void it_executes_all_tests () throws Exception {
    int retval = runner.run("samples.SimpleTestSuite");

    assertEquals(0, retval);
    assertEquals(6, results.getTotalNumberOfTests());
    assertEquals(3, results.getNumberOfPassedTests());
    assertEquals(3, results.getNumberOfFailedTests());

    String expectedSummary1 =
      "Test samples.SimpleTestWithAssert FAILED\n" +
      "Test samples.SimpleTest FAILED\n" +
      "6 tests completed, 3 failures";
    String expectedSummary2 =
      "Test samples.SimpleTest FAILED\n" +
      "Test samples.SimpleTestWithAssert FAILED\n" +
      "6 tests completed, 3 failures";

    String actualSummary = results.getSummary();
    assertTrue(expectedSummary1.equals(actualSummary) || expectedSummary2.equals(actualSummary));

    String actual = ((ByteArrayOutputStream) outputStream).toString("UTF-8");
    assertTrue(actual.contains("expected:<1> but was:<2>"));
    assertTrue(actual.contains("expected:<foo> but was:<bar>"));
  }
}
