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

import java.util.*;

public class TestSuiteRunner implements TestRunner {
  private Object specification;
  private Set<TestRunner> testRunners = new HashSet<TestRunner>();

  public TestSuiteRunner (Object specification) {
    this.specification = specification;
  }

  @Override public void run (TestResultLogger results) {
    for (TestRunner each : testRunners) each.run(results);
  }

  @Override public String getName () {
    return specification.getClass().getCanonicalName();
  }

  public void addTest (TestRunner testRunner) {
    testRunners.add(testRunner);
  }
}
