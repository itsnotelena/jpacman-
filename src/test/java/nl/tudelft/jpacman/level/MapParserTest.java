package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Testing the MapParser class using Mock objects.
 */
public class MapParserTest {

    private LevelFactory levelFactory;
    private BoardFactory boardFactory;
    private MapParser mapParser;
    private Pellet pellet;
    private Square ground;
    private Square wall;
    private Ghost ghost;


    /**
     * Initializing the new map parser and all of its dependencies.
     */
    @BeforeEach
    public void init() {
        levelFactory = Mockito.mock(LevelFactory.class);
        boardFactory = Mockito.mock(BoardFactory.class);
        ground = Mockito.mock(Square.class);
        wall = Mockito.mock(Square.class);
        pellet = Mockito.mock(Pellet.class);
        ghost = Mockito.mock(Ghost.class);

        Mockito.when(boardFactory.createGround()).thenReturn(ground);
        Mockito.when(levelFactory.createPellet()).thenReturn(pellet);
        Mockito.when(boardFactory.createWall()).thenReturn(wall);
        Mockito.when(levelFactory.createGhost()).thenReturn(ghost);

        mapParser = new MapParser(levelFactory, boardFactory);
    }


    /**
     * Test a good weather case with a simple map
     * and verify the method calls other methods the right amount of times.
     * @throws IOException when the resource could not be read
     * @throws PacmanConfigurationException when the file is not found
     */
    @Test
    void testParseMapGoodWeatherTest() throws IOException, PacmanConfigurationException {
        mapParser.parseMap("/simplemap.txt");
        List<Square> expStart = Arrays.asList(ground);

        final int timesCreateGround = 7;
        final int timesCreateWall = 17;
        final int timesCreatePellet = 1;
        final int timesCreateGhost = 2;
        final int timesOccupy = 1;
        final int timesCreateBoard = 1;
        final int timesCreateLevel = 1;

        Mockito.verify(boardFactory, Mockito.times(timesCreateGround)).createGround();
        Mockito.verify(boardFactory, Mockito.times(timesCreateWall)).createWall();
        Mockito.verify(levelFactory, Mockito.times(timesCreatePellet)).createPellet();
        Mockito.verify(levelFactory, Mockito.times(timesCreateGhost)).createGhost();
        Mockito.verify(pellet, Mockito.times(timesOccupy)).occupy(ground);

        Mockito.verify(boardFactory, Mockito.times(timesCreateBoard)).createBoard(Mockito.any());
        Mockito.verify(levelFactory, Mockito.times(timesCreateLevel)).createLevel(
            Mockito.any(),
            Mockito.any(),
            Mockito.eq(expStart)
        );

        Mockito.verifyNoMoreInteractions(boardFactory);
        Mockito.verifyNoMoreInteractions(levelFactory);
    }


    /**
     * Test only with players and spaces.
     * @throws IOException when the resource could not be read
     * @throws PacmanConfigurationException when the file is not found
     */
    @Test
    void testSpaces() throws IOException, PacmanConfigurationException {
        mapParser.parseMap("/testSpaces.txt");
        List<Square> starts = Arrays.asList(ground, ground, ground);

        final int timesCreateGround = 9;

        Mockito.verify(boardFactory, Mockito.times(timesCreateGround)).createGround();

        Mockito.verify(boardFactory, Mockito.times(1)).createBoard(Mockito.any());
        Mockito.verify(levelFactory, Mockito.times(1)).createLevel(
            Mockito.any(),
            Mockito.any(),
            Mockito.eq(starts)
        );

        Mockito.verifyNoMoreInteractions(boardFactory);
        Mockito.verifyNoMoreInteractions(levelFactory);
    }


    /**
     * Test only with a player and walls.
     * @throws IOException when the resource could not be read
     * @throws PacmanConfigurationException when the file could not be found
     */
    @Test
    void testWalls() throws IOException, PacmanConfigurationException {
        mapParser.parseMap("/testWalls.txt");
        List<Square> starts = Arrays.asList(ground);

        final int timesCreateWall = 8;

        Mockito.verify(boardFactory, Mockito.times(timesCreateWall)).createWall();
        Mockito.verify(boardFactory, Mockito.times(1)).createGround();

        Mockito.verify(boardFactory, Mockito.times(1)).createBoard(Mockito.any());
        Mockito.verify(levelFactory, Mockito.times(1)).createLevel(
            Mockito.any(),
            Mockito.any(),
            Mockito.eq(starts)
        );

        Mockito.verifyNoMoreInteractions(boardFactory);
        Mockito.verifyNoMoreInteractions(levelFactory);
    }


