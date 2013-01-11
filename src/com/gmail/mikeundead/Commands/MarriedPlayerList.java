package com.gmail.mikeundead.Commands;

import java.util.Map;
import java.util.NavigableMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gmail.mikeundead.MarriagePlus;

public class MarriedPlayerList 
{
	private MarriagePlus marriagePlus;
	
	public MarriedPlayerList(MarriagePlus marriagePlus)
	{
		this.marriagePlus = marriagePlus;
	}

	public void Handle(Player player)
	{		
		NavigableMap<String, String> map = this.marriagePlus.flatFiles.LoadAllMarriedPlayers();

		if(map.size() > 0)
		{
			player.sendMessage(ChatColor.GREEN + "Married Player List");
			
			for(Map.Entry<String, String> item : map.entrySet())
			{
				player.sendMessage(ChatColor.GREEN + item.getKey() + " + " + item.getValue());
			}	
		}	
		else
		{
			player.sendMessage(ChatColor.RED + "There are no Married Players.");
		}
	}
}
