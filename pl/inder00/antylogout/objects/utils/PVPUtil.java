package pl.inder00.antylogout.objects.utils;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.entity.Player;

import pl.inder00.antylogout.data.Config;
import pl.inder00.antylogout.objects.PVP;

public class PVPUtil {
	
	private static ArrayList<PVP> pvps = new ArrayList<PVP>();

	public static ArrayList<PVP> getPVPList() {
		return pvps;
	}
	
	public static int size() {
		return pvps.size();
	}
	
	public static void addPVP(PVP pvp) {
		PVP pvpobject = getPVP(pvp.getUuid());
		if(pvpobject == null) {
			pvps.add(pvp);
			pvp.getPlayer().sendMessage(Config.getInst().combatPlayer);
		} else {
			pvpobject.setLeftTime(pvp.getLeftTime());
		}
	}
	public static void remPVP(PVP pvp) {
		if(getPVP(pvp.getUuid()) != null) {
			pvps.remove(pvp);
		}
	}
	
	public static PVP getPVP(UUID uuid) {
		for(PVP s : pvps) {
			if(s.getUuid().equals(uuid)) {
				return s;
			}
		}
		return null;
	}
	
	public static boolean inPVP(Player p) {
		PVP pvp = getPVP(p.getUniqueId());
		if(pvp != null) {
			if(pvp.getLeftTime() > 0) {
				return true;
			} else {
				remPVP(pvp);
				return false;
			}
		}
		return false;
	}
	
	

}
