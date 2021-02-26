package pl.inder00.antylogout;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import bstats.bukkit.Metrics;
import pl.inder00.antylogout.commands.AntyLogoutCommand;
import pl.inder00.antylogout.data.Config;
import pl.inder00.antylogout.listeners.BlockPlaceListener;
import pl.inder00.antylogout.listeners.EntityDamageByEntityListener;
import pl.inder00.antylogout.listeners.PlayerCommandPreprocessListener;
import pl.inder00.antylogout.listeners.PlayerDeathListener;
import pl.inder00.antylogout.listeners.PlayerInteractListener;
import pl.inder00.antylogout.listeners.PlayerJoinListener;
import pl.inder00.antylogout.listeners.PlayerMoveListener;
import pl.inder00.antylogout.listeners.PlayerQuitListener;
import pl.inder00.antylogout.reflections.Reflection;
import pl.inder00.antylogout.tasks.Task;
import pl.inder00.antylogout.utils.Updater;

public class AntyLogout extends JavaPlugin {
	
	private static AntyLogout inst;
	private PluginDescriptionFile pdf;
	private String version;
	private Task task;
	public static String checkUpdateURL = "https://raw.githubusercontent.com/Inder00/iAntyLogout/master/version.info";

	private static File mainDir;
	private static File cfgFile = new File(mainDir, "config.yml");

	@Override
	public void onEnable() {
		inst = this;
		
		Metrics metrics = new Metrics(this);
		
		mainDir = this.getDataFolder();
		if(!mainDir.exists()) mainDir.mkdir();
		if(!cfgFile.exists()) this.saveDefaultConfig();
		
		this.version = Reflection.getVersion();
		this.pdf = this.getDescription();
		this.version();
		
		Config config = new Config(this);
		config.load();
		
		new Updater().check();
		new AntyLogoutCommand(this);
		
		this.task = new Task(this);
		this.task.runTaskTimerAsynchronously(this, 0, 4);
		
		if(config.event$build == true) {
			Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), this);
		}
		if(config.event$move == true) {
			Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
		}
		if(config.event$interact) {
			Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);	
		}
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerCommandPreprocessListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
	}

	public PluginDescriptionFile getPluginDescriptionFile() {
		return pdf;
	}
	
	public static AntyLogout getInst() {
		return inst;
	}
	public void info(String s) {
		Bukkit.getLogger().info("["+pdf.getName()+"] "+s);
	}
	public void error(String s) {
		Bukkit.getLogger().severe("["+pdf.getName()+"] "+s);
	}

	public String getVersion() {
		return version;
	}

	public Task getTask() {
		return task;
	}

	public static File getMainDIR() {
		return mainDir;
	}

	public static File getConfigFile() {
		return cfgFile;
	}
	
	private void version() {
		if(version.contains("1_8") || version.contains("1_9") || version.contains("1_10") || version.contains("1_11") || version.contains("1_12") || version.contains("1_13")) {
			this.info("Uzywasz wersji serwera: "+version.replace("_R1", "").replace("_R2", "").replace("_R3", "").replace("_R4", "").replace(".", "").replace("_", ".").replace("v", ""));
		} else {
			this.error("Uzywasz wersji serwera, ktora nie wspolpracuje z tym pluginem.");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
	}
	
}
