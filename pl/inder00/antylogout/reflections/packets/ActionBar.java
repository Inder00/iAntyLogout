package pl.inder00.antylogout.reflections.packets;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import pl.inder00.antylogout.AntyLogout;
import pl.inder00.antylogout.reflections.Reflection;

public class ActionBar {
	
	private String message;
	private Object packet;
	private String version;
	
	public ActionBar(String message) {
		this.version = AntyLogout.getInst().getVersion();
		this.message = ChatColor.translateAlternateColorCodes('&', message);
        try {
        	
        	if(version.equalsIgnoreCase("v1_8_R1")) {
        		Object chat = Reflection.getClass(this.version,"net.minecraft.server", "ChatSerializer").getMethod("a", String.class).invoke(null, "{text:\"" + this.message + "\"}");
            	this.packet = Reflection.getClass(this.version, "net.minecraft.server", "PacketPlayOutChat").getConstructor(Reflection.getClass(this.version, "net.minecraft.server", "IChatBaseComponent"), byte.class).newInstance(chat, (byte) 2);
        	
        	} else if(version.startsWith("v1_12_")) {
        		
        		Object chat = Reflection.getClass(this.version,"net.minecraft.server", "ChatComponentText").getConstructor(new Class<?>[]{String.class}).newInstance(this.message);
        		Class<?> chatMessageTypeClass = Reflection.getClass(this.version, "net.minecraft.server", "ChatMessageType");
        		Object[] chatMessageTypes = chatMessageTypeClass.getEnumConstants();
        		Object chatMessageType = null;
                for (Object obj : chatMessageTypes) {
                    if (obj.toString().equals("GAME_INFO")) {
                        chatMessageType = obj;
                    }
                }
        		this.packet = Reflection.getClass(this.version, "net.minecraft.server", "PacketPlayOutChat").getConstructor(new Class<?>[]{Reflection.getClass(this.version, "net.minecraft.server", "IChatBaseComponent"), chatMessageTypeClass}).newInstance(chat, chatMessageType);
        	
        	} else {
        		
        		Object chat = Reflection.getClass(this.version,"net.minecraft.server", "ChatComponentText").getConstructor(new Class<?>[]{String.class}).newInstance(this.message);
            	this.packet = Reflection.getClass(this.version, "net.minecraft.server", "PacketPlayOutChat").getConstructor(Reflection.getClass(this.version, "net.minecraft.server", "IChatBaseComponent"), byte.class).newInstance(chat, (byte) 2);
        	}

        } catch (Exception e) {
        	
			e.printStackTrace();
			
		}
	}
	
	public void send(Player player) {
		if(!player.isOnline() || player == null) {
			return;
		}
	    try {
	    	
	        Reflection.sendPacket(this.version, player, this.packet, "net.minecraft.server");
	   
	    } catch (Exception e) {
	    	
	        e.printStackTrace();
	        
	    }
		
	}
	
	public void sendToAll() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			this.send(p);
		}
	}

}
