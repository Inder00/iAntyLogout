package pl.inder00.antylogout.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import pl.inder00.antylogout.utils.Updater;

public class PlayerJoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(p.isOp() || p.hasPermission("antylogout.updates")) {
			new Updater().check(p);
		}
	}

}
