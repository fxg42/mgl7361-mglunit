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
import org.junit.*;
import static org.junit.Assert.*; 

public class TestSimpleTestSuite {
  private Runner runner;
  private TestResultLogger results;
  private OutputStream outputStream;

  @org.junit.Before public void setup () {
    runner = new Runner();
    results = new TestResultLogger();
    outputStream = new ByteArrayOutputStream();
    results.setOutputStream(outputStream);
    runner.setTestResultLogger(results);
  }

  @org.junit.Test public void it_executes_all_tests () throws Exception {
    runner.setSpecificationClass(samples.SimpleTestSuite.class);
    int retval = runner.run();
    results.print();

    assertEquals(0, retval);
    assertEquals(6, results.getTotalNumberOfTests());
    assertEquals(3, results.getNumberOfPassedTests());
    assertEquals(3, results.getNumberOfFailedTests());

    String expectedSummary1 =
      "Test samples.SimpleTestWithAssert FAILED\n" +
      "Test samples.SimpleTest FAILED\n" +
      "6 tests completed, 3 failures";
    String expectedSummary2 =
      "Test samples.SimpleTest FAILED\n" +
      "Test samples.SimpleTestWithAssert FAILED\n" +
      "6 tests completed, 3 failures";

    String actualSummary = results.getSummary();
    assertTrue(expectedSummary1.equals(actualSummary) || expectedSummary2.equals(actualSummary));

    String actual = ((ByteArrayOutputStream) outputStream).toString("UTF-8");
    assertTrue(actual.contains("expected:<1> but was:<2>"));
    assertTrue(actual.contains("expected:<foo> but was:<bar>"));
  }
}
