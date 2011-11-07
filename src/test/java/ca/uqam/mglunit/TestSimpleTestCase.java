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

public class TestSimpleTestCase {
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

  @org.junit.Test public void it_finds_all_individual_test_methods () {
    runner.setSpecificationClass(samples.SimpleTest.class);
    int retval = runner.run();

    assertEquals(0, retval);
    assertEquals(3, results.getTotalNumberOfTests());
  }

  @org.junit.Test public void it_executes_all_test_methods () {
    runner.setSpecificationClass(samples.SimpleTest.class);
    int retval = runner.run();

    assertEquals(1, results.getNumberOfFailedTests());
    assertEquals(2, results.getNumberOfPassedTests());
  }

  @org.junit.Test public void it_uses_the_assert_methods () {
    runner.setSpecificationClass(samples.SimpleTestWithAssert.class);
    int retval = runner.run();

    assertEquals(2, results.getNumberOfFailedTests());
    assertEquals(1, results.getNumberOfPassedTests());
  }

  @org.junit.Test public void it_records_the_results_and_prints_summary () throws Exception {
    runner.setSpecificationClass(samples.SimpleTest.class);
    int retval = runner.run();

    String expected =
      "Test samples.SimpleTest FAILED\n" +
      "3 tests completed, 1 failure";
    assertEquals(expected, results.getSummary());
  }

  @org.junit.Test public void it_records_the_results_and_prints_details () throws Exception {
    runner.setSpecificationClass(samples.SimpleTestWithAssert.class);
    int retval = runner.run();
    results.print();

    String actual = ((ByteArrayOutputStream) outputStream).toString("UTF-8");
    assertTrue(actual.contains("expected:<1> but was:<2>"));
    assertTrue(actual.contains("expected:<foo> but was:<bar>"));
  }
}
