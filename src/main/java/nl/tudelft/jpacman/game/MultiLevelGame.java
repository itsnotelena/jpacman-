package nl.tudelft.jpacman.game;

import com.google.common.collect.ImmutableList;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;

import java.util.List;

public class MultiLevelGame extends Game {

    /**
     * The player of this game.
     */
    private final Player player;

    /**
     * The level of this game.
     */
    private final Level[] levels;

    private int level;

    /**
     * Create a new single player game for the provided level and player.
     *
     * @param player
     *            The player.
     * @param levels
     *            The level.
     * @param pointCalculator
     *            The way to calculate points upon collisions.
     */
    protected MultiLevelGame(Player player, Level[] levels, PointCalculator pointCalculator) {
        super(pointCalculator);

        assert player != null;
        assert levels != null;

        this.player = player;
        this.levels = levels;
        this.level = 0;

        this.levels[level].registerPlayer(player);
    }

    @Override
    public List<Player> getPlayers() {
        return ImmutableList.of(player);
    }

    @Override
    public Level getLevel() {
        return levels[level];
    }

    @Override
    public void levelWon() {
        if (level < 3) {
            level++;
            levels[level].registerPlayer(player);
        } else {
            stop();
        }
    }
}
