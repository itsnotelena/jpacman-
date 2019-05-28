package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        //Mockito.when()
        mapParser = new MapParser(levelFactory, boardFactory);
    }


    /**
     * Verifying the methods are called the right amount of times. (Good weather case).
     * Suppressed the MagicNumber warning - we need to check how many
     * times a method is called.
     */
    @SuppressWarnings("MagicNumber")
    @Test
    void testParseMapCharsTest() {
        List<String> map = Arrays.asList(
            "########",
            "#G . P G",
            "########"
        );

        mapParser.parseMap(map);
        List<Square> expStart = Arrays.asList(ground);

        Mockito.verify(boardFactory, Mockito.times(7)).createGround();
        Mockito.verify(boardFactory, Mockito.times(17)).createWall();
        Mockito.verify(levelFactory, Mockito.times(1)).createPellet();
        Mockito.verify(levelFactory, Mockito.times(2)).createGhost();
        Mockito.verify(pellet, Mockito.times(1)).occupy(ground);

        Mockito.verify(boardFactory, Mockito.times(1)).createBoard(Mockito.any());
        Mockito.verify(levelFactory, Mockito.times(1)).createLevel(
            Mockito.any(),
            Mockito.any(),
            Mockito.eq(expStart)
        );

        Mockito.verifyNoMoreInteractions(boardFactory);
        Mockito.verifyNoMoreInteractions(levelFactory);
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
