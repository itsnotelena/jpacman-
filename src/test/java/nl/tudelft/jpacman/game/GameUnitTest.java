package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Checking the functionality of the Game class.
 */
public class GameUnitTest {

    private Game game;
    private Player player;
    private Level level;
    private PointCalculator pointCalculator;

    /**
     * Initialize a single player game.
     */
    @BeforeEach
    void init() {
        player = Mockito.mock(Player.class);
        level = Mockito.mock(Level.class);
        pointCalculator = Mockito.mock(PointCalculator.class);
        game = new SinglePlayerGame(player, level, pointCalculator);
    }


    /**
     * Test when there is an alive player and the number of pellets is positive.
     */
    @Test
    void testPlayerAliveRemainingPellets() {
        Mockito.when(level.isAnyPlayerAlive()).thenReturn(true);
        Mockito.when(level.remainingPellets()).thenReturn(1);

        game.start();

        Assertions.assertThat(game.isInProgress()).isTrue();
        Mockito.verify(level, Mockito.times(1)).addObserver(Mockito.eq(game));
        Mockito.verify(level, Mockito.times(1)).start();
    }


    /**
     * Test when there is an alive player and not any pellets left.
     */
    @Test
    void testPlayerAliveNotRemainingPellets() {
        Mockito.when(level.isAnyPlayerAlive()).thenReturn(true);
        Mockito.when(level.remainingPellets()).thenReturn(0);

        game.start();

        Assertions.assertThat(game.isInProgress()).isFalse();
        Mockito.verify(level, Mockito.times(0)).addObserver(Mockito.any());
        Mockito.verify(level, Mockito.times(0)).start();
    }


    /**
     * Test when there is not an alive player and the number of remaining pellets is positive.
     */
    @Test
    void testPlayerNotAliveRemainingPellets() {
        Mockito.when(level.isAnyPlayerAlive()).thenReturn(false);
        Mockito.when(level.remainingPellets()).thenReturn(1);

        game.start();

        Assertions.assertThat(game.isInProgress()).isFalse();
        Mockito.verify(level, Mockito.times(0)).addObserver(Mockito.any());
        Mockito.verify(level, Mockito.times(0)).start();
    }


    /**
     * Test when we call the start method 2 times,
     * so that the game is in progress for the second one.
     */
    @Test
    void testInProgressTrue() {
        Mockito.when(level.isAnyPlayerAlive()).thenReturn(true);
        Mockito.when(level.remainingPellets()).thenReturn(1);

        game.start();
        game.start();

        Assertions.assertThat(game.isInProgress()).isTrue();
        Mockito.verify(level, Mockito.times(1)).addObserver(Mockito.eq(game));
        Mockito.verify(level, Mockito.times(1)).start();
    }
}
