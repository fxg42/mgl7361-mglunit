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
package samples;

import ca.uqam.mglunit.*;
import static ca.uqam.mglunit.Assert.*;

public class SimpleTestWithFixtures {
  public String callSequence = "";

  @Setup public void setup () {
    callSequence += "b";
  }
  @Teardown public void teardown () {
    callSequence += "a";
  }
  @Test public void test_a () {
    callSequence += "t";
    assertEquals(true, false);
  }
  @Test public void test_b () {
    callSequence += "t";
  }
  @Test public void test_c () {
    callSequence += "t";
  }
}
