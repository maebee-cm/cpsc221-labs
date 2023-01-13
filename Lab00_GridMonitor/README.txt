# Lab 00: Grid Monitor

* Author: Mustafa hashim
* Class: CPSC221 Section 001
* Semester: Fall 2022

## Overview

This program reads a 2D grid of numbers from a file, then evaluates the data.
It's goal is to simulate a fictitious program who's goal is to verify the stability
of cells in a solar array on a spaceship.

## Compiling and Using

The files GridMonitor.java and GridMonitorInterface.java are the necessary files
for this program's functionality. GridMonitorTest.java, the SampleFiles directory
and all files contained therein are for the purpose of testing the program works
according to specification.

One can use the program by instantiating a new GridMonitor, who's constructor takes
an argument to a file contained within the "SampleFiles" directory. The file should
specify how many rows there are then how many columns. Every line after that should
contain `column` real numbers, and there should be `row` lines of numbers.

after construction, calling GridMonitor's getter methods would yield each grid as
specified by the methods name and documentation. GridMonitor also has a toString
implementation (which is undocumented in the project specification) which prints
the solar array's cell values then another boolean grid who's cell positions correspond
1:1 with the solar array's cells and specify if the cell is in danger by printing true,
and printing false if it's not.

## Discussion

The only issues I ran into is due tests in GridMonitorTest testing for undocumented
specifications, and creating a proper deep copy of the array (since apparently clone
is a liar). As far as undocumented specifications go, the tester class tests for toString
functions that are unspecified who's functionality has to be guessed at, as well as
the fact that it passes file names, rather than file paths which forces the prepending
of the string "SampleFiles/" to all strings passed to GridMonitor.

one thing I would change about the structure of this project is that I wouldn't personally
structure the GridMonitor class to use so many 2D arrays. I personally would create a Cell
class that contains the following properties: "value, surroundingSum, sumAvg, delta, inDanger",
all of which would be doubles except inDanger, which would be a boolean, and who's names
correspond to the 2D arrays who's names they come from. The GridMonitor class then would
only have a single 2D array of type `Cell[][]`.

## Testing

I ran the main metho in the GridMonitorTest class until all tests were passed.

----------