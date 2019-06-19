package nl.tudelft.jpacman.integration.suspension;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

/**
 * Testing scenario 2.
 */
public class UserStory2 {

    private Launcher launcher;

    /**
     * Initialize launcher.
     */
    @BeforeEach
    void setup() {
        launcher = new Launcher();
    }

    /**
     * Testing when the player consumes a pellet.
     * @throws IOException when file is not found.
     */
    @Test
    void playerEatsPellet() throws IOException {
        final int score = 10;

        launcher.withMapFile("/map_scenario2-1.txt");
        launcher.launch();
        launcher.getGame().start();

        Player player = launcher.getGame().getPlayers().get(0);
        Square beginning = player.getSquare();

        assertThat(player.getSquare()).isEqualTo(beginning);
        assertThat(player.getScore()).isEqualTo(0);
        assertThat(launcher.getGame().getLevel().remainingPellets()).isEqualTo(1);

        launcher.getGame().move(player, Direction.EAST);
        assertThat(player.getScore()).isEqualTo(score);
        assertThat(launcher.getGame().getLevel().remainingPellets()).isEqualTo(0);
    }

    /**
     * Testing that player can move freely in an empty square.
     * @throws IOException when file is not found.
     */
    @Test
    void playerEmptySquare() throws IOException {
        launcher.withMapFile("/map_scenario2-2.txt");
        launcher.launch();
        launcher.getGame().start();

        Player player = launcher.getGame().getPlayers().get(0);
        Square beginning = player.getSquare();
        Square destination = player.getSquare().getSquareAt(Direction.EAST);

        assertThat(player.getSquare()).isEqualTo(beginning);
        assertThat(player.getScore()).isEqualTo(0);

        launcher.getGame().move(player, Direction.EAST);
        assertThat(player.getScore()).isEqualTo(0);
        assertThat(player.getSquare()).isEqualTo(destination);
    }

    /**
     * Testing when the player can't move next to a wall.
     * @throws IOException when file is not found.
     */
    @Test
    void playerCantMove() throws IOException {
        launcher.withMapFile("/map_scenario2-3.txt");
        launcher.launch();
        launcher.getGame().start();

        Player player = launcher.getGame().getPlayers().get(0);
        Square beginning = player.getSquare();

        assertThat(player.getSquare()).isEqualTo(beginning);

        launcher.getGame().move(player, Direction.EAST);
        assertThat(player.getSquare()).isEqualTo(beginning);
    }


    /**
     * Testing when player is killed.
     * @throws IOException when file is not found.
     */
    @Test
    void playerDies() throws IOException {
        launcher.withMapFile("/map_scenario2-4.txt");
        launcher.launch();
        launcher.getGame().start();

        Player player = launcher.getGame().getPlayers().get(0);
        Square beginning = player.getSquare();

        assertThat(player.getSquare()).isEqualTo(beginning);

        launcher.getGame().move(player, Direction.EAST);
        assertThat(player.isAlive()).isFalse();
        assertThat(launcher.getGame().isInProgress()).isFalse();
    }

    /**
     * Testing when player consumes all pellets and wins the game.
     * @throws IOException when file is not found.
     */
    @Test
    void playerWin() throws IOException {
        launcher.withMapFile("/map_scenario2-5.txt");
        launcher.launch();
        launcher.getGame().start();

        //the game stop
        assertThat(launcher.getGame().isInProgress()).isFalse();
    }
}


