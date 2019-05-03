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

##3.2 Unit Testing     
Exercice 10

2- 

3.a- LevelFactory, BoardFactory and GhostFactory are the classes that are required
in the constructor. 