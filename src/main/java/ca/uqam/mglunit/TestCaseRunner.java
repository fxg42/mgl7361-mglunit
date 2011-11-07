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

import java.lang.reflect.*;
import java.util.*;

public class TestCaseRunner implements TestRunner {
  private Object specification;
  private TestResultLogger results;
  private Set<Method> testMethods = new HashSet<Method>();
  private Method setupMethod;
  private Method teardownMethod;

  public TestCaseRunner (Object specification) {
    this.specification = specification;
  }

  @Override public void run (TestResultLogger results) {
    setTestResultLogger(results);
    for (Method test : testMethods)
      runIndividualTest(test);
  }

  @Override public String getName () {
    return specification.getClass().getCanonicalName();
  }

  private void runIndividualTest (Method test) {
    try {
      runFixture(setupMethod);
      test.invoke(specification);
      results.addPassedTest(test);
    } catch (Throwable t) {
      results.addFailedTest(test, t.getCause());
    } finally {
      runFixture(teardownMethod);
    }
  }

  private void runFixture (Method fixture) {
    try {
      if (fixture != null)
        fixture.invoke(specification);
    } catch (Exception e) {
      /* do nothing */
    }
  }

  public void addTestMethod (Method method) {
    testMethods.add(method);
  }

  public void setSetupMethod (Method method) {
    setupMethod = method;
  }

  public void setTeardownMethod (Method method) {
    teardownMethod = method;
  }

  private void setTestResultLogger (TestResultLogger results) {
    this.results = results;
    this.results.setCurrentTestCase(this);
  }

  Object getSpecification () {
    return specification;
  }
}
