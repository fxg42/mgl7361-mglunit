/*
 * Copyright (C) 2011 Francois-Xavier Guillemette
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
