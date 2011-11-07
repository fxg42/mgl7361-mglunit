package ca.uqam.mglunit;

import java.util.*;

public class TestSuiteRunner implements TestRunner {
  private Object specification;
  private Set<TestRunner> testRunners = new HashSet<TestRunner>();

  public TestSuiteRunner (Object specification) {
    this.specification = specification;
  }

  @Override public void run (TestResultLogger results) {
    for (TestRunner each : testRunners) each.run(results);
  }

  @Override public String getName () {
    return specification.getClass().getCanonicalName();
  }

  public void addTest (TestRunner testRunner) {
    testRunners.add(testRunner);
  }
}
