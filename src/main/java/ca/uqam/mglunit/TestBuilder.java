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

class TestBuilder {

  public TestRunner buildTestFrom (Class specificationClass) throws Exception {
    for (Field each : specificationClass.getFields())
      if (each.isAnnotationPresent(TestCases.class))
        return buildTestSuiteFrom(specificationClass, each);
    return buildTestCaseFrom(specificationClass);
  }

  @SuppressWarnings("unchecked")
  public TestRunner buildTestSuiteFrom (Class specificationClass, Field testCaseClassesField) throws Exception {
    Object specification = specificationClass.newInstance();
    TestSuiteRunner suite = new TestSuiteRunner(specification);
    Class[] testCaseClasses = (Class[]) testCaseClassesField.get(specification);

    for (Class each : testCaseClasses) suite.addTest(buildTestFrom(each));
    return suite;
  }

  public TestRunner buildTestCaseFrom (Class specificationClass) throws Exception {
    Object specification = specificationClass.newInstance();
    TestCaseRunner testcase = new TestCaseRunner(specification);

    for (Method each : specificationClass.getMethods()) {
      if (each.isAnnotationPresent(Test.class)) testcase.addTestMethod(each);
      if (each.isAnnotationPresent(Setup.class)) testcase.setSetupMethod(each);
      if (each.isAnnotationPresent(Teardown.class)) testcase.setTeardownMethod(each);
    }
    return testcase;
  }
}
