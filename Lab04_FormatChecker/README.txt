# Lab 04: Format Checker

* Author: Mustafa Hashim
* Class: CPSC221 Section 001
* Semester: Fall 2022

## Overview

The program checks if the files it is passed are formatted correctly in the following format:

N M
1 2 3 ... M
2 2 3 ... M
3 2 3 ... M
. 2 3 ... M
. 2 3 ... M
. 2 3 ... M
N 2 3 ... M

The program prints each file name, then an error if there was an issue with the file, and finally "VALID" if the file
was valid and "INVALID" otherwise. For example if passed the files `valid.dat` and `invalid.dat` the output will be as follows.

```
valid.dat
VALID

invalid.dat
<reason for invalidity>
INVALID
```

## Compiling and Using

All the provided *.java files are needed for compilation, the provided *.dat files are sample files to test the program.

to compile use the following command in the same directory as this file:

$ javac *.java

to run the program and test all the sample *.dat files use the following command in the same directory as this file:

$ java FormatChecker *.dat

You can use the program as follows

$ java FormatChecker [file1 file2 ... fileN]

## Discussion

What problems did you have? What went well?
Didn't have any problems.

What process did you go through to create the program?
Ran down each line and wrote checks for what could possibly be wrong with any given input.

What did you have to research and learn on your own?
Nothing

What kinds of errors did you get? How did you fix them?
Didn't get errors

What parts of the project did you find challenging?
None

Is there anything that finally "clicked" for you in the process of working on this project?
Not really no

Is there anything that you would change about the project?
I don't think so

Can you apply what you learned in this project to furture projects?
Yes, input validation is important in any program that takes user input as the user can never be trusted

## Testing

Ran the following command and manually checked each file was either correctly valid or invalid (with a proper reason)
$ java FormatChecker *.dat
