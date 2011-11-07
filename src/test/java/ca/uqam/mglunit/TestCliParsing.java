package ca.uqam.mglunit;

import java.io.*;
import org.junit.*;
import org.apache.commons.io.*;
import static org.junit.Assert.*;

public class TestCliParsing {

  @org.junit.Test public void it_prints_the_help () throws Exception {
    String[] args = { "--help" };
    Runner.main(args);
  }

  @org.junit.Test public void it_runs_with_defaults () throws Exception {
    String[] args = { "samples.SimpleTestWithAssert" };
    Runner.main(args);
  }

  @org.junit.Test public void it_writes_to_a_file () throws Exception {
    String testClassName = "samples.SimpleTestWithAssert";
    String outputFilePath = "./build/test."+testClassName+".txt";

    String[] args = { "-o", outputFilePath, testClassName };
    Runner.main(args);
    
    String contents = FileUtils.readFileToString(new File(outputFilePath));

    assertTrue(contents.contains("3 tests completed, 2 failures"));
    assertTrue(contents.contains("expected:<1> but was:<2>"));
    assertTrue(contents.contains("expected:<foo> but was:<bar>"));
  }

  @org.junit.Test public void it_writes_to_an_xml_file () throws Exception {
    String testClassName = "samples.SimpleTestWithAssert";
    String outputFilePath = "./build/test."+testClassName+".xml";

    String[] args = { "-o", outputFilePath, "-f", "xml", testClassName };
    Runner.main(args);
    
    String contents = FileUtils.readFileToString(new File(outputFilePath));
  }
}
