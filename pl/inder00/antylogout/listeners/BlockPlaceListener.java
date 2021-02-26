package pl.inder00.antylogout.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import pl.inder00.antylogout.data.Config;
import pl.inder00.antylogout.objects.utils.PVPUtil;

public class BlockPlaceListener implements Listener {
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onPlace(BlockPlaceEvent e) {
		if(e.isCancelled()) return;
		Player player = e.getPlayer();
		Block block = e.getBlock();
		
		Config config = Config.getInst();
		
		if(PVPUtil.inPVP(player)) {
			if(block.getY() <= config.max$y$build) {
				player.sendMessage(config.cannot$build$here);
				e.setBuild(false);
				e.setCancelled(true);
				return;	
			}
		}
	}

}
