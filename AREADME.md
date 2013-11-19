CS311 Homework 6 Tests
======================

## Downloading

To use these tests, just use Git and clone them into the directory you
have your project.

You can do this using:

    git clone https://gist.github.com/7539406.git tests

## Running the Tests

If you compile things from the command line and have ant installed:
http://ant.apache.org/, you only have to modify a few lines to make it
work.

1. First, modify the property below with the name `src.dir`. It should point to
   the directory that your package lives in.

2. Open up `TestRunner.java` file and edit the return line of the method
   `newInstance`.  The class that is instantiated should point to your
   implementation of IGraph.

3. Now you should just be able to run `ant test` on the command line.

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
