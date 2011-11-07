package ca.uqam.mglunit;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class TestResultLogger {
  private int failedTests = 0;
  private int passedTests = 0;
  private TestRunner currentTestRunner;
  private OutputStream outputStream;
  private Set<TestRunner> testCasesInError = new HashSet<TestRunner>();

  public void addPassedTest (Method test) {
    passedTests += 1;
  }
  public void addFailedTest (Method test, Throwable t) {
    failedTests += 1;
    writeStackTrace(t);
    testCasesInError.add(currentTestRunner);
  }
  public void setCurrentTestCase (TestRunner runner) {
    currentTestRunner = runner;
  }

  public int getTotalNumberOfTests () {
    return passedTests + failedTests;
  }
  public int getNumberOfFailedTests () {
    return failedTests;
  }
  public int getNumberOfPassedTests () {
    return passedTests;
  }

  public String getSummary () {
    if (failedTests == 0) return "";

    StringBuilder builder = new StringBuilder();
    for (TestRunner each : testCasesInError)
      builder.append("Test ").append(each.getName()).append(" FAILED\n");
    
    return builder.append(String.format("%d test%s completed, %d failure%s",
        getTotalNumberOfTests(),
        (getTotalNumberOfTests() > 1 ? "s" : ""),
        getNumberOfFailedTests(),
        (getNumberOfFailedTests() > 1 ? "s" : "")))
      .toString();
  }

  private void writeStackTrace (Throwable t) {
    PrintWriter writer = new PrintWriter(outputStream);
    t.printStackTrace(writer);
    writer.flush();
    writer.close();
  }

  void setOutputStream (OutputStream outputStream) {
    this.outputStream = outputStream;
  }
}
