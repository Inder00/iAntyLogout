package pl.inder00.antylogout.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import pl.inder00.antylogout.AntyLogout;
import pl.inder00.api.connection.HTTPContent;

public class Updater {
	
	final HTTPContent updater;
	
	public Updater() {
		updater = new HTTPContent(AntyLogout.checkUpdateURL);
		updater.connect();
	}
	
	public void check() {
		String newVersion = updater.read();
		PluginDescriptionFile pdf = AntyLogout.getInst().getPluginDescriptionFile();
		String oldVersion = pdf.getVersion();
		if(!oldVersion.equalsIgnoreCase(newVersion)) {
			Bukkit.getLogger().warning("["+pdf.getName()+" Updater] ===========================");
			Bukkit.getLogger().warning("["+pdf.getName()+" Updater]");
			Bukkit.getLogger().warning("["+pdf.getName()+" Updater] Uzywasz przestarzalej wersji pluginu");
			Bukkit.getLogger().warning("["+pdf.getName()+" Updater] Obecna: v"+pdf.getVersion());
			Bukkit.getLogger().warning("["+pdf.getName()+" Updater] Najnowsza: v"+newVersion);
			Bukkit.getLogger().warning("["+pdf.getName()+" Updater]");
			Bukkit.getLogger().warning("["+pdf.getName()+" Updater] ===========================");	
		}
	}
	
	public void check(Player player) {
		String newVersion = updater.read();
		PluginDescriptionFile pdf = AntyLogout.getInst().getPluginDescriptionFile();
		String oldVersion = pdf.getVersion();
		if(!oldVersion.equalsIgnoreCase(newVersion)) {
			player.sendMessage(ChatColor.GRAY+""+ChatColor.STRIKETHROUGH+"===========================");
			player.sendMessage("§8[§eiAntyLogout§8] §7Uzywasz przestarzalej wersji pluginu.");
			player.sendMessage("§8[§eiAntyLogout§8] §7Najnowsza wersja: §ev"+newVersion);
			player.sendMessage("§8[§eiAntyLogout§8] §7Pobierz na §ehttps://github.com/Inder00/iAntyLogout/releases");
			player.sendMessage(ChatColor.GRAY+""+ChatColor.STRIKETHROUGH+"===========================");
		} else {
			player.sendMessage("§8[§eiAntyLogout§8] §7Uzywasz najnowszej wersji pluginu");
		}
	}

}
