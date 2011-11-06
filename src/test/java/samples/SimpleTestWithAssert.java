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
}
