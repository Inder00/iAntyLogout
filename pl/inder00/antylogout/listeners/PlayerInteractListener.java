package pl.inder00.antylogout.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.inder00.antylogout.data.Config;
import pl.inder00.antylogout.objects.utils.PVPUtil;

public class PlayerInteractListener implements Listener {
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void onClick(PlayerInteractEvent e) {
		if(e.isCancelled()) {
			return;
		}
		Action action = e.getAction();
		Block clicked = e.getClickedBlock();
		Material material = clicked.getType();
		Player player = e.getPlayer();
		
		if(action.equals(Action.RIGHT_CLICK_BLOCK)) {
			if(PVPUtil.inPVP(player)) {
				boolean blocked = false;
				Config config = Config.getInst();
				for(String s : config.block$inventory) {
					if(s.toLowerCase().equalsIgnoreCase(material.name().toLowerCase())) {
						blocked = true;
						break;
					}
				}
				if(blocked == true) {
					player.sendMessage(config.cannot$open$inventory.replace("{TYPE}", material.name().toUpperCase()));
					e.setCancelled(true);
					return;
				}
			}	
		}
	}

}
