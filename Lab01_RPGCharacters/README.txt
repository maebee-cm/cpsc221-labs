# Lab 01: RPG Characters

* Author: Your Name
* Class: CPSC221 Section 001
* Semester: Fall 2022

## Overview

This program offers the classes needed to make a simple RPG. It has the builtin types Knight, Barbarian, Ranger, Rouge,
Cleric, and Wizard. It also offers the Abstract classes which allow establishing new character classes if extended.
The Character class comes with reasonable default methods for abilities, as well as a toString method that provides
basic information about said class (name, HPs, AC).

## Compiling and Using

All the provided Java classes are necessary for compilation, no external files or input is needed to run this program.

## Discussion

I originally wrote this program with only Character as a base class, in which it would drive all actions, and store
all info about a class (armor type, weapon type, and abilities) through enums and an EnumSet. However, that design was
too flat and didn't properly reflect OOP concepts.

The critical design choice for me was trying to find what trait should be the basis for inheritance on the basis
of trying to find the most common trait among classes. I eventually came to the conclusion that weapon type was the
most duplicated property, and established a hierarchy primarily based around a class's weapon type.

## Testing

I instantiated an instance of each of the provided classes, listed above in the overview, then called their toString
method as well as the method that matches the abilities they are given, then matched the output to the provided sample
until they were both identical.

----------