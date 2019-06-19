package nl.tudelft.jpacman.integration.suspension;

import nl.tudelft.jpacman.Launcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing scenario 4.
 */
public class UserStory4 {

    private Launcher launcher;

    /**
     * Initialize Launcher().
     * */
    @BeforeEach
    void setup() {
        launcher = new Launcher();
    }

    /**
     * Testing when the player stops the game.
     */
    @Test
    void resumeGame() {
        launcher.launch();
        launcher.getGame().start();
        launcher.getGame().stop();
        assertThat(launcher.getGame().isInProgress()).isFalse();
    }

    /**
     * Testing when the player starts the game, resume and starts again.
     */
    @Test
    void startGame() {
        launcher.launch();

        launcher.getGame().start();
        assertThat(launcher.getGame().isInProgress()).isTrue();

        launcher.getGame().stop();
        assertThat(launcher.getGame().isInProgress()).isFalse();

        launcher.getGame().start();
        assertThat(launcher.getGame().isInProgress()).isTrue();
    }


}
