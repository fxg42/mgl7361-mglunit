package ca.uqam.mglunit;

import java.lang.reflect.*;
import java.util.*;
import java.util.logging.*;

public class TestRunner {
  private static final Logger log = Logger.getLogger(TestRunner.class.getCanonicalName());

  private Class testCaseClass;
  private Set<Method> individualTests = new HashSet<Method>();
  private TestResultLogger results = new TestResultLogger();

  public int run (String testCaseClassName) {
    try {
      testCaseClass = Class.forName(testCaseClassName);
      results.setTestCaseClass(testCaseClass);
      individualTests = findTestMethods(testCaseClass);
      executeTestCase();
      return 0;
    } catch (Exception ex) {
      log.log(Level.SEVERE, "Caught expected", ex);
      return 1;
    }
  }

  public int getTotalNumberOfTests () {
    return results.getTotalNumberOfTests();
  }
  public int getNumberOfFailedTests () {
    return results.getNumberOfFailedTests();
  }
  public int getNumberOfPassedTests () {
    return results.getNumberOfPassedTests();
  }
  public String getSummary () {
    return results.getSummary();
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
        results.addOnePassedTest();
      } catch (Throwable t) {
        results.addOneFailedTest();
      }
  }
}
