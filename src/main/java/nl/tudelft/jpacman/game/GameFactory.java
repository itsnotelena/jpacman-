package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.points.PointCalculator;

/**
 * Factory that provides Game objects.
 *
 * @author Jeroen Roosen
 */
public class GameFactory {

    /**
     * The factory providing the player objects.
     */
    private final PlayerFactory playerFactory;

    /**
     * Creates a new game factory.
     *
     * @param playerFactory The factory providing the player objects.
     */
    public GameFactory(PlayerFactory playerFactory) {
        this.playerFactory = playerFactory;
    }

    /**
     * Creates a game for a single level with one player.
     *
     * @param level           The level to create a game for.
     * @param pointCalculator The way to calculate points upon collisions.
     * @return A new single player game.
     */
    public Game createSinglePlayerGame(Level level, PointCalculator pointCalculator) {
        return new SinglePlayerGame(playerFactory.createPacMan(), level, pointCalculator);
    }

    /**
     * Creates a game for multiple levels with one player.
     *
     * @param levels           The level to create a game for.
     * @param pointCalculator The way to calculate points upon collisions.
     * @return A new multi level game.
     */
    public MultiLevelGame createMultiLevelGame(Level[] levels, PointCalculator pointCalculator) {
        return new MultiLevelGame(playerFactory.createPacMan(), levels, pointCalculator);
    }

    /**
     * Returns the player factory associated with this game factory.
     *
     * @return The player factory associated with this game factory.
     */
    protected PlayerFactory getPlayerFactory() {
        return playerFactory;
    }
}
