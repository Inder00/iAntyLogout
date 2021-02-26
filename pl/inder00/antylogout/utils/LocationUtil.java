package pl.inder00.antylogout.utils;

import java.util.Arrays;

import org.bukkit.Location;

public class LocationUtil {
	
	public static boolean Location(int minX, int maxX, int minZ, int maxZ, Location l){
		if(l.getBlockX() >= maxX || l.getBlockX() <= minX) return false;

		if(l.getBlockZ() >= maxZ || l.getBlockZ() <= minZ)return false;
		 
		return true;
	}

}
