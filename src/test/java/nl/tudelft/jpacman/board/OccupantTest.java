package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test suite to confirm that {@link Unit}s correctly (de)occupy squares.
 *
 * @author Jeroen Roosen 
 *
 */
class OccupantTest {

    /**
     * The unit under test.
     */
    private Unit unit;

    /**
     * Resets the unit under test.
     */
    @BeforeEach
    void setUp() {
        unit = new BasicUnit();
    }

    /**
     * Asserts that a unit has no square to start with.
     */
    @Test
    void noStartSquare() {
        assertThat(unit.hasSquare()).isFalse();
    }

    /**
     * Tests that the unit indeed has the target square as its base after
     * occupation.
     */
    @Test
    void testOccupy() {
        Square targetTest = new BasicSquare();
        unit.occupy(targetTest);
        assertThat(unit.getSquare()).isEqualTo(targetTest);
    }

    /**
     * Test that the unit indeed has the target square as its base after
     * double occupation.
     */
    @Test
    void testReoccupy() {
        Square targetTest1 = new BasicSquare();
        Square targetTest2 = new BasicSquare();

        unit.occupy(targetTest1);
        assertThat(unit.getSquare()).isEqualTo(targetTest1);

        unit.occupy(targetTest2);
        assertThat(unit.getSquare()).isEqualTo(targetTest2);
    }
}
