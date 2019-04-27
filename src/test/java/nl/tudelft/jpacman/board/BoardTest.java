package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * test class for Board class.
 *
 * @author Elena Ibanez
 */
public class BoardTest {

    private Square [][] board;

    /**
     * Tests that the board is valid.
     */
    @Test
    void basicBoardTest() {

        Square[][] board1 = new BasicSquare[1][1];
        for (int i = 0; i < board1.length; i++) {
            for (int j = 0; j < board1[i].length; j++) {
                board1[i][j] = new BasicSquare();
            }
        }

        Board test = new Board(board1);

        assertThat(test.getWidth()).isEqualTo(1);
        assertThat(test.getHeight()).isEqualTo(1);

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
