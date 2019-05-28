package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class MapParserTest {

    private LevelFactory levelFactory;
    private BoardFactory boardFactory;
    private MapParser mapParser;
    private Pellet pellet;
    private Square ground;
    private Square wall;
    private Ghost ghost;


    /**
     * Initializing the new map parser.
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
     */
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
}
