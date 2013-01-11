package com.gmail.mikeundead.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.mikeundead.MarriagePlus;

public class PvPEnable 
{
	private MarriagePlus marriagePlus;
	
	public PvPEnable(MarriagePlus marriagePlus) 
	{
		this.marriagePlus = marriagePlus;
	}

	public void Handle(Player player)
	{
		String partner = this.marriagePlus.marriedPlayer.GetPartner(player.getName());
		
		this.marriagePlus.flatFiles.SetPvPState(player.getName(), true);
		this.marriagePlus.flatFiles.SetPvPState(partner, true);
		
		player.sendMessage(ChatColor.GREEN + "You have now enabled PvP with your Partner");
	}
}
