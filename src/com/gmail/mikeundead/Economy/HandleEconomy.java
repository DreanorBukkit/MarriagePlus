package com.gmail.mikeundead.Economy;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.milkbowl.vault.economy.EconomyResponse;

import com.gmail.mikeundead.MarriagePlus;

public class HandleEconomy 
{
	private MarriagePlus marriagePlus;
	
	public HandleEconomy(MarriagePlus marriagePlus)
	{
		this.marriagePlus = marriagePlus;
	}

	public boolean HandleHomeTeleport(Player player, double money)
	{
		EconomyResponse response = this.marriagePlus.econ.withdrawPlayer(player.getName(), money);
		
		if(response.transactionSuccess()) 
		{
			player.sendMessage(String.format(ChatColor.GREEN + "You paid %s for your home teleport. (%s left)", this.marriagePlus.econ.format(response.amount), this.marriagePlus.econ.getBalance(player.getName())));
			return true;
		} 
		else 
		{
			player.sendMessage(String.format(ChatColor.RED + "You dont have enough money"));
			return false;
		}
	}
	
	public boolean HandleSetHome(Player player, double money)
	{
		EconomyResponse response = this.marriagePlus.econ.withdrawPlayer(player.getName(), money);
		
		if(response.transactionSuccess()) 
		{
			player.sendMessage(String.format(ChatColor.GREEN + "You paid %s to set your Home (%s left)", this.marriagePlus.econ.format(response.amount), this.marriagePlus.econ.getBalance(player.getName())));
			return true;
		} 
		else 
		{
			player.sendMessage(String.format(ChatColor.RED + "You dont have enough money"));
			return false;
		}
	}
	
	public boolean HandleTeleport(Player player, double money)
	{
		EconomyResponse response = this.marriagePlus.econ.withdrawPlayer(player.getName(), money);
		
		if(response.transactionSuccess()) 
		{
			player.sendMessage(String.format(ChatColor.GREEN + "You paid %s for the Teleport (%s left)", this.marriagePlus.econ.format(response.amount), this.marriagePlus.econ.getBalance(player.getName())));
			return true;
		} 
		else 
		{
			player.sendMessage(String.format(ChatColor.RED + "You dont have enough money"));
			return false;
		}
	}
	
	public boolean HandleDivorce(Player player, Player otherPlayer, double money)
	{
		EconomyResponse response = this.marriagePlus.econ.withdrawPlayer(player.getName(), money/2);
		EconomyResponse response2 = this.marriagePlus.econ.withdrawPlayer(otherPlayer.getName(), money/2);
		
		if(response.transactionSuccess() && response2.transactionSuccess()) 
		{
			otherPlayer.sendMessage(String.format(ChatColor.GREEN + "You paid %s for the annul of your marriage (%s left)", this.marriagePlus.econ.format(response.amount), this.marriagePlus.econ.getBalance(otherPlayer.getName())));
			player.sendMessage(String.format(ChatColor.GREEN + "You paid %s for the annul of your marriage (%s left)", this.marriagePlus.econ.format(response.amount), this.marriagePlus.econ.getBalance(player.getName())));
			return true;
		} 
		else 
		{
			if(response.transactionSuccess())
			{
				otherPlayer.sendMessage(String.format(ChatColor.RED + "You dont have enough money"));
				player.sendMessage(String.format(ChatColor.RED + "Your partner doesnt have enough money"));
				return false;
			}
			if(response2.transactionSuccess())
			{
				player.sendMessage(String.format(ChatColor.RED + "You dont have enough money"));
				otherPlayer.sendMessage(String.format(ChatColor.RED + "Your partner doesnt have enough money"));
				return false;
			}

			otherPlayer.sendMessage(String.format(ChatColor.RED + "You dont have enough money"));
			player.sendMessage(String.format(ChatColor.RED + "You dont have enough money"));
			return false;
		}
	}
	
	public boolean HandleMarry(Player player, Player otherPlayer, double money)
	{
		EconomyResponse response = this.marriagePlus.econ.withdrawPlayer(player.getName(), money/2);
		EconomyResponse response2 = this.marriagePlus.econ.withdrawPlayer(otherPlayer.getName(), money/2);
		
		if(response.transactionSuccess() && response2.transactionSuccess()) 
		{
			otherPlayer.sendMessage(String.format(ChatColor.GREEN + "You paid %s for your marriage (%s left)", this.marriagePlus.econ.format(response.amount), this.marriagePlus.econ.getBalance(otherPlayer.getName())));
			player.sendMessage(String.format(ChatColor.GREEN + "You paid %s for your marriage (%s left)", this.marriagePlus.econ.format(response.amount), this.marriagePlus.econ.getBalance(player.getName())));
			return true;
		} 
		else 
		{
			if(response.transactionSuccess())
			{
				otherPlayer.sendMessage(String.format(ChatColor.RED + "You dont have enough money"));
				player.sendMessage(String.format(ChatColor.RED + "Your partner doesnt have enough money"));
				return false;
			}
			if(response2.transactionSuccess())
			{
				player.sendMessage(String.format(ChatColor.RED + "You dont have enough money"));
				otherPlayer.sendMessage(String.format(ChatColor.RED + "Your partner doesnt have enough money"));
				return false;
			}

			otherPlayer.sendMessage(String.format(ChatColor.RED + "You dont have enough money"));
			player.sendMessage(String.format(ChatColor.RED + "You dont have enough money"));
			return false;
		}
	}
}
