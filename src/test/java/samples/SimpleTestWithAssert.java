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

public class SimpleTestWithAssert {
  @Test public void it_uses_the_assertequals_method_and_passes () {
    assertEquals(1, 1);
  }

  @Test public void it_uses_the_assertequals_method_and_fails () {
    assertEquals(1, 2);
  }

  @Test public void another_test_that_uses_the_assertequals_method_and_fails () {
    assertEquals("foo", "bar");
  }
}
