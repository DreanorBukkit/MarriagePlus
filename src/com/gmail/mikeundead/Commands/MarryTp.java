package com.gmail.mikeundead.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.mikeundead.MarriagePlus;

public class MarryTp 
{
	private MarriagePlus marriagePlus;
	
	public MarryTp(MarriagePlus marriagePlus)
	{
		this.marriagePlus = marriagePlus;
	}

	public void Handle(Player player)
	{		
		String partner = this.marriagePlus.marriedPlayer.GetPartner(player.getName());
		
		Player otherPlayer = this.marriagePlus.getServer().getPlayer(partner);
				
		if(otherPlayer != null)
		{
			if(player.canSee(otherPlayer))
			{
				if(this.marriagePlus.flatFiles.GetEconomyStatus())
				{
					this.HandleEconomy(player, otherPlayer);
				}
				else
				{
					this.HandleNonEconomy(player, otherPlayer);
				}
			}
			else
			{
				player.sendMessage(ChatColor.RED + "You cant use teleport if your Partner is Vanished.");
			}
		}
		else
		{
			player.sendMessage(ChatColor.RED + "Your Partner is Offline.");
		}
	}

	private void HandleEconomy(Player player, Player otherPlayer) 
	{
		double money = this.marriagePlus.flatFiles.GetEconomyTp();
		
		boolean result = this.marriagePlus.economy.HandleTeleport(player, money);
		
		if(result)
		{
			this.HandleNonEconomy(player, otherPlayer);
		}
		else
		{
			return;
		}
	}

	private void HandleNonEconomy(Player player, Player otherPlayer) 
	{
		player.teleport(otherPlayer);
		
		player.sendMessage(ChatColor.GREEN + "Teleporting to Partner...");
		otherPlayer.sendMessage(ChatColor.GREEN + "Your Partner is teleporting to you...");		
	}
}
