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
