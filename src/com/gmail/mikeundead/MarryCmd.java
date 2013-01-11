package com.gmail.mikeundead;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.mikeundead.Commands.Help;
import com.gmail.mikeundead.Commands.Home;
import com.gmail.mikeundead.Commands.LoveAnimation;
import com.gmail.mikeundead.Commands.MarriedPlayerList;
import com.gmail.mikeundead.Commands.MarryTp;
import com.gmail.mikeundead.Commands.NonPriestMarriage;
import com.gmail.mikeundead.Commands.Priester;
import com.gmail.mikeundead.Commands.PvPDisable;
import com.gmail.mikeundead.Commands.PvPEnable;

public class MarryCmd implements CommandExecutor 
{
	private MarriagePlus marriagePlus;
	private Help help;
	private MarriedPlayerList list;
	private Permission perms;
	private LoveAnimation love;
	private MarryTp marryTp;
	private Priester priester;
	private PvPDisable pvpDisable;
	private PvPEnable pvpEnable;
	private Home home;
	private NonPriestMarriage nonPriest;
	
	public Economy econ;
	
	public MarryCmd(MarriagePlus marriagePlus, Permission perms, Economy econ) 
	{
		this.marriagePlus = marriagePlus;
		this.help = this.marriagePlus.help;
		this.list = new MarriedPlayerList(this.marriagePlus);
		
		this.perms = perms;
		this.econ = econ;
		
		this.love = new LoveAnimation(this.marriagePlus);
		this.home = new Home(this.marriagePlus);
		this.marryTp = new MarryTp(this.marriagePlus);
		this.priester = new Priester(this.marriagePlus);
		this.pvpDisable = new PvPDisable(this.marriagePlus);
		this.pvpEnable = new PvPEnable(this.marriagePlus);
		this.nonPriest = new NonPriestMarriage(this.marriagePlus);
	}

	public boolean CheckForPerm(Player player, String perms)
	{
		if(this.perms.has(player, perms))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
		
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) 
	{
		Player player = null;
		if (sender instanceof Player) 
		{
			player = (Player) sender;
	    }
		else
		{
			sender.sendMessage("You can't use this command from the console.");
			return true;
		}
		
        if (args.length == 0) 
        {
        	this.help.ShowAvailableCmds(player);
        	return true;
        }
        if (args[0].equalsIgnoreCase("list"))
        {
        	return this.Listcmd(player);
        }        
        if (args[0].equalsIgnoreCase("love")) 
        {
      		return this.Lovecmd(player);
        }
        if (args[0].equalsIgnoreCase("pvpon")) 
        {
        	return this.PVPoncmd(player);
        }
        if (args[0].equalsIgnoreCase("pvpoff"))
        {
        	return this.PVPoffcmd(player);
        }
        if(args[0].equalsIgnoreCase("home"))
        {
        	return this.Homecmd(player);
        }
        if(args[0].equalsIgnoreCase("sethome"))
        {
        	return this.SetHomecmd(player);
        }
        if (args[0].equalsIgnoreCase("tp"))
        {
        	return this.Teleportcmd(player);
        }
        if(args.length == 2 && args[0].equalsIgnoreCase("priest") && player.isOp())
        {
        	this.priester.setPriester(args, player);
        	return true;
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("divorce"))
        {
        	return this.Divorcecmd(player, args);
        }
        if (args.length == 2)
        {        	
        	return this.priester.Handle(player, args);
        }
        if (args.length == 1)
        {        	
        	return this.nonPriest.Handle(player, args);
        }
        return true;
	}
	
