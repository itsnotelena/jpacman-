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


    /**
     * Test when game is not started and you want to move towards a wall.
     */
    @Test
    void testNotStartedMoveWall() {
        Launcher launcher = new Launcher().withMapFile("/moveWall.txt");
        launcher.launch();

        Game game = launcher.getGame();
        Player player = game.getPlayers().get(0);
        Square square = player.getSquare();

        assertThat(game.isInProgress()).isFalse();

        game.move(player, Direction.NORTH);
        assertThat(player.getSquare()).isEqualTo(square);

        assertThat(game.isInProgress()).isFalse();
    }


    /**
     * Test when game is not started and you want to move to an empty square.
     */
    @Test
    void testNotStartedMoveEmpty() {
        Launcher launcher = new Launcher().withMapFile("/moveEmpty.txt");
        launcher.launch();

        Game game = launcher.getGame();
        Player player = game.getPlayers().get(0);

        assertThat(game.isInProgress()).isFalse();

        game.move(player, Direction.EAST);
        assertThat(game.isInProgress()).isFalse();
    }


    /**
     * Test when the game is not started and you want to move towards a pellet.
     */
    @Test
    void testNotStartedMovePellet() {
        Level.LevelObserver levelObserver = Mockito.mock(Level.LevelObserver.class);
        Launcher launcher = new Launcher().withMapFile("/mapTest.txt");
        launcher.launch();

        Game game = launcher.getGame();
        game.getLevel().addObserver(levelObserver);
        Player player = game.getPlayers().get(0);

        assertThat(game.isInProgress()).isFalse();

        game.move(player, Direction.EAST);

        assertThat(game.isInProgress()).isFalse();
        assertThat(player.getScore()).isEqualTo(0);
    }


    /**
     * Test when the game is stopped and you want to move towards a wall.
     */
    @Test
    void testSuspendMoveWall() {
        Launcher launcher = new Launcher().withMapFile("/moveWall.txt");
        launcher.launch();

        Game game = launcher.getGame();
        Player player = game.getPlayers().get(0);

        game.start();
        assertThat(game.isInProgress()).isTrue();

        game.stop();
        assertThat(game.isInProgress()).isFalse();

        game.move(player, Direction.NORTH);

        assertThat(game.isInProgress()).isFalse();
    }


    /**
     * Test when the game is stopped and you want to move to an empty box.
     */
    @Test
    void testSuspendMoveEmpty() {
        Launcher launcher = new Launcher().withMapFile("/moveEmpty.txt");
        launcher.launch();

        Game game = launcher.getGame();
        Player player = game.getPlayers().get(0);
        Square square = player.getSquare();

        game.start();
        assertThat(game.isInProgress()).isTrue();

        game.stop();
        assertThat(game.isInProgress()).isFalse();

        game.move(player, Direction.EAST);
        assertThat(player.getSquare()).isEqualTo(square);

        assertThat(game.isInProgress()).isFalse();
    }


    /**
     * Test when the game is started and you press start again.
     */
    @Test
    void testInGameStart() {
        Launcher launcher = new Launcher().withMapFile("/mapTest.txt");
        launcher.launch();

        Game game = launcher.getGame();
        game.start();
        assertThat(game.isInProgress()).isTrue();

        game.start();
        assertThat(game.isInProgress()).isTrue();
    }


    /**
     * Test when the game is won and you try to start it again.
     */
    @Test
    void testWinStart() {
        Level.LevelObserver levelObserver = Mockito.mock(Level.LevelObserver.class);
        Launcher launcher = new Launcher().withMapFile("/mapLose.txt");
        launcher.launch();

        Game game = launcher.getGame();
        game.getLevel().addObserver(levelObserver);
        game.start();
        Player player = game.getPlayers().get(0);

        game.move(player, Direction.EAST);
        Mockito.verify(levelObserver, Mockito.times(1)).levelWon();

        game.start();
        assertThat(game.isInProgress()).isFalse();
    }


    /**
     * Test when the game is lost and you try to start it again.
     */
    @Test
    void testLoseStart() {
        Level.LevelObserver levelObserver = Mockito.mock(Level.LevelObserver.class);
        Launcher launcher = new Launcher().withMapFile("/mapLose.txt");
        launcher.launch();

        Game game = launcher.getGame();
        game.getLevel().addObserver(levelObserver);
        game.start();
        Player player = game.getPlayers().get(0);

        game.move(player, Direction.SOUTH);
        Mockito.verify(levelObserver, Mockito.times(1)).levelLost();

        game.start();
        assertThat(game.isInProgress()).isFalse();
    }
}
