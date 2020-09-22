package dev.lielamar.talecraft.constants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BedStatus {

	private long currentWorldTime;
	private long timeStamp;
	private int accept;
	private int deny;
	
	private List<UUID> votes;
	
	public BedStatus(long currentWorldTime, long timeStamp, int accept, int deny) {
		this.currentWorldTime = currentWorldTime;
		this.timeStamp = timeStamp;
		this.accept = accept;
		this.deny = deny;
		
		votes = new ArrayList<UUID>();
	}

	public long getCurrentWorldTime() {
		return currentWorldTime;
	}

	public void setCurrentWorldTime(long currentWorldTime) {
		this.currentWorldTime = currentWorldTime;
	}
	
	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getAccept() {
		return accept;
	}

	public void setAccept(int accept) {
		this.accept = accept;
	}

	public int getDeny() {
		return deny;
	}

	public void setDeny(int deny) {
		this.deny = deny;
	}
	
	public List<UUID> getVotes() {
		return votes;
	}
	
	public void addList(UUID u) {
		votes.add(u);
	}
}
