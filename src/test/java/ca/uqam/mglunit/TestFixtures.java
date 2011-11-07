package ca.uqam.mglunit;

import java.io.*;
import org.junit.*;
import static org.junit.Assert.*; 

public class TestFixtures {
  private Runner runner;
  private TestResultLogger results;
  private OutputStream outputStream;

  @org.junit.Before public void setup () {
    runner = new Runner();
    results = new TestResultLogger();
    outputStream = new ByteArrayOutputStream();
    results.setOutputStream(outputStream);
    runner.setTestResultLogger(results);
  }

  @org.junit.Test public void it_executes_test_fixtures () throws Exception {
    runner.setSpecificationClass(samples.SimpleTestWithFixtures.class);
    runner.run();
    results.print();
    
    samples.SimpleTestWithFixtures testcase = (samples.SimpleTestWithFixtures) ((TestCaseRunner) runner.getRootTestRunner()).getSpecification();
    assertEquals("btabtabta", testcase.callSequence);
  }
}
