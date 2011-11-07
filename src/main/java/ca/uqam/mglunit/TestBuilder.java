package ca.uqam.mglunit;

import java.lang.reflect.*;

class TestBuilder {

  public TestRunner buildTestFrom (Class specificationClass) throws Exception {
    for (Field each : specificationClass.getFields())
      if (each.isAnnotationPresent(TestCases.class))
        return buildTestSuiteFrom(specificationClass, each);
    return buildTestCaseFrom(specificationClass);
  }

  @SuppressWarnings("unchecked")
  public TestRunner buildTestSuiteFrom (Class specificationClass, Field testCaseClassesField) throws Exception {
    Object specification = specificationClass.newInstance();
    TestSuiteRunner suite = new TestSuiteRunner(specification);
    Class[] testCaseClasses = (Class[]) testCaseClassesField.get(specification);

    for (Class each : testCaseClasses) suite.addTest(buildTestFrom(each));
    return suite;
  }

  public TestRunner buildTestCaseFrom (Class specificationClass) throws Exception {
    Object specification = specificationClass.newInstance();
    TestCaseRunner testcase = new TestCaseRunner(specification);

    for (Method each : specificationClass.getMethods()) {
      if (each.isAnnotationPresent(Test.class)) testcase.addTestMethod(each);
      if (each.isAnnotationPresent(Setup.class)) testcase.setSetupMethod(each);
      if (each.isAnnotationPresent(Teardown.class)) testcase.setTeardownMethod(each);
    }
    return testcase;
  }
}
