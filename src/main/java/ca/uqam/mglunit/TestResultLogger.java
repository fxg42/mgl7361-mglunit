package ca.uqam.mglunit;

public class TestResultLogger {
  private int failedTests = 0;
  private int passedTests = 0;
  private String testCaseClassName = "";

  public void addOneFailedTest () {
    failedTests += 1;
  }
  public void addOnePassedTest () {
    passedTests += 1;
  }
  public void setTestCaseClass (Class klass) {
    testCaseClassName = klass.getCanonicalName();
  }

  public int getTotalNumberOfTests () {
    return passedTests + failedTests;
  }
  public int getNumberOfFailedTests () {
    return failedTests;
  }
  public int getNumberOfPassedTests () {
    return passedTests;
  }

  public String getSummary () {
    if (failedTests == 0) return "";

    return new StringBuilder()
        .append("Test ").append(testCaseClassName).append(" FAILED\n")
        .append(String.format("%d tests completed, %d failure",
              getTotalNumberOfTests(),
              getNumberOfFailedTests()))
        .toString();
  }
}
