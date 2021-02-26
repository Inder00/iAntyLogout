package pl.inder00.antylogout.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import pl.inder00.antylogout.data.Config;
import pl.inder00.antylogout.objects.utils.PVPUtil;

public class PlayerCommandPreprocessListener implements Listener {
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onCommand(PlayerCommandPreprocessEvent e){
		if(e.isCancelled()){
			return;
		}
		Player player = e.getPlayer();
		if(PVPUtil.inPVP(player)) {
			boolean allowed = false;
			Config config = Config.getInst();
			for(String s : config.allowedCommands) {
				if(e.getMessage().toLowerCase().startsWith("/"+s.toLowerCase())) {
					allowed = true;
					break;
				}
			}
			if(allowed == false) {
				player.sendMessage(config.cannotUseCommands);
				e.setCancelled(true);
				return;
			}
		}
	}

}
