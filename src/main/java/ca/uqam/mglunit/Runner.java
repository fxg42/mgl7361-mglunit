package ca.uqam.mglunit;

import java.util.*;
import java.util.logging.*;
import java.lang.reflect.*;

public class Runner {
  private static final Logger logger = Logger.getLogger(Runner.class.getCanonicalName());

  private Class specificationClass;
  private TestResultLogger results;

  public int run (String className) {
    try {
      specificationClass = Class.forName(className);
      buildTestFrom(specificationClass).run(results);
      return 0;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Caught expected", ex);
      return 1;
    }
  }

  private TestRunner buildTestFrom (Class specificationClass) throws Exception {
    for (Field each : specificationClass.getFields())
      if (each.isAnnotationPresent(TestCases.class))
        return buildTestSuiteFrom(specificationClass, each);
    return buildTestCaseFrom(specificationClass);
  }

  @SuppressWarnings("unchecked")
  private TestRunner buildTestSuiteFrom (Class specificationClass, Field testCaseClassesField) throws Exception {
    Object specification = specificationClass.newInstance();
    TestSuiteRunner suite = new TestSuiteRunner(specification);
    Class[] testCaseClasses = (Class[]) testCaseClassesField.get(specification);
    for (Class each : testCaseClasses)
      suite.addTest(buildTestFrom(each));
    return suite;
  }

  private TestRunner buildTestCaseFrom (Class specificationClass) throws Exception {
    Object specification = specificationClass.newInstance();
    TestCaseRunner testcase = new TestCaseRunner(specification);
    for (Method each : specificationClass.getMethods())
      if (each.isAnnotationPresent(Test.class))
        testcase.addTestMethod(each);
    return testcase;
  }

  public void setTestResultLogger (TestResultLogger testResultLogger) {
    this.results = testResultLogger;
  }
}
