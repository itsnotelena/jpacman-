package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Class to verify that collisions between different units are handled correctly.
 */
public class PlayerCollisionsTest {

    private PointCalculator pointCalculator;
    private Player player;
    private Pellet pellet;
    private Ghost ghost;
    private PlayerCollisions playerCollisions;


    /**
     * Initialize a player, a pellet and a ghost using for testing.
     * Also, initialize a PlayerCollisions object.
     */
    @BeforeEach
    void init() {
        pointCalculator = Mockito.mock(PointCalculator.class);
        player = Mockito.mock(Player.class);
        pellet = Mockito.mock(Pellet.class);
        ghost = Mockito.mock(Ghost.class);
        playerCollisions = new PlayerCollisions(pointCalculator);
    }


    /**
     * Test where first parameter for collide method is a player and the second is also a player.
     */
    @Test
    void testPlayerPlayer() {
        Player player1 = Mockito.mock(Player.class);
        playerCollisions.collide(player, player1);
        Mockito.verifyZeroInteractions(player, player1);
    }



    /**
     * Test where first parameter for collide method is a player and the second is a pellet.
     */
    @Test
    void testPlayerPellet() {
        playerCollisions.collide(player, pellet);

        Mockito.verify(pointCalculator, Mockito.times(1)).consumedAPellet(
            Mockito.eq(player),
            Mockito.eq(pellet)
        );

        Mockito.verify(pellet, Mockito.times(1)).leaveSquare();

        Mockito.verifyNoMoreInteractions(player, pellet);
    }


    /**
     * Test where first parameter for collide method is a player and the second is a ghost.
     */
    @Test
    void testPlayerGhost() {
        playerCollisions.collide(player, ghost);

        Mockito.verify(pointCalculator, Mockito.times(1)).collidedWithAGhost(
            Mockito.eq(player),
            Mockito.eq(ghost)
        );

        Mockito.verify(player, Mockito.times(1)).setAlive(false);

        Mockito.verify(player, Mockito.times(1)).setKiller(Mockito.eq(ghost));

        Mockito.verifyNoMoreInteractions(player, ghost);
    }


    /**
     * Test where first parameter for collide method is a ghost and the second one is a player.
     */
    @Test
    void testGhostPlayer() {
        playerCollisions.collide(ghost, player);

        Mockito.verify(pointCalculator, Mockito.times(1)).collidedWithAGhost(
            Mockito.eq(player),
            Mockito.eq(ghost)
        );

        Mockito.verify(player, Mockito.times(1)).setAlive(false);

        Mockito.verify(player, Mockito.times(1)).setKiller(Mockito.eq(ghost));

        Mockito.verifyNoMoreInteractions(player, ghost);
    }


    /**
     * Test where first parameter for collide method is a ghost and the second one is a pellet.
     */
    @Test
    void testGhostPellet() {
        playerCollisions.collide(ghost, pellet);

        Mockito.verifyZeroInteractions(ghost, pellet);
    }


    /**
     * Test where first parameter for collide method is a ghost and the second one is also a ghost.
     */
    @Test
    void testGhostGhost() {
        Ghost ghost1 = Mockito.mock(Ghost.class);
        playerCollisions.collide(ghost, ghost1);

        Mockito.verifyZeroInteractions(ghost, ghost1);
    }


    /**
     * Test where first parameter for collide method is a pellet and the second one is a player.
     */
    @Test
    void testPelletPlayer() {
        playerCollisions.collide(pellet, player);

        Mockito.verify(pointCalculator, Mockito.times(1)).consumedAPellet(
            Mockito.eq(player),
            Mockito.eq(pellet)
        );

        Mockito.verify(pellet, Mockito.times(1)).leaveSquare();

        Mockito.verifyNoMoreInteractions(pellet, player);
    }


    /**
     * Test where first parameter for collide method is a pellet and the second one is a ghost.
     */
    @Test
    void testPelletGhost() {
        playerCollisions.collide(pellet, ghost);

        Mockito.verifyZeroInteractions(pellet, ghost);
    }


    /**
     * Test where first parameter for collide method is pellet and the second one is also a pellet.
     */
    @Test
    void testPelletPellet() {
        Pellet pellet1 = Mockito.mock(Pellet.class);
        playerCollisions.collide(pellet, pellet1);

        Mockito.verifyZeroInteractions(pellet, pellet1);
    }
}
