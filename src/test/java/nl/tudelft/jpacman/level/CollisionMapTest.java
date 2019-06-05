package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Tests for classes that implement the CollisionMap class.
 */
public abstract class CollisionMapTest {
    private PointCalculator pointCalculator;
    private Player player;
    private Pellet pellet;
    private Ghost ghost;
    private CollisionMap playerCollisions;


    /**
     * Setting the point calculator.
     * @param pointCalculator the point calculator to be set
     */
    public void setPointCalculator(PointCalculator pointCalculator) {
        this.pointCalculator = pointCalculator;
    }


    /**
     * Setting the player.
     * @param player the player to be set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }


    /**
     * Setting the pellet.
     * @param pellet the pellet to be set
     */
    public void setPellet(Pellet pellet) {
        this.pellet = pellet;
    }


    /**
     * Setting the ghost.
     * @param ghost the ghost to be set
     */
    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }


    /**
     * Setting the player collisions.
     * @param playerCollisions the player collisions to be set
     */
    public void setPlayerCollisions(CollisionMap playerCollisions) {
        this.playerCollisions = playerCollisions;
    }


    /**
     * Getting the point calculator of this object.
     * @return the point calculator
     */
    public PointCalculator getPointCalculator() {
        return pointCalculator;
    }


    /**
     * Initialization of parameters for the test cases.
     */
    @BeforeEach
    abstract void init();



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
