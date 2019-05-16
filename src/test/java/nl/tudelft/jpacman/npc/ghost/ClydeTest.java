package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.points.DefaultPointCalculator;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * Class that tests the nextAiMove() method in the Clyde class.
 */
public class ClydeTest {

    private PacManSprites pacManSprites;
    private BoardFactory boardFactory;
    private GhostFactory ghostFactory;
    private PointCalculator pointCalculator;
    private LevelFactory levelFactory;
    private GhostMapParser ghostMapParser;
    private PlayerFactory playerFactory;

    /**
     * Instantiate the GhostMapParser that will help us with the testing.
     */
    @BeforeEach
    void init() {
        pacManSprites = new PacManSprites();
        boardFactory = new BoardFactory(pacManSprites);
        ghostFactory = new GhostFactory(pacManSprites);
        pointCalculator = new DefaultPointCalculator();
        levelFactory = new LevelFactory(pacManSprites, ghostFactory, pointCalculator);
        playerFactory = new PlayerFactory(pacManSprites);

        ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
    }


    /**
     * Test in which Clyde is located near PacMan (on 8 squares distance),
     * so it will try to run away.
     */
    @Test
    void testTooClose() {
        Level level = ghostMapParser.parseMap(
            Lists.newArrayList("############", "P       C###", "############")
        );
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.WEST);
        level.registerPlayer(player);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());

        assertThat(clyde.nextAiMove()).isEqualTo(Optional.of(Direction.EAST));
    }


    /**
     * Test in which Clyde is far from PacMan (on 9 squares distance),
     * so it tries to go towards PacMan.
     */
    @Test
    void testTooFar() {
        Level level = ghostMapParser.parseMap(
            Lists.newArrayList("############", "P        C##", "############")
        );
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.EAST);
        level.registerPlayer(player);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());

        assertThat(clyde.nextAiMove()).isEqualTo(Optional.of(Direction.WEST));
    }

    /**
     * Bad weather test case, in which there isn't a player playing the current level.
     */
    @Test
    void testNoPlayer() {
        Level level = ghostMapParser.parseMap(
            Lists.newArrayList("#####", "##C  ", "     ")
        );
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());

        assertThat(clyde.nextAiMove()).isEqualTo(Optional.empty());
    }


    /**
     * Bad weather test case, in which there isn't a valid path between PacMan and Clyde.
     */
    @Test
    void testNoPathBetweenPlayerAndClyde() {
        Level level = ghostMapParser.parseMap(
            Lists.newArrayList("######", "#P##C ", " ###  ")
        );
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.EAST);
        level.registerPlayer(player);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());

        assertThat(clyde.nextAiMove()).isEqualTo(Optional.empty());
    }
}
