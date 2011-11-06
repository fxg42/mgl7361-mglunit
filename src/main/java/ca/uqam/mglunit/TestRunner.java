package ca.uqam.mglunit;

import java.lang.reflect.*;
import java.util.*;
import java.util.logging.*;

public class TestRunner {
  private static final Logger log = Logger.getLogger(TestRunner.class.getCanonicalName());

  private Class testCaseClass;
  private Set<Method> individualTests = new HashSet<Method>();
  private int numberOfFailedTests;
  private int numberOfPassedTests;

  public int run (String testCaseClassName) {
    try {
      testCaseClass = Class.forName(testCaseClassName);
      individualTests = findTestMethods(testCaseClass);
      executeTestCase();
      return 0;
    } catch (Exception ex) {
      log.log(Level.SEVERE, "Caught expected", ex);
      return 1;
    }
  }

  public int getTotalNumberOfTests () {
    return individualTests.size();
  }
  public int getNumberOfFailedTests () {
    return numberOfFailedTests;
  }
  public int getNumberOfPassedTests () {
    return numberOfPassedTests;
  }

  private Set<Method> findTestMethods (Class testCaseClass) throws Exception {
    Set<Method> result = new HashSet<Method>();
    for (Method method : testCaseClass.getMethods())
      if (method.isAnnotationPresent(Test.class))
        result.add(method);
    return result;
  }

  private void executeTestCase () throws Exception {
    Object testCase = testCaseClass.newInstance();
    for (Method test : individualTests)
      try {
        test.invoke(testCase);
        numberOfPassedTests += 1;
      } catch (Throwable t) {
        numberOfFailedTests += 1;
      }
  }
}
