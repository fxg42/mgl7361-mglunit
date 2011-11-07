package ca.uqam.mglunit;

import java.util.*;
import java.util.logging.*;
import java.lang.reflect.*;

public class Runner {
  private static final Logger logger = Logger.getLogger(Runner.class.getCanonicalName());

  private Class specificationClass;
  private TestResultLogger results;
  private TestRunner rootTestRunner;

  public int run (String className) {
    try {
      specificationClass = Class.forName(className);
      rootTestRunner = new TestBuilder().buildTestFrom(specificationClass);
      rootTestRunner.run(results);
      return 0;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Caught expected", ex);
      return 1;
    }
  }

  public void setTestResultLogger (TestResultLogger testResultLogger) {
    this.results = testResultLogger;
  }

  TestRunner getRootTestRunner () {
    return rootTestRunner;
  }
}
