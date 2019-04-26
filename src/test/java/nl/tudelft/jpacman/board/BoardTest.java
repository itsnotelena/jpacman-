package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

public class BoardTest {

    private Square [][] board;

    @Test
    void BasicBoardTest() {

        Square[][] board1 = new BasicSquare[1][1];
        for (int i=0; i<board1.length; i++) {
            for (int j=0; j<board1[i].length; j++) {
                board1[i][j] = new BasicSquare();
            }
        }

        Board test = new Board( board1 );

        assertThat( test.getWidth()).isEqualTo( 1 );
        assertThat( test.getHeight() ).isEqualTo( 1 );

    }

//    @Test
//    void NullSquareTest() {
//
//        Square[][] grid = new BasicSquare[1][0];
//        Board board = new Board(grid);
//        assertThat( board.squareAt( 1,0) ).isEqualTo( grid[1][0] );
//
//    }



}
