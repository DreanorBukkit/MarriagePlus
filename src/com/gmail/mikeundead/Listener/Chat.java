package com.gmail.mikeundead.Listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.gmail.mikeundead.MarriagePlus;

public class Chat implements Listener 
{
	private MarriagePlus marriagePlus;

	public Chat(MarriagePlus marriagePlus) 
	{
		this.marriagePlus = marriagePlus;
	}
	
	@EventHandler
	public void PlayerLoginEvent(PlayerJoinEvent event) 
	{
		String otherPlayer = this.marriagePlus.marriedPlayer.GetPartner(event.getPlayer().getName());
		
		if(otherPlayer != null)
		{
			Player oPlayer = this.marriagePlus.getServer().getPlayer(otherPlayer);
			
			if(oPlayer != null)
			{
				event.getPlayer().sendMessage(ChatColor.GREEN + "Your Partner is online.");
				oPlayer.sendMessage(ChatColor.GREEN + "Your Partner is now online.");
			}
			else
			{
				event.getPlayer().sendMessage(ChatColor.GREEN + "Your Partner is offline.");
			}
		}
	}

	@EventHandler
	public void PlayerLeaveEvent(PlayerQuitEvent event)
	{
		String otherPlayer = this.marriagePlus.marriedPlayer.GetPartner(event.getPlayer().getName());
		
		if(otherPlayer != null)
		{
			Player oPlayer = this.marriagePlus.getServer().getPlayer(otherPlayer);
			
			if(oPlayer != null)
			{
				oPlayer.sendMessage(ChatColor.GREEN + "Your Partner is now offline.");
			}
		}
	}
}
