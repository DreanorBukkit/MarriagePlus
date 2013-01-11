package com.gmail.mikeundead.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.gmail.mikeundead.MarriagePlus;

public class Home 
{
	private MarriagePlus marriagePlus;

	public Home(MarriagePlus marriagePlus) 
	{
		this.marriagePlus = marriagePlus;
	}

	public void Handle(Player player)
	{
		Location loc = this.marriagePlus.marriedPlayer.GetHome(player.getName());
		
		if(loc != null)
		{
			if(this.marriagePlus.flatFiles.GetEconomyStatus())
			{
				this.HandleEconomyHomeTp(player, loc);
			}
			else
			{
				this.HandleNonEconomyHomeTp(player, loc);
			}
		}
		else
		{
			player.sendMessage(ChatColor.GREEN + "You have no Home yet.");
		}
	}
	
	private void HandleNonEconomyHomeTp(Player player, Location loc)
	{
		player.teleport(loc);
		
		player.sendMessage(ChatColor.GREEN + "You have been teleported to your Home.");
	}
	
	private void HandleEconomyHomeTp(Player player, Location loc)
	{
		if(this.marriagePlus.flatFiles.GetEconomyStatus())
		{
			double money = this.marriagePlus.flatFiles.GetEconomyHomeTp();
			
			boolean result = this.marriagePlus.economy.HandleHomeTeleport(player, money);
			
			if(result)
			{
				this.HandleNonEconomyHomeTp(player, loc);
			}
			else
			{
				return;
			}
		}
	}

	public void HandleSetHome(Player player)
	{
		Location home = player.getLocation();
		
		if(this.marriagePlus.flatFiles.GetEconomyStatus())
		{
			this.HandleEconomySetHome(player, home);
		}
		else
		{
			this.HandleNonEconomySetHome(player, home);
		}
	}
	
	private void HandleEconomySetHome(Player player, Location home)
	{
		double money = this.marriagePlus.flatFiles.GetEconomySetHome();
		
		boolean result = this.marriagePlus.economy.HandleHomeTeleport(player, money);
		
		if(result)
		{
			this.HandleNonEconomySetHome(player, home);
		}
		else
		{
			return;
		}
	}
	
	private void HandleNonEconomySetHome(Player player, Location home)
	{
		String otherPlayer = this.marriagePlus.marriedPlayer.GetPartner(player.getName());
		
		this.marriagePlus.flatFiles.SaveMarriedHome(home, player.getName());
		this.marriagePlus.flatFiles.SaveMarriedHome(home, otherPlayer);
		
		player.sendMessage(ChatColor.GREEN + "Your home has been saved.");
	}
}
