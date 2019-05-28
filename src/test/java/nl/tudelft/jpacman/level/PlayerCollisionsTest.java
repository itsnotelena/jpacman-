package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

/**
 * Class to verify that collisions between different units are handled correctly.
 */
public class PlayerCollisionsTest extends CollisionMapTest {

    /**
     * Initialize a player, a pellet and a ghost using for testing.
     * Also, initialize a PlayerCollisions object.
     */
    @BeforeEach
    @Override
    void init() {
        this.setPointCalculator(Mockito.mock(PointCalculator.class));
        this.setPlayer(Mockito.mock(Player.class));
        this.setPellet(Mockito.mock(Pellet.class));
        this.setGhost(Mockito.mock(Ghost.class));
        this.setPlayerCollisions(new PlayerCollisions(this.getPointCalculator()));
    }
}
