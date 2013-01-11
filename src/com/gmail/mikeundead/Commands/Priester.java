package com.gmail.mikeundead.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.gmail.mikeundead.MarriagePlus;

public class Priester 
{
	private MarriagePlus marriagePlus;
	
	public Priester(MarriagePlus marriagePlus) 
	{
		this.marriagePlus = marriagePlus;
	}

	public boolean Handle(Player priester, String[] args)
	{
		if(priester.isOp() || this.marriagePlus.marriedPlayer.IsPriester(priester.getName()) || this.marriagePlus.perms.has(priester, "marry.priest"))
		{
			this.HandlePriestMarry(priester, args);
		}
		else
		{
			priester.sendMessage(ChatColor.RED + "You are not a Priest.");
		}
		return true;
	}

	private void HandlePriestMarry(Player priester, String[] args) 
	{
		Player player = Bukkit.getServer().getPlayer(args[0]);
		Player otherPlayer = Bukkit.getServer().getPlayer(args[1]);
		
		if(player.getName().equalsIgnoreCase(otherPlayer.getName()))
		{
			priester.sendMessage(ChatColor.RED + "You can't marry " + player.getName() + " with himself.");
		}
		else
		{
			if(player.isOnline() && otherPlayer.isOnline())
			{
				if(this.getRadius(player, otherPlayer, priester))
				{
					String a1 = this.marriagePlus.marriedPlayer.GetPartner(player.getName());
					String a2 = this.marriagePlus.marriedPlayer.GetPartner(otherPlayer.getName());
					
					if(a1.isEmpty() || a2.isEmpty() && a1 == null || a2 == null)
					{
						priester.sendMessage(ChatColor.RED + "You can't marry people that are already married.");
						return;
					}
					
					if(this.marriagePlus.flatFiles.GetEconomyStatus())
					{
						this.HandleEconomyMarry(priester, player, otherPlayer);
					}
					else
					{
						this.HandleNonEconomyMarry(priester, player, otherPlayer);
					}
				}
				else
				{
					priester.sendMessage(ChatColor.RED + "The players are not in range.");
				}
			}
			else
			{
				this.HandlePlayerStatus(priester, player, otherPlayer);
			}
		}
	}

	private void HandleEconomyMarry(Player priester, Player player, Player otherPlayer) 
	{
		double money = this.marriagePlus.flatFiles.GetEconomyMarry();
		
		boolean result = this.marriagePlus.economy.HandleMarry(player, otherPlayer, money);
		
		if(result)
		{
			this.HandleNonEconomyMarry(priester, player, otherPlayer);
		}
		else
		{
			return;
		}
	}

	private void HandleNonEconomyMarry(Player priester, Player player, Player otherPlayer) 
	{
		this.marriagePlus.flatFiles.SaveMarriedPlayer(player.getName(), otherPlayer.getName(), priester.getName());
		this.marriagePlus.flatFiles.SaveMarriedPlayer(otherPlayer.getName(), player.getName(), priester.getName());
		
		priester.sendMessage(ChatColor.GREEN + "You have married " + player.getName() + " and " + otherPlayer.getName());
		player.sendMessage(ChatColor.GREEN + priester.getName() + " has married you with " + otherPlayer.getName());
		otherPlayer.sendMessage(ChatColor.GREEN + priester.getName() + " has married you with " + player.getName());
		
		if(this.marriagePlus.flatFiles.GetAnnouncementStatus())
		{
			this.marriagePlus.getServer().broadcastMessage(ChatColor.GREEN + priester.getName() + " has married " + player.getName() + " and " + otherPlayer.getName() + ".");
		}
	}

	private boolean getRadius(Player player, Player otherPlayer, Player priester) 
	{
		Location ploc = priester.getLocation();
		Location plloc = player.getLocation();
		Location oplloc = otherPlayer.getLocation();
		
		if(ploc.distance(plloc) <= 25 && ploc.distance(oplloc) <= 25)
		{
			return true;
		}
		
		return false;
	}

	public void setPriester(String[] args, Player sender) 
	{
		Player player = this.marriagePlus.getServer().getPlayer(args[1]);

		if(player.isOnline())
		{
			this.marriagePlus.flatFiles.SavePriester(player.getName(), sender);
		}
		else
		{
			player.sendMessage(ChatColor.RED + player.getName() + " is not Online.");
		}
	}
	
	public void Divorce(Player priester, String[] args)
	{
		Player player = Bukkit.getServer().getPlayer(args[1]);

		String otP = this.marriagePlus.marriedPlayer.GetPartner(player.getName());
		Player otherPlayer = Bukkit.getServer().getPlayer(otP);
		
		String p1 = this.marriagePlus.marriedPlayer.GetPartner(otherPlayer.getName());
		String p2 = this.marriagePlus.marriedPlayer.GetPartner(player.getName());
		
		if(p1.equalsIgnoreCase(player.getName()) && p2.equalsIgnoreCase(otherPlayer.getName()))
		{
			if(player.isOnline() && otherPlayer.isOnline())
			{
				if(this.getRadius(player, otherPlayer, priester))
				{ 
					if(this.marriagePlus.flatFiles.GetEconomyStatus())
					{
						this.HandleEconomyDivorce(priester, player, otherPlayer);
					}
					else
					{
						this.HandleNonEconomyDivorce(priester, player, otherPlayer);
					}
				}
				else
				{
					priester.sendMessage(ChatColor.RED + "The players are not in range.");
				}
			}
			else
			{
				this.HandlePlayerStatus(priester, player, otherPlayer);
			}
		}	
		else
		{
			priester.sendMessage(ChatColor.RED + player.getName() + " is not married with " + otherPlayer.getName());
		}
	}

	private void HandleEconomyDivorce(Player priester, Player player, Player otherPlayer) 
	{
		double money = this.marriagePlus.flatFiles.GetEconomyDivorce();
		
		boolean result = this.marriagePlus.economy.HandleDivorce(player, otherPlayer, money);
		
		if(result)
		{
			this.HandleNonEconomyDivorce(priester, player, otherPlayer);
		}
		else
		{
			return;
		}
	}

	private void HandleNonEconomyDivorce(Player priester, Player player, Player otherPlayer) 
	{
		this.marriagePlus.flatFiles.SaveMarriedPlayerDivorce(player.getName(), otherPlayer.getName(), priester.getName());
		this.marriagePlus.flatFiles.SaveMarriedPlayerDivorce(otherPlayer.getName(), player.getName(), priester.getName());
		
		priester.sendMessage(ChatColor.GREEN + "You have divorced " + player.getName() + " and " + otherPlayer.getName());
		player.sendMessage(ChatColor.GREEN + priester.getName() + " has divorced your marriage with " + otherPlayer.getName());
		otherPlayer.sendMessage(ChatColor.GREEN + priester.getName() + " has divorced your marriage with " + player.getName());
	}

	private void HandlePlayerStatus(Player priester, Player player,	Player otherPlayer) 
	{
		if(player.isOnline())
		{
			priester.sendMessage(ChatColor.RED + otherPlayer.getName() + " is not Online.");
		}
		if(otherPlayer.isOnline())
		{
			priester.sendMessage(ChatColor.RED + player.getName() + " is not Online.");
		}
		else
		{
			priester.sendMessage(ChatColor.RED + player.getName() + " and " + otherPlayer.getName() + " are not Online.");
		}
	}
}