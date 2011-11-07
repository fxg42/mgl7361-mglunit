package ca.uqam.mglunit;

import java.io.*;
import java.util.logging.*;
import org.apache.commons.cli.*;

public class Runner {
  private static final Logger logger = Logger.getLogger(Runner.class.getCanonicalName());
  private static Options cliOptions = new Options();

  static {
    cliOptions.addOption("h", "help", false, "print this message");
    cliOptions.addOption("o", "output", true, "use given file for output");
    cliOptions.addOption("f", "format", true, "specify output format. Possible values: (plain|xml). Defaults to 'plain'.");
  }

  private Class specificationClass;
  private TestResultLogger results;
  private TestRunner rootTestRunner;

  public int run () {
    try {
      rootTestRunner = new TestBuilder().buildTestFrom(specificationClass);
      rootTestRunner.run(results);
      return 0;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Caught expected", ex);
      return 1;
    }
  }

  public void setTestResultLogger (TestResultLogger testResultLogger) {
    this.results = testResultLogger;
  }

  public void setSpecificationClass (Class specificationClass) {
    this.specificationClass = specificationClass;
  }

  TestRunner getRootTestRunner () {
    return rootTestRunner;
  }

  public static void main (String[] args) throws Exception {
    Runner runner = new Runner();
    TestResultLogger logger = new TestResultLogger();
    logger.setOutputStream(System.out);

    try {
      CommandLineParser parser = new GnuParser();
      CommandLine cli = parser.parse(cliOptions, args);
      if (cli.hasOption("help") || cli.getArgs().length == 0)
        printHelp();
      else {
        configureLogger(logger, cli);
        runner.setTestResultLogger(logger);
        runner.setSpecificationClass(Class.forName(cli.getArgs()[0]));
        runner.run();
      }
    } catch (ParseException ex) {
      printHelp();
    } finally {
      logger.print();
    }
  }

  private static void configureLogger (TestResultLogger logger, CommandLine cli) throws Exception {
    if (cli.hasOption("format") && cli.getOptionValue("format") == "xml") {
      logger.setFormatter(new XmlFormatter());
    }
    if (cli.hasOption("output"))
      logger.setOutputStream(new FileOutputStream(cli.getOptionValue("output")));
  }

  private static void printHelp () {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("java "+ Runner.class.getCanonicalName() +" [OPTIONS] FILE", cliOptions);
  }
}
