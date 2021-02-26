package pl.inder00.antylogout.listeners;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.inder00.antylogout.data.Config;
import pl.inder00.antylogout.objects.PVP;
import pl.inder00.antylogout.objects.utils.PVPUtil;
import pl.inder00.antylogout.utils.DoubleFormatUtil;
import pl.inder00.antylogout.utils.TimeUtil;

public class PlayerQuitListener implements Listener {
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		PVP pvp = PVPUtil.getPVP(player.getUniqueId());
		if(pvp != null && pvp.getLeftTime() > 0) {
			double health = new BigDecimal((player.getHealth()/2)).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
			Location location = player.getLocation();
			player.setHealth(0.0D);
			Config config = Config.getInst();
			Bukkit.broadcastMessage(config.broadcastLogout
					.replace("{PLAYER}", player.getName())
					.replace("{HEALTH}", (health + "❤"))
					.replace("{X}", location.getBlockX()+"")
					.replace("{Y}", location.getBlockY()+"")
					.replace("{Z}", location.getBlockZ()+"")
					.replace("{LEFTTIME}", new DoubleFormatUtil(TimeUtil.outTime(TimeUtil.fromTime(pvp.getLeftTime()))).output())
					);
		}
	}

}
