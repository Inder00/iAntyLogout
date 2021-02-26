package pl.inder00.antylogout.tasks;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import pl.inder00.antylogout.AntyLogout;
import pl.inder00.antylogout.data.Config;
import pl.inder00.antylogout.objects.PVP;
import pl.inder00.antylogout.objects.utils.PVPUtil;
import pl.inder00.antylogout.reflections.packets.ActionBar;

public class Task extends BukkitRunnable {
	
	private AntyLogout main;
	private int taskId;
	private Config cfg;
	
	public Task(AntyLogout main) {
		this.main = main;
		this.taskId = this.getTaskId();
		this.cfg = Config.getInst();
	}

	public AntyLogout getMain() {
		return main;
	}

	public int getTaskId() {
		return taskId;
	}

	@Override
	public void run()
	{	
		ArrayList<PVP> pvps = PVPUtil.getPVPList();
		if(!pvps.isEmpty()) {
			for(PVP p : pvps) {
				if(p != null) {
					p.setLeftTime(p.getLeftTime()-1);
					if(p.getLeftTime() > 0) {
						new ActionBar(cfg.cannot$logout$actionbar.replace("{LEFTTIME}", ""+p.getLeftTime())).send(p.getPlayer());
					} else if(p.getLeftTime() <= 0) {
						p.delete();
						p.getPlayer().sendMessage(cfg.safePlayer);
						new ActionBar(cfg.can$logout$actionbar.replace("{LEFTTIME}", ""+p.getLeftTime())).send(p.getPlayer());
						return;
					}
				}
			}	
		}
		
	}

}
