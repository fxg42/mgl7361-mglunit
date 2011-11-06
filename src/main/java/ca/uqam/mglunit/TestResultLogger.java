package ca.uqam.mglunit;

import java.lang.reflect.*;
import java.io.*;

public class TestResultLogger {
  private int failedTests = 0;
  private int passedTests = 0;
  private String testCaseClassName = "";
  private OutputStream outputStream;

  public void addPassedTest (Method test) {
    passedTests += 1;
  }
  public void addFailedTest (Method test, Throwable t) {
    failedTests += 1;
    PrintWriter writer = new PrintWriter(outputStream);
    t.printStackTrace(writer);
    writer.flush();
    writer.close();
  }
  public void setTestCaseClass (Class klass) {
    testCaseClassName = klass.getCanonicalName();
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

    return new StringBuilder()
        .append("Test ").append(testCaseClassName).append(" FAILED\n")
        .append(String.format("%d tests completed, %d failure",
              getTotalNumberOfTests(),
              getNumberOfFailedTests()))
        .toString();
  }

  void setOutputStream (OutputStream outputStream) {
    this.outputStream = outputStream;
  }
}
