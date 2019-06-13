package nl.tudelft.jpacman.integration.suspension;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.*;
import nl.tudelft.jpacman.level.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

public class story2 {

    private Launcher launcher;

    @BeforeEach
    void setup(){
        launcher = new Launcher();

    }

    @Test
    void playerEatsPellet() throws IOException {
        launcher.withMapFile("/map_scenario2-1.txt");
        launcher.launch();
        launcher.getGame().start();

        Player player = launcher.getGame().getPlayers().get(0);
        Square beginning = player.getSquare();
        Square destination = player.getSquare().getSquareAt(Direction.EAST);

        assertThat(player.getSquare()).isEqualTo(beginning);
        assertThat(player.getScore()).isEqualTo(0);
        assertThat(launcher.getGame().getLevel().remainingPellets()).isEqualTo(1);

        launcher.getGame().move(player, Direction.EAST);
        assertThat(player.getScore()).isEqualTo(10);
        assertThat(launcher.getGame().getLevel().remainingPellets()).isEqualTo(0);
        assertThat(player.getSquare()).isEqualTo(destination);
    }

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

        launcher.getGame().move(player,Direction.EAST);
        assertThat(player.getScore()).isEqualTo(0);
        assertThat(player.getSquare()).isEqualTo(destination);
    }


}

    

