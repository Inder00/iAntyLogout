package pl.inder00.antylogout.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.antylogout.AntyLogout;
import pl.inder00.antylogout.data.Config;
import pl.inder00.antylogout.objects.PVP;
import pl.inder00.antylogout.objects.utils.PVPUtil;
import pl.inder00.antylogout.utils.TimeUtil;

public class AntyLogoutCommand implements CommandExecutor {
	
	private AntyLogout main;
	
	public AntyLogoutCommand(AntyLogout plugin) {
		this.main = plugin;
		plugin.getCommand("antylogout").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Ta komenda jest tylko dla graczy!");
			return false;
		}
		Config config = Config.getInst();
		Player player = (Player) sender;
		
		if(args.length > 0 && (player.hasPermission("antylogout.reload") || player.isOp())) {
			if(args[0].equalsIgnoreCase("reload")) {
				config.reload();
				player.sendMessage(ChatColor.GREEN+"Przeladowano config.yml");
				return false;
			} else {
				player.sendMessage(ChatColor.RED+"Poprawne uzycie /antylogout reload");
				return false;
			}
		}
		
		PVP pvp = PVPUtil.getPVP(player.getUniqueId());
		for(String s : config.antylogoutCommand) {
			player.sendMessage(s
					.replace("{CANLOGOUT}", pvp != null ? "NIE" : "TAK")
					.replace("{LEFTTIME}", pvp != null ? ""+TimeUtil.outTime(TimeUtil.fromTime(pvp.getLeftTime())) : "0.00")
					.replace("{PLAYER}", player.getName())
					);
		}
		return false;
	}

	@SuppressWarnings("unused")
	private AntyLogout getMain() {
		return main;
	}

}
