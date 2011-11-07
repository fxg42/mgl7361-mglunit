package ca.uqam.mglunit;

import java.io.*;
import java.util.*;
import javax.xml.stream.*;

public class XmlFormatter implements Formatter {
  private XMLStreamWriter xml;
  private TestResultLogger logger;

  @Override public String formatSummary (TestResultLogger logger) throws Exception {
    this.logger = logger;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    xml = XMLOutputFactory.newInstance().createXMLStreamWriter(baos);

    writeSummary();

    xml.flush();
    xml.close();

    return baos.toString("UTF-8");
  }

  @Override public void print (TestResultLogger logger) throws Exception { 
    this.logger = logger;
    xml = XMLOutputFactory.newInstance().createXMLStreamWriter(logger.getOutputStream());

    writeHeader();
    writeSummary();
    writeStacktraces();
    writeFooter();

    xml.flush();
    xml.close();
  }

  protected void writeHeader () throws Exception {
    xml.writeStartDocument();
    xml.writeStartElement("results");
  }
  protected void writeFooter () throws Exception {
    xml.writeEndElement();
  }

  protected void writeSummary () throws Exception {
    int total = logger.getTotalNumberOfTests();
    int failed = logger.getNumberOfFailedTests();
    Set<TestRunner> inError = logger.getTestCasesInError();

    xml.writeStartElement("summary");
    xml.writeAttribute("total", String.valueOf(total));
    xml.writeAttribute("failed", String.valueOf(failed));
    for (TestRunner each : inError) {
      xml.writeStartElement("failed");
      xml.writeCharacters(each.getName());
      xml.writeEndElement();
    }
    xml.writeEndElement();
  }

  protected void writeStacktraces () throws Exception {
    Set<Throwable> thrown = logger.getThrownExceptions();

    for (Throwable each : thrown) {
      xml.writeStartElement("thrown");
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      writeStackTrace(each, baos);
      xml.writeCharacters(baos.toString("UTF-8"));
      xml.writeEndElement();
    }
  }
  protected void writeStackTrace (Throwable t, OutputStream outputStream) {
    PrintWriter writer = new PrintWriter(outputStream);
    t.printStackTrace(writer);
    writer.flush();
  }
}