    /**
     * Test only with a player and dots.
     * @throws IOException when the resource cannot be read
     * @throws PacmanConfigurationException when the file is not found
     */
    @Test
    void testDots() throws IOException, PacmanConfigurationException {
        mapParser.parseMap("/testDots.txt");
        List<Square> starts = Arrays.asList(ground);

        final int timesCreateGround = 9;
        final int timesCreatePellet = 8;
        final int timesOccupy = 8;

        Mockito.verify(boardFactory, Mockito.times(timesCreateGround)).createGround();
        Mockito.verify(levelFactory, Mockito.times(timesCreatePellet)).createPellet();
        Mockito.verify(pellet, Mockito.times(timesOccupy)).occupy(ground);

        Mockito.verify(boardFactory, Mockito.times(1)).createBoard(Mockito.any());
        Mockito.verify(levelFactory, Mockito.times(1)).createLevel(
            Mockito.any(),
            Mockito.any(),
            Mockito.eq(starts)
        );

        Mockito.verifyNoMoreInteractions(boardFactory);
        Mockito.verifyNoMoreInteractions(levelFactory);
    }


    /**
     * Test only with ghosts and players.
     * @throws IOException when the resource could not be read
     * @throws PacmanConfigurationException when the file is not found
     */
    @Test
    void testGhostsPlayers() throws IOException, PacmanConfigurationException {
        mapParser.parseMap("/testGhostsPlayers.txt");
        List<Square> starts = Arrays.asList(ground, ground);

        final int timesCreateGhost = 2;
        final int timesOccupy = 2;
        final int timesCreateGround = 4;

        Mockito.verify(boardFactory, Mockito.times(timesCreateGround)).createGround();
        Mockito.verify(levelFactory, Mockito.times(timesCreateGhost)).createGhost();
        Mockito.verify(ghost, Mockito.times(timesOccupy)).occupy(ground);

        Mockito.verify(boardFactory, Mockito.times(1)).createBoard(Mockito.any());
        Mockito.verify(levelFactory, Mockito.times(1)).createLevel(
            Mockito.any(),
            Mockito.any(),
            Mockito.eq(starts)
        );

        Mockito.verifyNoMoreInteractions(boardFactory);
        Mockito.verifyNoMoreInteractions(levelFactory);
    }


    /**
     * Bad weather case when the map is not present in the package.
     */
    @Test
    void testNoSuchFile() {
        assertThrows(PacmanConfigurationException.class, () ->
            mapParser.parseMap("/random.txt")
        );
    }



    /**
     * Bad weather case when the map is null.
     */
    @Test
    void testParseMapExceptionNull() {
        List<String> map = null;
        assertThrows(PacmanConfigurationException.class, () ->
            mapParser.parseMap(map)
        );
    }


    /**
     * Bad weather case when the map is empty.
     */
    @Test
    void testParseMapExceptionNoText() {
        List<String> map = new ArrayList<>();
        assertThrows(PacmanConfigurationException.class, () ->
            mapParser.parseMap(map)
        );
    }


    /**
     * Bad weather case when the map has an empty line.
     */
    @Test
    void testParseMapExceptionEmptyLine() {
        List<String> map = Arrays.asList("");
        assertThrows(PacmanConfigurationException.class, () ->
            mapParser.parseMap(map)
        );
    }


    /**
     * Bad weather case when the rows of the map aren't with the same length.
     */
    @Test
    void testParseMapNotEqualLengthOfLines() {
        List<String> map = Arrays.asList(
            "###",
            "P G",
            "##"
        );
        assertThrows(PacmanConfigurationException.class, () ->
            mapParser.parseMap(map)
        );
    }


    /**
     * Bad weather case when the map contains forbidden characters.
     */
    @Test
    void testParseMapForbiddenCharacter() {
        List<String> map = Arrays.asList(
            "###",
            "P C",
            "###"
        );
        assertThrows(PacmanConfigurationException.class, () ->
            mapParser.parseMap(map)
        );
    }
}
