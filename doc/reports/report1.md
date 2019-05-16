Part I: Unit tests + Boundary Tests
===========================

# 3.1 Smoke Testing
## Exercise 6 
CollisionInteractionMap and DefaultPlayerInteractionMap are the two classes that are not covered by the smoke test. 
DefaultPlayerInteractionMap is never used in the application, so we cannot test it by
running the program from end to end. CollisionInteractionMap is only used in the DefaultPlayerInteractionMap therefore it cannot be tested for the same reasons. 

## Exercise 7
Method move is covered by the smoke test. When commenting out the last line in game. Game,
as the move was not successful the points are not updated correctly. Thus, the test fails.

## Exercise 8
This yields the same exception in the test from exercise 7. Therefore, not
enough information about the bug is gained using a smoke test - the presence of the bug
is detected, but its actual location - not. This means that starting with unit tests
is essential to detect possible bugs, and after that testing the whole application is
supposed to be done.

## Exercise 9
In Unit class the components/players (Pac man and the monsters) with their properties are defined. 
Then in Board class, the board is created and the units are placed in their respective square. Level class will make the interaction possible 
between the components according to its level and determine the game's functionalities. Game class will 
use the elements from Level class to be able to extend some basic functions like : start game, pause game etc... 


# 3.2 Unit testing

## Exercise 10
There are 4 partitions that could be made in order to test
the Clyde.nextAiMove() method:

- When Clyde is near Pacman (this means that it should go
away from him);
- When Clyde is far away from Pacman (this means that it
should go towards him);
- When a Pacman isn't present in the board (bad weather
case, we should ensure that the application does not stop
working in this case);
- When there isn't a path between Pacman and Clyde (bad
weather case, we should ensure that the application does
not stop working in this case).

Tests are implemented in test.java.nl.tudelft.jpacman.npc.ghost.ClydeTest
class.

## Exercise 11
There are 5 partitions in this case:

- When Blinky is not present on the board (since Inky uses
Blinky to choose where to go this is a bad weather test
case);
- When there is not a player on the board (bad weather case,
shouldn't happen in reality, the application shouldn't stop
working);
- When there is not a valid path between Inky and the square
it wants to go to (bad weather case, path will be evaluated
to null);
- When both Inky and Blinky are in front of Pacman, so Inky
would go towards Pacman;
- When Blinky is behind Pacman and Inky is in front of him,
then Inky would go away from Pacman.

Tests are implemented in test.java.nl.tudelft.jpacman.npc.ghost.InkyTest
class.

# 3.3 Boundary Testing

## Exercise 12
For this test a (2x2)-board is used. This means that the
domain matrix would be:

![Alt text](/doc/Domain_Matrix.PNG?raw=true)

## Exercise 13
The tests are implemented in the test.java.nl.tudelft.jpacman.board.BoardTest
class.

# 3.4 Understanding Testing
 
## Exercise 14: 
Arrange is the first step of a unit test application. In
this phase all the required objects are created, mocks
(if needed) are set up and possibly the expected outputs
are set.

## Exercise 15: 
'clean instances' like in BoardFactoryTest helps to 
provide independence between test methods. This way 
instance variable values are not being reused across test 
methods as each test method will run on a new test class 
instance.

## Exercise 16: 
For comparing primitive types, assertTrue() will check 
for a condition and assertEquals() will test the equality 
of the expected value with the returning value. 
assertEquals() will provide a better error information 
if the test fails (like "Expected X but was Y") whereas 
assertTrue() would not.

## Exercise 17: 
Generally speaking, private methods should not be tested 
because they are used only in public methods from the same
class. This means that if the public methods are well
tested, then the private methods work fine as well. If a
private method needs to be tested in an isolated way, this
means that the code is not cohesive. For MapParser Class, 
we do not need to test the private method in isolation. 
The private methods are being used by the public methods 
like makeGrid() in parseMap() so in this case, a 
solution is to test the public methods so it will hit 
all the private methods.

## Exercise 18:
In this assignment, firstly a smoke test to check the whole
functionality of the application was executed. After changing
different parts of the code, though, this smoke test gave
the same errors. That is why implementing unit tests first
is better - unit tests are executed for only one function
of the application and can spot bugs in exactly that part
of the application. That is why unit tests for the "Clyde"
and "Inky" classes were implemented.
We did not have any issues with the CI since before
committing we always first executed gradle check to see if
we have any issues with the checkstyle or with the tests.
We committed only when the build was successful, that is why
our CI pipelines were always successful.