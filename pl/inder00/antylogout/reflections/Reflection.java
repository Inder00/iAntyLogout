package pl.inder00.antylogout.reflections;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Reflection {
	
	public static String getVersion() {
		return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
	}
	
    public static Class<?> getClass(String version, String prefix, String nmsClassString) {
        String name = prefix + "." + version + nmsClassString;
        Class<?> nmsClass;
		try {
			nmsClass = Class.forName(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
        return nmsClass;
    }
    public static void sendPacket(String version, Player p, Object packet, String prefix) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, ClassNotFoundException {
        Object nmsPlayer = p.getClass().getMethod("getHandle").invoke(p);
        Object plrConnection = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
        plrConnection.getClass().getMethod("sendPacket", getClass(version, prefix, "Packet")).invoke(plrConnection, packet);
    }
}
