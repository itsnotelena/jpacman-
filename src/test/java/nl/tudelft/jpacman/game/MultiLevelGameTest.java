package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.MultiLevelLauncher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Class for testing different paths in a multi level game.
 */
public class MultiLevelGameTest extends GameTest {

    private Player player;
    private Level.LevelObserver levelObserver;

    @Override
    public void init(String[] mapName) {
        setLauncher(new MultiLevelLauncher().withMapFiles(mapName));
        levelObserver = Mockito.mock(Level.LevelObserver.class);
    }

    /**
     * Start the game with the next level.
     *
     * @param game the game to be restarted
     */
    void startGameAgain(Game game) {
        game.start();
        game.getLevel().addObserver(levelObserver);
        player = game.getPlayers().get(0);
    }


    @Override
    @Test
    void testWin() {
        String[] maps = {"/sampleMap.txt", "/sampleMap.txt", "/sampleMap.txt", "/sampleMap.txt"};
        init(maps);

        Launcher launcher = getLauncher();
        launcher.launch();
        Game game = launcher.getGame();

        startGameAgain(game);
        game.move(player, Direction.EAST);
        assertThat(game.isInProgress()).isFalse();

        startGameAgain(game);
        game.move(player, Direction.EAST);
        assertThat(game.isInProgress()).isFalse();

        startGameAgain(game);
        game.move(player, Direction.EAST);
        assertThat(game.isInProgress()).isFalse();

        startGameAgain(game);
        game.move(player, Direction.EAST);
        assertThat(game.isInProgress()).isFalse();

        final int levelsWon = 4;
        Mockito.verify(levelObserver, Mockito.times(levelsWon)).levelWon();
    }


    /**
     * Test for the transition from one level to another.
     */
    @Test
    void testWinLevel() {
        String[] maps = {"/sampleMap.txt", "/sampleMap.txt", "/sampleMap.txt", "/sampleMap.txt"};
        init(maps);

        Launcher launcher = getLauncher();
        launcher.launch();
        Game game = launcher.getGame();

        startGameAgain(game);
        game.move(player, Direction.EAST);

        assertThat(game.isInProgress()).isFalse();
        Mockito.verify(levelObserver, Mockito.times(1)).levelWon();
    }


    @Override
    @Test
    void testWinStart() {
        String[] maps = {"/sampleMap.txt", "/sampleMap.txt", "/sampleMap.txt", "/sampleMap.txt"};
        init(maps);

        Launcher launcher = getLauncher();
        launcher.launch();
        Game game = launcher.getGame();

        startGameAgain(game);
        game.move(player, Direction.EAST);
        assertThat(game.isInProgress()).isFalse();

        startGameAgain(game);
        game.move(player, Direction.EAST);
        assertThat(game.isInProgress()).isFalse();

        startGameAgain(game);
        game.move(player, Direction.EAST);
        assertThat(game.isInProgress()).isFalse();

        startGameAgain(game);
        game.move(player, Direction.EAST);
        assertThat(game.isInProgress()).isFalse();

        startGameAgain(game);
        assertThat(game.isInProgress()).isFalse();

        final int levelsWon = 4;
        Mockito.verify(levelObserver, Mockito.times(levelsWon)).levelWon();
    }
}
