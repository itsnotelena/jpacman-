Part 0: Prerequisites Report
===========================

## 1. Exhaustive testing and why it is impossible

Exhaustive testing means considering all possible inputs
for a program and making sure that for each one of them
the program works as it is supposed to. Usually this is
impossible because the domain of the inputs is too large
to be exhaustively considered - testing a method that
multiplies 2 integers, for example, should take into account
around 2<sup>32</sup> * 2<sup>32</sup> different test cases.

An effective testing method should be used instead. This is
achieved by testing the development cycle at each phase (and
not postponing it for the last possible moment) and thoroughly
considering all potential defects in the program.


## 2. Pesticide Paradox and what it implies to software testers

In software testing, Pesticide Paradox refers to the problem
of testers executing the same tests over and over again which
in the end will no longer find new bugs in the program. When
testers find lots of defects in a certain part of an application,
developers start being extremely careful about further implementations
of this exact part and tend to not look into possible defects
in other areas.

To prevent this from happening, preparing new tests for other
aspects of the program (variation in testing) is of big
importance. If the number of the test cases become extremely
large and executing them becomes really expensive, tests 
that are already known to be working properly should be 
removed.


## 3. Automating test execution

Test automation is of great importance to the developers
of an application. Usually same test cases are run 
throughout the development of the program. Manually repeating
this process is time consuming. Machines, however, are fast - 
testing something that will take hours for a person might be
done in couple of minutes by a computer. Also, choosing 
test strategies and routes would be much faster if machines 
did it instead of humans - again, because they are really fast
in computing the statistics for these routes. 