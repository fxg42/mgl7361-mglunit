package samples;

import ca.uqam.mglunit.*;

public class SimpleTest {
  @Test public void a_failing_test_method () {
    throw new Error("boom");
  }
  @Test public void another_test_method () {
  }
  @Test public void yet_another_test_method () {
  }
}
