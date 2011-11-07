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
