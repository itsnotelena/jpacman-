package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * test class for Board class.
 *
 * @author Elena Ibanez
 */
public class BoardTest {

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


    /**
     * Series of tests for the Board.withinBorders() method.
     * @param length the x-coordinate of the square
     * @param width the y-coordinate of the square
     * @param expected the expected returned value from the method
     */
    @ParameterizedTest
    @CsvSource({
        "0 , 0, true",
        "1, 0, false",
        "-1, 0, false",
        "0, 1, false",
        "0, -1, false"})
    void testInRangeOfBoard(int length, int width, boolean expected) {
        Square[][] squares = new BasicSquare[1][1];
        squares[0][0] = new BasicSquare();
        Board board = new Board(squares);

        assertThat(board.withinBorders(length, width)).isEqualTo(expected);
    }

}
