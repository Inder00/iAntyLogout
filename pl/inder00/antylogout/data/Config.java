package pl.inder00.antylogout.data;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import net.md_5.bungee.api.ChatColor;
import pl.inder00.antylogout.AntyLogout;

public class Config {
	
	//=========================================================================
	private static Config inst;
	private AntyLogout antylogoutMainInstance;
	public FileConfiguration cfg = AntyLogout.getInst().getConfig();
	
	public boolean event$move;
	public int antylogout$time;
	public String cannotUseCommands;
	public String broadcastLogout;
	public List<String> allowedCommands = new ArrayList<String>();
	public boolean blockGameModeCREATIVE;
	public String hitGameModeCREATIVE;
	public List<String> antylogoutCommand = new ArrayList<String>();
	public String combatPlayer;
	public String safePlayer;
	public String cannot$logout$actionbar;
	public String can$logout$actionbar;
	public String cannot$build$here;
	public int max$y$build;
	public boolean event$build;
	public List<String> block$inventory = new ArrayList<String>();
	public boolean event$interact;
	public String cannot$open$inventory;
	
	public int max$x;
	public int min$x;
	public int max$z;
	public int min$z;

	public boolean auto$disable$fly;
	
	//=========================================================================
	
	//=========================================================================
	//Reload
	public void reload(){
		this.antylogoutMainInstance.reloadConfig();
		this.cfg = this.antylogoutMainInstance.getConfig();
		antylogoutCommand.clear();
		allowedCommands.clear();
		load();
	}
	//=========================================================================
	
	public Config(AntyLogout main) {
		inst = this;
		this.antylogoutMainInstance = main;
	}
	
	//=========================================================================
	//Load
	public void load(){
		this.antylogoutMainInstance.info("Ladowanie config.yml...");
		this.event$move = cfg.getBoolean("event-move");
		this.antylogout$time = cfg.getInt("antylogout-time");
		this.cannotUseCommands = ChatColor.translateAlternateColorCodes('&', cfg.getString("cannot-use-commands")).replace("Â", "");
		this.broadcastLogout = ChatColor.translateAlternateColorCodes('&', cfg.getString("broadcast-logout")).replace("Â", "");
		allowedCommands.clear();
		for(String s : cfg.getStringList("allowed-commands")) {
			allowedCommands.add(s.replace("/", ""));
		}
		this.blockGameModeCREATIVE = cfg.getBoolean("block-gamemode-CREATIVE");
		this.hitGameModeCREATIVE = ChatColor.translateAlternateColorCodes('&', cfg.getString("hit-gamemode-CREATIVE")).replace("Â", "");
		antylogoutCommand.clear();
		for(String s : cfg.getStringList("antylogout")) {
			antylogoutCommand.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		this.combatPlayer = ChatColor.translateAlternateColorCodes('&', cfg.getString("combat-player")).replace("Â", "");
		this.safePlayer = ChatColor.translateAlternateColorCodes('&', cfg.getString("safe-player")).replace("Â", "");
		this.cannot$logout$actionbar = ChatColor.translateAlternateColorCodes('&', cfg.getString("cannot-logout-actionbar")).replace("Â", "");
		this.can$logout$actionbar = ChatColor.translateAlternateColorCodes('&', cfg.getString("can-logout-actionbar")).replace("Â", "");
		this.cannot$build$here = ChatColor.translateAlternateColorCodes('&', cfg.getString("cannot-build-here")).replace("Â", "");
		this.event$build = cfg.getBoolean("event-build");
		this.max$y$build = cfg.getInt("max-y-build");
		this.block$inventory = cfg.getStringList("block-inventory");
		this.event$interact = cfg.getBoolean("event-interact");
		this.cannot$open$inventory = ChatColor.translateAlternateColorCodes('&', cfg.getString("cannot-open-inventory")).replace("Â", "");
		
		this.min$x = cfg.getInt("spawn.min.x");
		this.max$x = cfg.getInt("spawn.max.x");
		this.min$z = cfg.getInt("spawn.min.z");
		this.max$z = cfg.getInt("spawn.max.z");

		this.auto$disable$fly = cfg.getBoolean("auto-disable-fly");

		this.antylogoutMainInstance.info("Wladowano config.yml");
		for(String s : this.block$inventory) {
			if(Material.getMaterial(s.toUpperCase()) == null) {
				this.block$inventory.remove(s);
				this.antylogoutMainInstance.error("[InteractEvent] Invalid material: "+s);
				return;
			}
		}
		
	}
	//=========================================================================
	
	//=========================================================================
	//Instance
	public static Config getInst(){
		return inst;
	}
	//=========================================================================

}
