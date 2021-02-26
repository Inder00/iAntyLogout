package pl.inder00.antylogout.objects;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import pl.inder00.antylogout.objects.utils.PVPUtil;
import pl.inder00.antylogout.utils.TimeUtil;

public class PVP {
	
	private UUID uuid;
	private long leftTime;
	private int id;
	private Player player;
	private List<Location> blocks;
	
	public PVP(UUID uuid, int time) {
		this.uuid = uuid;
		this.leftTime = TimeUtil.addTime(time);
		this.id = PVPUtil.size();
		this.player = Bukkit.getPlayer(this.uuid);
		this.blocks = Lists.newArrayList();
		PVPUtil.addPVP(this);
	}

	public List<Location> getBlocks() {
		return blocks;
	}
	public void addBlock(Location l){
		this.blocks.add(l);
	}

	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public long getLeftTime() {
		return leftTime;
	}
	public void setLeftTime(long leftTime) {
		this.leftTime = leftTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	public void delete() {
		PVPUtil.remPVP(this);
	}

}
