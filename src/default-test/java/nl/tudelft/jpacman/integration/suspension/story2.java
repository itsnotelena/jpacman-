package nl.tudelft.jpacman.integration.suspension;


import nl.tudelft.jpacman.PacmanConfigurationException;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.*;
import nl.tudelft.jpacman.level.*;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.points.DefaultPointCalculator;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class story2 {

    private Launcher launcher;
    private PacManSprites spriteStore;
    private MapParser mapParser;
    private GhostFactory ghostFactory;
    private PointCalculator pointCalculator;
    private BoardFactory boardFactory;
    private LevelFactory levelFactory;
    private PlayerFactory playerFactory;
    private PlayerCollisions playerCollisions;
    private Pellet pellet;

    @BeforeEach
    void setup(){
        launcher = new Launcher();
        pointCalculator = new DefaultPointCalculator();

        boardFactory = new BoardFactory( spriteStore );
        ghostFactory = new GhostFactory( spriteStore );
        playerFactory = new PlayerFactory( spriteStore );
        playerCollisions = new PlayerCollisions( pointCalculator );
        levelFactory = new LevelFactory( spriteStore,ghostFactory,pointCalculator );
        mapParser = new MapParser(levelFactory, boardFactory);

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


}

    

