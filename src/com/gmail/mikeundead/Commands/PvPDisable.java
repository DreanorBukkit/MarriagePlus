package com.gmail.mikeundead.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.mikeundead.MarriagePlus;

public class PvPDisable 
{
private MarriagePlus marriagePlus;
	
	public PvPDisable(MarriagePlus marriagePlus) 
	{
		this.marriagePlus = marriagePlus;
	}

	public void Handle(Player player)
	{
		String partner = this.marriagePlus.marriedPlayer.GetPartner(player.getName());
		
		this.marriagePlus.flatFiles.SetPvPState(player.getName(), false);
		this.marriagePlus.flatFiles.SetPvPState(partner, false);
		
		player.sendMessage(ChatColor.GREEN + "You have now disabled PvP with your Partner");
	}
}