	private boolean Listcmd(Player player)
	{
		if(this.marriagePlus.flatFiles.GetPermissionsStatus())
    	{
    		if(this.CheckForPerm(player, "marry.list"))
    		{
    			this.list.Handle(player);
    		}
    	}
    	else
    	{
        	this.list.Handle(player);
    	}
		return true;
	}
	
	
	private boolean Lovecmd(Player player)
	{
		if(this.marriagePlus.marriedPlayer.GetPartner(player.getName()) != null && !this.marriagePlus.marriedPlayer.GetPartner(player.getName()).equalsIgnoreCase(""))
    	{
			if(this.marriagePlus.flatFiles.GetPermissionsStatus())
			{
				if(this.CheckForPerm(player, "marry.love"))
				{
					this.love.Handle(player); 
				}
			}
			else
			{
				this.love.Handle(player);
			}
    	}
		else
		{
			player.sendMessage(ChatColor.RED + "You are not Married.");
		}
    	return true;
	}
	
	
	private boolean PVPoncmd(Player player)
	{
		if(this.marriagePlus.marriedPlayer.GetPartner(player.getName()) != null && !this.marriagePlus.marriedPlayer.GetPartner(player.getName()).equalsIgnoreCase(""))
    	{
	       	if(this.marriagePlus.flatFiles.GetPermissionsStatus())
	    	{
	    		if(this.CheckForPerm(player, "marry.pvpon"))
	    		{
	            	this.pvpEnable.Handle(player);
	    		}
	    	}
	    	else
	    	{
	        	this.pvpEnable.Handle(player);
	    	}
       	}
		else
		{
			player.sendMessage(ChatColor.RED + "You are not Married.");
		}
    	return true;
	}
	
	
	private boolean PVPoffcmd(Player player)
	{
		if(this.marriagePlus.marriedPlayer.GetPartner(player.getName()) != null && !this.marriagePlus.marriedPlayer.GetPartner(player.getName()).equalsIgnoreCase(""))
    	{
			if(this.marriagePlus.flatFiles.GetPermissionsStatus())
	    	{
	    		if(this.CheckForPerm(player, "marry.pvpoff"))
	    		{
	            	this.pvpDisable.Handle(player);
	    		}
	    	}
	    	else
	    	{
	        	this.pvpDisable.Handle(player);
	    	}
		}
		else
		{
			player.sendMessage(ChatColor.RED + "You are not Married.");
		}
    	return true;
	}
	
	
	private boolean Homecmd(Player player)
	{
		if(this.marriagePlus.marriedPlayer.GetPartner(player.getName()) != null && !this.marriagePlus.marriedPlayer.GetPartner(player.getName()).equalsIgnoreCase(""))
    	{
	    	if(this.marriagePlus.flatFiles.GetPermissionsStatus())
	    	{
	    		if(this.CheckForPerm(player, "marry.home"))
	    		{
	            	this.home.Handle(player);
	    		}
	    	}
	    	else
	    	{
	        	this.home.Handle(player);
	    	}
    	}
		else
		{
			player.sendMessage(ChatColor.RED + "You are not Married.");
		}
    	return true;
	}
		
	
	private boolean SetHomecmd(Player player)
	{
		if(this.marriagePlus.marriedPlayer.GetPartner(player.getName()) != null && !this.marriagePlus.marriedPlayer.GetPartner(player.getName()).equalsIgnoreCase(""))
    	{
	    	if(this.marriagePlus.flatFiles.GetPermissionsStatus())
	    	{
	    		if(this.CheckForPerm(player, "marry.home"))
	    		{
	            	this.home.HandleSetHome(player);
	    		}
	    	}
	    	else
	    	{
	        	this.home.HandleSetHome(player);
	    	}
    	}
		else
		{
			player.sendMessage(ChatColor.RED + "You are not Married.");
		}
    	return true;
	}
	

	private boolean Divorcecmd(Player player, String[] args)
	{
		if(this.marriagePlus.flatFiles.GetPermissionsStatus())
    	{
    		if(this.CheckForPerm(player, "marry.priest") || this.marriagePlus.flatFiles.IsPriester(player.getName()))
    		{
            	this.priester.Divorce(player, args);
    		}
    	}
    	else
    	{
    		if(this.marriagePlus.flatFiles.IsPriester(player.getName()))
    		{
    	       	this.priester.Divorce(player, args);
    		}
    	}
    	return true;
	}

	private boolean Teleportcmd(Player player)
	{
		if(this.marriagePlus.marriedPlayer.GetPartner(player.getName()) != null && !this.marriagePlus.marriedPlayer.GetPartner(player.getName()).equalsIgnoreCase(""))
    	{
	    	if(this.marriagePlus.flatFiles.GetPermissionsStatus())
	    	{
	    		if(this.CheckForPerm(player, "marry.tp"))
	    		{
	            	this.marryTp.Handle(player);
	    		}
	    	}
	    	else
	    	{
	        	this.marryTp.Handle(player);
	    	}
    	}
		else
		{
			player.sendMessage(ChatColor.RED + "You are not Married.");
		}
    	return true;
	}
}
