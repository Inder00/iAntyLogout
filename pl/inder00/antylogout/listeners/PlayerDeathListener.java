package pl.inder00.antylogout.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import pl.inder00.antylogout.objects.PVP;
import pl.inder00.antylogout.objects.utils.PVPUtil;

public class PlayerDeathListener implements Listener {
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void onDeath(PlayerDeathEvent e) {
		Player player = e.getEntity();
		PVP pvp = PVPUtil.getPVP(player.getUniqueId());
		if(pvp != null) {
			pvp.delete();
		}
	}

}
