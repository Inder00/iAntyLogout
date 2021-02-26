package pl.inder00.antylogout.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import pl.inder00.antylogout.data.Config;
import pl.inder00.antylogout.objects.PVP;

public class EntityDamageByEntityListener implements Listener {
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onDamage(EntityDamageByEntityEvent e) {
		if(e.isCancelled()) {
			return;
		}
		boolean initPVP = false;
		Entity victimEntity = e.getEntity();
		Entity attackerEntity = e.getDamager();
		if(attackerEntity != null && victimEntity != null) {
			if(attackerEntity instanceof TNTPrimed) return;
			if(attackerEntity instanceof Projectile) {
				if(!(victimEntity instanceof Player)) return;
				if(((Projectile) attackerEntity).getShooter() instanceof Entity){
					attackerEntity = (Entity) ((Projectile) attackerEntity).getShooter();	
				}
			}
			if(attackerEntity instanceof Player) {
				if(!(victimEntity instanceof Player)) return;
				initPVP = true;
			}
		}
		if(initPVP == true) {
			if(!(victimEntity instanceof Player) || !(attackerEntity instanceof Player)) return;
			if(attackerEntity.equals(victimEntity)) return;
			Config config = Config.getInst();
			
			Player victim = (Player) victimEntity;
			Player attacker = (Player) attackerEntity;
			
			if(config.blockGameModeCREATIVE == true) {
				if(attacker.getGameMode().equals(GameMode.CREATIVE)) {
					attacker.sendMessage(config.hitGameModeCREATIVE);
					e.setCancelled(true);
					return;
				}
			}
			if(config.auto$disable$fly == true){
				attacker.setFlying(false);
				victim.setFlying(false);
				attacker.setAllowFlight(false);
				victim.setAllowFlight(false);
			}
			
			new PVP(victim.getUniqueId(), config.antylogout$time);
			new PVP(attacker.getUniqueId(), config.antylogout$time);
		}
		
	}

}
