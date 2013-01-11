package com.gmail.mikeundead.Listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.gmail.mikeundead.MarriagePlus;

public class Damage implements Listener 
{
	private MarriagePlus marriagePlus;
	
	public Damage(MarriagePlus marriagePlus)
	{
		this.marriagePlus = marriagePlus;
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent event)
    {
        Entity defender = event.getEntity();
        Entity attacker = event.getDamager();
        
		Player player = null;
		Player otherPlayer = null;
		
        if(attacker instanceof Player)
        {
        	player = (Player)event.getDamager();
        }
        
        if(defender instanceof Player)
        {
        	otherPlayer = (Player) event.getEntity();
        }
		
		if(player != null && otherPlayer != null)
		{
			String married1 = this.marriagePlus.marriedPlayer.GetPartner(player.getName());
			String married2 = this.marriagePlus.marriedPlayer.GetPartner(otherPlayer.getName());
			
			if(married1 != null && married2 != null)
			{
				if(married1.equalsIgnoreCase(otherPlayer.getName()) && married2.equalsIgnoreCase(player.getName()))
				{
					if(!this.marriagePlus.marriedPlayer.HasPvPEnabled(player.getName()))
					{
						player.sendMessage(ChatColor.RED + "You can't hurt your Partner if you have PvP disabled.");
						event.setCancelled(true);
					}
				}
			}
		}
    }
}
