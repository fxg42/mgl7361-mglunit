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

public class TestFixtures {
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

  @org.junit.Test public void it_executes_test_fixtures () throws Exception {
    runner.setSpecificationClass(samples.SimpleTestWithFixtures.class);
    runner.run();
    results.print();
    
    samples.SimpleTestWithFixtures testcase = (samples.SimpleTestWithFixtures) ((TestCaseRunner) runner.getRootTestRunner()).getSpecification();
    assertEquals("btabtabta", testcase.callSequence);
  }
}
