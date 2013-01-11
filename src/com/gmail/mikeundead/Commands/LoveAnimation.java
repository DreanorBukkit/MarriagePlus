package com.gmail.mikeundead.Commands;

import java.util.WeakHashMap;

import org.bukkit.craftbukkit.entity.CraftOcelot;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;

import com.gmail.mikeundead.MarriagePlus;

public class LoveAnimation 
{
	private MarriagePlus marriagePlus;
	
	public LoveAnimation(MarriagePlus marriagePlus) 
	{
		this.marriagePlus = marriagePlus;
	}
	
	 WeakHashMap<CraftPlayer, Integer> players = new WeakHashMap<CraftPlayer, Integer>();
	 
	public void Handle(Player player)
	{
		this.Task();
		
        String partner = this.marriagePlus.marriedPlayer.GetPartner(player.getName());
        
        Player marriedPlayer = this.marriagePlus.getServer().getPlayer(partner);
        
		CraftPlayer loveTo = (CraftPlayer) marriedPlayer;
		CraftPlayer loveFrom = (CraftPlayer) player;
		
		synchronized (players)
        {
            if (players.containsKey(marriedPlayer))
            {
                players.remove(marriedPlayer);
                return;
            }
                        
            if(marriedPlayer.isOnline())
            {
                players.put(loveTo, 20);
                players.put(loveFrom, 20);
            }
        }
	}
	
	
	private void Task()
	{
		 this.marriagePlus.getServer().getScheduler().scheduleAsyncRepeatingTask(this.marriagePlus, new Runnable()
		 {
			 public void run()
			 {
				 synchronized (players)
				 {
					 try
					 {
						 for (CraftPlayer player : players.keySet())
						 {
							 if (!player.isOnline())
	                         {
								 players.remove(player);
								 continue;
	                         }
							 
							 int current = players.get(player);
							 
							 CraftOcelot ocelot = (CraftOcelot) player.getWorld().spawn(player.getLocation(), Ocelot.class);
							 
							 player.getHandle().world.broadcastEntityEffect(ocelot.getHandle(), (byte) 7);
							 
							 ocelot.remove();
							 
							 if (current > 1)
							 {
								 players.put(player, current - 1);
							 }
							 else
							 {
								 players.remove(player);
							 }
	                    } 
					 }
					 catch(Exception e)
					 {
						 return;
					 }
                }
            }
        }, 20, 20);
	}
	
	
	public CraftPlayer getPlayer(String player)
    {
        try
        {
            return (CraftPlayer) this.marriagePlus.getServer().getPlayer(player);
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
