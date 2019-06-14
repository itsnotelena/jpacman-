package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Class for testing the different paths in the transition tree.
 */
public class GameTest {

    /**
     * Test for consuming one of the pellets(not the last one).
     */
    @Test
    void testConsumePellet() {
        Launcher launcher = new Launcher().withMapFile("/mapTest.txt");

        launcher.launch();
        final int score = 20;

        Game game = launcher.getGame();
        assertThat(game.isInProgress()).isFalse();

        game.start();
        Player myPlayer = game.getPlayers().get(0);

        game.move(myPlayer, Direction.EAST);
        game.move(myPlayer, Direction.SOUTH);

        assertThat(myPlayer.getScore()).isEqualTo(score);
        assertThat(game.isInProgress()).isTrue();

        game.stop();
    }


    /**
     * Test for consuming all of the pellets and thus winning the game.
     */
    @Test
    void testWin() {
        Level.LevelObserver levelObserver = Mockito.mock(Level.LevelObserver.class);
        Launcher launcher = new Launcher().withMapFile("/mapTest.txt");

        launcher.launch();
        Game game = launcher.getGame();
        game.getLevel().addObserver(levelObserver);
        assertThat(game.isInProgress()).isFalse();

        game.start();
        Player myPlayer = game.getPlayers().get(0);

        game.move(myPlayer, Direction.EAST);
        game.move(myPlayer, Direction.EAST);
        game.move(myPlayer, Direction.SOUTH);
        game.move(myPlayer, Direction.WEST);
        game.move(myPlayer, Direction.WEST);
        game.move(myPlayer, Direction.SOUTH);
        game.move(myPlayer, Direction.EAST);

        assertThat(myPlayer.isAlive()).isTrue();
        Mockito.verify(levelObserver, Mockito.times(1)).levelWon();
        assertThat(game.isInProgress()).isFalse();

        game.stop();
    }


    /**
     * Test for collision with a ghost and thus losing the game.
     */
    @Test
    void testLose() {
        Level.LevelObserver levelObserver = Mockito.mock(Level.LevelObserver.class);
        Launcher launcher = new Launcher().withMapFile("/mapLose.txt");

        launcher.launch();
        Game game = launcher.getGame();
        game.getLevel().addObserver(levelObserver);
        assertThat(game.isInProgress()).isFalse();

        game.start();
        Player player = game.getPlayers().get(0);

        game.move(player, Direction.SOUTH);

        assertThat(player.isAlive()).isFalse();
        Mockito.verify(levelObserver, Mockito.times(1)).levelLost();
        assertThat(game.isInProgress()).isFalse();

        game.stop();
    }


    /**
     * Test for moving to an empty cell and thus not changing the state of the game.
     */
    @Test
    void testMoveEmpty() {
        Launcher launcher = new Launcher().withMapFile("/moveEmpty.txt");

        launcher.launch();
        Game game = launcher.getGame();
        assertThat(game.isInProgress()).isFalse();

        game.start();
        Player player = game.getPlayers().get(0);

        game.move(player, Direction.EAST);

        assertThat(player.isAlive()).isTrue();
        assertThat(player.getScore()).isEqualTo(0);
        assertThat(game.isInProgress()).isTrue();

        game.stop();
    }


    /**
     * Test for moving towards a wall and thus staying in the same square.
     */
    @Test
    void testMoveWall() {
        Launcher launcher = new Launcher().withMapFile("/moveWall.txt");

        launcher.launch();
        Game game = launcher.getGame();
        assertThat(game.isInProgress()).isFalse();

        game.start();
        Player player = game.getPlayers().get(0);
        Square square = player.getSquare();

        game.move(player, Direction.NORTH);

        assertThat(player.getSquare()).isEqualTo(square);
        assertThat(player.isAlive()).isTrue();
        assertThat(player.getScore()).isEqualTo(0);
        assertThat(game.isInProgress()).isTrue();

        game.stop();
    }


    /**
     * Test for stopping and resuming the game.
     */
    @Test
    void testStopStart() {
        Launcher launcher = new Launcher().withMapFile("/sampleMap.txt");

        launcher.launch();
        Game game = launcher.getGame();
        assertThat(game.isInProgress()).isFalse();

        game.start();
        assertThat(game.isInProgress()).isTrue();

        game.stop();
        assertThat(game.isInProgress()).isFalse();

        game.start();
        assertThat(game.isInProgress()).isTrue();

        game.stop();
    }
}
