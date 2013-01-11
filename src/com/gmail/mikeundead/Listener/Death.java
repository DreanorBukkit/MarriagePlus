package com.gmail.mikeundead.Listener;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.gmail.mikeundead.MarriagePlus;

public class Death implements Listener 
{
	private MarriagePlus marriagePlus;

	public Death(MarriagePlus marriagePlus) 
	{
		this.marriagePlus = marriagePlus;
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event)
    {
		if(this.marriagePlus.flatFiles.GetBonusXPEnable())
		{
			if(event.getEntityType() != EntityType.PLAYER)
			{
				Player killer = event.getEntity().getKiller();
				
				String partner = this.marriagePlus.marriedPlayer.GetPartner(killer.getName());
				
				Player otherPlayer = this.marriagePlus.getServer().getPlayer(partner);
				
				if(otherPlayer != null && otherPlayer.isOnline())
				{
					if(this.getRadius(killer, otherPlayer))
					{
						int amount = this.marriagePlus.flatFiles.GetBonusXPAmount();
						int droppedXp = event.getDroppedExp();
						
						int xp = (droppedXp / 2) * amount;
						
						otherPlayer.giveExp(xp);
						killer.giveExp(xp);
						
						event.setDroppedExp(0);
					}
				}
			}
		}
    }
	
	private boolean getRadius(Player player, Player otherPlayer) 
	{
		Location pl = player.getLocation();
		Location opl = otherPlayer.getLocation();
		
		if(pl.distance(opl) <= 10 && opl.distance(pl) <= 10)
		{
			return true;
		}
		
		return false;
	}
}
