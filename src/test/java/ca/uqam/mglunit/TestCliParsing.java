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
