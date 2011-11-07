package ca.uqam.mglunit;

import java.util.logging.*;

public class TestRunner {
  private static final Logger logger = Logger.getLogger(TestRunner.class.getCanonicalName());

  private TestResultLogger results;


  public int run (String className) {
    try {
      new TestCase(Class.forName(className), results).run();
      return 0;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Caught expected", ex);
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

  void setTestResultLogger (TestResultLogger testResultLogger) {
    this.results = testResultLogger;
  }
}
