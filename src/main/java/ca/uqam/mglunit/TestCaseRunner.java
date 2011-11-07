package ca.uqam.mglunit;

import java.lang.reflect.*;
import java.util.*;

public class TestCaseRunner implements TestRunner {
  private Object specification;
  private TestResultLogger results;
  private Set<Method> testMethods = new HashSet<Method>();

  public TestCaseRunner (Object specification) {
    this.specification = specification;
  }

  @Override public void run (TestResultLogger results) {
    setTestResultLogger(results);
    for (Method test : testMethods)
      runIndividualTest(test);
  }

  @Override public String getName () {
    return specification.getClass().getCanonicalName();
  }

  private void runIndividualTest (Method test) {
    try {
      test.invoke(specification);
      results.addPassedTest(test);
    } catch (Throwable t) {
      results.addFailedTest(test, t.getCause());
    }
  }

  public void addTestMethod (Method method) {
    testMethods.add(method);
  }

  private void setTestResultLogger (TestResultLogger results) {
    this.results = results;
    this.results.setCurrentTestCase(this);
  }
}
