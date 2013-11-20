CS311 Homework 6 Tests
======================

## Downloading

To use these tests, just use Git and clone them into the directory you
have your project.

You can do this using:

    git clone https://github.com/jdavis/cs311-f13-hw06-tests.git tests

## Running the Tests

### From the Command Line

If you compile things from the command line and have ant installed:
http://ant.apache.org/, you only have to modify a few lines to make it
work.

1. First, open up `build.xml` and modify the property with the name `src.dir`.
   It should point to the directory that your package lives in.

2. Open up the `TestRunner.java` file and edit all the methods that start with
   `new[...]` to point to your implementation classes.

   1. You should change `return new Graph();` such that `Graph` is the class
      you wrote that implements `IGraph`.

   2. You should change `return new TopologicalSort();` such that
      `TopologicalSort` is the class you wrote that implements
      `ITopologicalSortAlgorithms`.

3. Now you should just be able to run `ant test` on the command line.

### From an IDE

1. Uninstall your IDE.
2. Open up a terminal.
3. Go to the previous section.
4. Done.

## Additional Things

This ant `build.xml` file contains a few targets:

1. `ant init`
2. `ant build`
3. `ant build-test`
4. `ant test`
5. `ant clean`

More than likely you'll only have to run **clean** and **test**.

## Contributing

Feel free to Fork this Gist and make changes. Let me know when/if you do
and I'll add them to the existing tests.
