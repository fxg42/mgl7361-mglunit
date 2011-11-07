package ca.uqam.mglunit;

interface TestRunner {
  void run (TestResultLogger results);
  String getName();
}

