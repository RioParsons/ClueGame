package game;

import player.Player;

/* Observer Pattern */
public interface GameObserver {
    public void update(Player player, String message);
}
