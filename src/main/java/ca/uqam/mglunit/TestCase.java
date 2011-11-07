package ca.uqam.mglunit;

import java.lang.reflect.*;
import java.util.*;

public class TestCase {
  private Class specificationClass;
  private Object specification;
  private TestResultLogger results;

  public TestCase (Class specificationClass, TestResultLogger results) throws Exception {
    this.results = results;
    this.specificationClass = specificationClass;

    results.setTestCaseClass(specificationClass);
    specification = specificationClass.newInstance();
  }
    
  public void run () {
    for (Method test : getTestMethods())
      runIndividualTest(test);
  }

  private void runIndividualTest (Method test) {
    try {
      test.invoke(specification);
      results.addPassedTest(test);
    } catch (Throwable t) {
      results.addFailedTest(test, t.getCause());
    }
  }

  private Set<Method> getTestMethods () {
    Set<Method> result = new HashSet<Method>();
    for (Method method : specificationClass.getMethods())
      if (method.isAnnotationPresent(Test.class))
        result.add(method);
    return result;
  }
}
