package ca.uqam.mglunit;

interface Formatter {
  String formatSummary (TestResultLogger logger) throws Exception;
  void print (TestResultLogger logger) throws Exception;
}
