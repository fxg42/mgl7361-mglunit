package ca.uqam.mglunit;

import java.lang.reflect.*;
import java.util.*;
import java.util.logging.*;

public class TestRunner {
  private static final Logger log = Logger.getLogger(TestRunner.class.getCanonicalName());

  private Set<Method> individualTests = new HashSet<Method>();

  public int run (String testCaseClassName) {
    try {
      Class testCaseClass = Class.forName(testCaseClassName);
      individualTests = findTestMethods(testCaseClass);
      return 0;
    } catch (Exception ex) {
      log.log(Level.SEVERE, "Caught expected", ex);
      return 1;
    }
  }

  public int getTotalNumberOfTests () {
    return individualTests.size();
  }

  private Set<Method> findTestMethods (Class testCaseClass) throws Exception {
    Set<Method> result = new HashSet<Method>();
    for (Method method : testCaseClass.getMethods())
      if (method.isAnnotationPresent(Test.class))
        result.add(method);
    return result;
  }
}
