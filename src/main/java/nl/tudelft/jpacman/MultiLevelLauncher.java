package nl.tudelft.jpacman;

import nl.tudelft.jpacman.game.GameFactory;
import nl.tudelft.jpacman.game.MultiLevelGame;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.points.PointCalculatorLoader;

import java.io.IOException;

/**
 * Used to launch a multilevel game.
 */
public class MultiLevelLauncher extends Launcher {

    private MultiLevelGame multiGame;
    private String[] maps = {DEFAULT_MAP};

    /**
     * @return The game object this launcher will start when {@link #launch()}
     * is called.
     */
    @Override
    public MultiLevelGame getGame() {
        return multiGame;
    }

    /**
     * Creates a new game using the level from {@link #makeLevel()}.
     *
     * @return a new Game.
     */
    @Override
    public MultiLevelGame makeGame() {
        GameFactory gf = getGameFactory();
        Level[] levels = makeLevels();
        multiGame = gf.createMultiLevelGame(levels, new PointCalculatorLoader().load());
        return multiGame;
    }


    /**
     * Create the levels for this game.
     *
     * @return an array of levels for this game
     */
    public Level[] makeLevels() {
        final int nbrLevels = 4;
        Level[] levels = new Level[nbrLevels];

        for (int i = 0; i < maps.length; i++) {
            try {
                levels[i] = getMapParser().parseMap(maps[i]);
            } catch (IOException e) {
                throw new PacmanConfigurationException(
                    "Unable to create level, name = " + getLevelMap(), e);
            }
        }

        return levels;
    }

    /**
     * Create levels from text files.
     *
     * @param maps the maps for the levels
     * @return a new multilevel launcher
     */
    public MultiLevelLauncher withMapFiles(String[] maps) {
        this.maps = maps.clone();
        return this;
    }
}
