package com.gmail.mikeundead;

import org.bukkit.Location;

public class MarriedPlayer 
{
	private MarriagePlus marriagePlus;
	
	public MarriedPlayer(MarriagePlus marriagePlus) 
	{
		this.marriagePlus = marriagePlus;
	}

	public String GetPartner(String Playername)
	{
		return this.marriagePlus.flatFiles.LoadMarriedPlayer(Playername);
	}
	
	public boolean IsPriester(String Playername)
	{
		return this.marriagePlus.flatFiles.IsPriester(Playername);
	}
	
	public boolean HasPvPEnabled(String Playername)
	{
		return this.marriagePlus.flatFiles.GetPvPState(Playername);
	}
	
	public Location GetHome(String Playername)
	{
		return this.marriagePlus.flatFiles.LoadMarriedHome(Playername);
	}
}
