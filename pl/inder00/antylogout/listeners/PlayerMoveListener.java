package pl.inder00.antylogout.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.util.Vector;
import pl.inder00.antylogout.data.Config;
import pl.inder00.antylogout.objects.PVP;
import pl.inder00.antylogout.objects.utils.PVPUtil;
import pl.inder00.antylogout.utils.LocationUtil;

import java.util.ArrayList;
import java.util.List;

public class PlayerMoveListener implements Listener {
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void onMove(PlayerMoveEvent e) {
		if(e.isCancelled()) {
			return;
		}
		Player player = e.getPlayer();
		PVP pvp = PVPUtil.getPVP(player.getUniqueId());
		if(pvp != null) {
			Location to = e.getTo();
			Location from = e.getFrom();
			Config config = Config.getInst();
			if(from.getBlockX() != to.getBlockX() || from.getBlockY() != to.getBlockY() || from.getBlockZ() != to.getBlockZ()) {

				if(LocationUtil.Location(config.min$x, config.max$x, config.min$z, config.max$z, to)){
					Vector playerCenterVector = Bukkit.getWorld("world").getSpawnLocation().toVector();
					Vector playerToThrowVector = player.getEyeLocation().toVector();

					double x = playerToThrowVector.getX() - playerCenterVector.getX();
					double z = playerToThrowVector.getZ() - playerCenterVector.getZ();

					Vector throwVector = new Vector(x, 1, z).normalize().multiply(2);
					throwVector.multiply(0.41);
					throwVector.setY(0);

					try {
						player.setVelocity(throwVector);
					} catch(Exception error){

					}
					e.setCancelled(true);
					return;
				}
			}
		}
	}

}
