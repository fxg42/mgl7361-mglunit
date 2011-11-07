package samples;

import ca.uqam.mglunit.*;
import java.util.*;

public class SimpleTestSuite {
  @TestCases public Class[] testcases =
    { SimpleTest.class, SimpleTestWithAssert.class };
}
