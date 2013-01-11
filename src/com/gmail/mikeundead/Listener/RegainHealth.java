package com.gmail.mikeundead.Listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import com.gmail.mikeundead.MarriagePlus;

public class RegainHealth implements Listener
{
	private MarriagePlus marriagePlus;

	public RegainHealth(MarriagePlus marriagePlus) 
	{
		this.marriagePlus = marriagePlus;
	}

	@EventHandler
	public void onHeal(EntityRegainHealthEvent event) 
	{
		Player player = null;
		
		if (event.getEntity() instanceof Player)
		{
			player = (Player) event.getEntity();
		}
		
		if(player != null)
		{
			if(this.marriagePlus.flatFiles.GetHealthRegainStatus())
			{
				int amount = this.marriagePlus.flatFiles.GetHealthRegainAmount();
				
				String partner = this.marriagePlus.marriedPlayer.GetPartner(player.getName());
				
				Player otherPlayer = this.marriagePlus.getServer().getPlayer(partner);
				
				if(otherPlayer != null)
				{
					if(otherPlayer.isOnline())
					{
						if(this.getRadius(player, otherPlayer))
						{
							event.setAmount(amount);
						}
					}
				}
			}
		}
	}
	
	private boolean getRadius(Player player, Player otherPlayer) 
	{
		Location pl = player.getLocation();
		Location opl = otherPlayer.getLocation();
		
		if(pl.distance(opl) <= 2 && opl.distance(pl) <= 2)
		{
			return true;
		}
		
		return false;
	}
}
