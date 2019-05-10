Part I: Unit tests + Boundary Tests
===========================

##3.1 Smoke Testing
Exercice 6: CollisionInteractionMap and DefaultPlayerInteractionMap are the two classes that are not covered by the smoke test. 
DefaultPlayerInteractionMap is never used in the application, so we cannot test it by
running the program from end to end. CollisionInteractionMap is only used in the DefaultPlayerInteractionMap therefore it cannot be tested for the same reasons. 

Exercice 7: method move is covered by our smoke test. When we comment out the last line in game.Game,
as the move wasn't successful the points are not updated correctly. Thus, the test fails.

Exercice 8: This yields the same exception in the test from exercise 7. Therefore, not
enough information about the bug is gained using a smoke test - the presence of the bug
is detected, but its actual location - not. This means that starting with unit tests
is essential to detect possible bugs, and after that testing the whole application is
supposed to be done.

Exercice 9: In Unit class we define the components/players (Pac man and the monsters) with their properties. 
Then in Board class, the board is created with and the units are placed in their respective square. Level class will make the interaction possible 
between the components according to its level and determine the game's functionalities. Game class will 
use the elements from Level class to be able to extend some basic functions like : start game, pause game etc... 


##3.4 Understanding Testing 
Exercise 14: Arrange is the first step of a unit test application. 
To avoid code duplication we need to make test code as simple as possible. 
We can also avoid using variables for expected values and create complex code in the tests.
As we are doing the setup of the test, simple assertions should be used instead. 

Exercise 15: 'clean instances' like in BoardFactoryTest helps to provide independence between test methods. 
This way instance variable values are not being reused across test methods as each test method will
run on a new test class instance.

Exercise 16: For comparing primitive types, assertTrue() will check for a condition and assertEquals() will test the equality of the 
expected value with the returning value. assertEquals() will provide a better error information when the test fails whereas assertTrue() can't. 

Exercise 17: Generally speaking, we should not test private methods because we would test the implementation 
rather than the functionality. But if testing the private method is necessary then we can use reflection 
and using setAccessible(true). This way the private method can be called outside the class with 
setAccessible(true). For MapParser Class, we need to test the private method neither tests them
 in isolation. The private methods are being used by the public methods like makeGrid() in parseMap() 
 so in this case, a solution is to test the public methods so it will hit all the private methods.