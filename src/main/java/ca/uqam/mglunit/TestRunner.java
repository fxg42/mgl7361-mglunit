package ca.uqam.mglunit;

import java.util.logging.*;

public class TestRunner {
  private static final Logger logger = Logger.getLogger(TestRunner.class.getCanonicalName());

  private TestResultLogger results;

  public int run (String className) {
    try {
      new TestCaseRunner(Class.forName(className), results).run();
      return 0;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Caught expected", ex);
      return 1;
    }
  }

  void setTestResultLogger (TestResultLogger testResultLogger) {
    this.results = testResultLogger;
  }
}
