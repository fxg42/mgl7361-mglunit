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
  private Set<Throwable> thrown = new HashSet<Throwable>();
  private Formatter formatter = new PlainFormatter();

  public void addPassedTest (Method test) {
    passedTests += 1;
  }
  public void addFailedTest (Method test, Throwable t) {
    failedTests += 1;
    thrown.add(t);
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
  public Set<TestRunner> getTestCasesInError () {
    return testCasesInError;
  }
  public Set<Throwable> getThrownExceptions () {
    return thrown;
  }

  public String getSummary () throws Exception {
    return formatter.formatSummary(this);
  }
  public void print () throws Exception {
    formatter.print(this);
  }

  OutputStream getOutputStream () {
    return outputStream;
  }
  public void setOutputStream (OutputStream outputStream) {
    this.outputStream = outputStream;
  }
  public void setFormatter (Formatter formatter) {
    this.formatter = formatter;
  }
}
