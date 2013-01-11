package com.gmail.mikeundead.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.mikeundead.MarriagePlus;

public class Help 
{
	private MarriagePlus marriagePlus;
	
	public Help(MarriagePlus marriagePlus) 
	{
		this.marriagePlus = marriagePlus;
	}

	public void ShowAvailableCmds(Player player)
	{
		player.sendMessage(ChatColor.YELLOW + "MarriagePlus - commands");
		player.sendMessage(ChatColor.AQUA + "/marry list" + ChatColor.WHITE + " - Lists all Married Players.");
		
		if(this.marriagePlus.marriedPlayer.IsPriester(player.getName()))
		{
			player.sendMessage(ChatColor.AQUA + "/marry <Playername> <Playername>" + ChatColor.WHITE + " - Marry two Persons in a range of 25 blocks.");
			player.sendMessage(ChatColor.AQUA + "/marry divorce <Playername>" + ChatColor.WHITE + " - Divorces two Persons in a range of 25 blocks.");
		}
		if(player.isOp())
		{
			player.sendMessage(ChatColor.AQUA + "/marry priest <Playername>" + ChatColor.WHITE + " - Sets a priest.");
			player.sendMessage(ChatColor.AQUA + "/marry <Playername> <Playername>" + ChatColor.WHITE + " - Marry two Persons in a range of 25 blocks.");
			player.sendMessage(ChatColor.AQUA + "/marry divorce <Playername>" + ChatColor.WHITE + " - Divorces two Persons in a range of 25 blocks.");
		}
		
		player.sendMessage(ChatColor.AQUA + "/marry tp" + ChatColor.WHITE + " - Teleport to Married Player.");
		player.sendMessage(ChatColor.AQUA + "/marry love" + ChatColor.WHITE + " - Heart animation around you and the Partner.");
		player.sendMessage(ChatColor.AQUA + "/marry pvpon" + ChatColor.WHITE + " - Enables PvP with Partner.");
		player.sendMessage(ChatColor.AQUA + "/marry sethome" + ChatColor.WHITE + " - Sets your Home.");	
		player.sendMessage(ChatColor.AQUA + "/marry home" + ChatColor.WHITE + " - Teleports to your Home.");	
		player.sendMessage(ChatColor.AQUA + "/marry pvpoff" + ChatColor.WHITE + " - Disables PvP with Partner.");	
	}
}