package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.MultiLevelLauncher;

/**
 * Class for testing different paths in a multi level game.
 */
public class MultiLevelGameTest extends GameTest {

    @Override
    public void init(String[] mapName) {
        setLauncher(new MultiLevelLauncher().withMapFiles(mapName));
    }
}
