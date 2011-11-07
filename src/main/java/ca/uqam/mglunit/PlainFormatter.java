package ca.uqam.mglunit;

import java.io.*;
import java.util.*;

public class PlainFormatter implements Formatter {

  @Override public String formatSummary (TestResultLogger logger) throws Exception {
    int total = logger.getTotalNumberOfTests();
    int failed = logger.getNumberOfFailedTests();
    Set<TestRunner> inError = logger.getTestCasesInError();

    if (failed == 0) return "";

    StringBuilder builder = new StringBuilder();
    for (TestRunner each : inError) builder.append("Test ").append(each.getName()).append(" FAILED\n");
    return builder.append(String.format("%d test%s completed, %d failure%s", total, (total > 1 ? "s" : ""), failed, (failed > 1 ? "s" : ""))).toString();
  }

  @Override public void print (TestResultLogger logger) throws Exception { 
    OutputStream outputStream = logger.getOutputStream();
    Set<Throwable> thrown = logger.getThrownExceptions();

    outputStream.write((formatSummary(logger)+"\n").getBytes("UTF-8"));
    for (Throwable each : thrown) writeStackTrace(each, outputStream);
    outputStream.flush();
  }

  protected void writeStackTrace (Throwable t, OutputStream outputStream) {
    PrintWriter writer = new PrintWriter(outputStream);
    t.printStackTrace(writer);
    writer.flush();
  }
}
