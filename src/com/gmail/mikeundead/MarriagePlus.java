package com.gmail.mikeundead;

import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.mikeundead.Commands.Help;
import com.gmail.mikeundead.Databases.FlatFiles;
import com.gmail.mikeundead.Databases.MySQL;
import com.gmail.mikeundead.Economy.HandleEconomy;
import com.gmail.mikeundead.Listener.Chat;
import com.gmail.mikeundead.Listener.Damage;
import com.gmail.mikeundead.Listener.Death;
import com.gmail.mikeundead.Listener.RegainHealth;

public class MarriagePlus extends JavaPlugin
{
	public Logger log;
	private MarryCmd marryCmd;
	public MarriedPlayer marriedPlayer;
	public Help help;
    public Economy econ;
    public Permission perms;
	public FlatFiles flatFiles;
	public MySQL mySQL;
	public Chat chat;
	public Damage dmg;
	public HandleEconomy economy;
	private RegainHealth regainHealth;
	private Death death;

	public void onEnable()
	{			
		this.log = getLogger();

		this.setupEconomy();
		this.setupPermissions();
		
		this.flatFiles = new FlatFiles(this);
		this.mySQL = new MySQL(this);

		this.economy = new HandleEconomy(this);
		this.marriedPlayer = new MarriedPlayer(this);
		this.help = new Help(this);
		
		this.chat = new Chat(this);
		this.dmg = new Damage(this);
		this.regainHealth = new RegainHealth(this);
		this.death = new Death(this);
		
		this.marryCmd = new MarryCmd(this, this.perms, this.econ);
		
		getCommand("marry").setExecutor(this.marryCmd);
		getServer().getPluginManager().registerEvents(this.chat, this);
		getServer().getPluginManager().registerEvents(this.dmg, this);
		getServer().getPluginManager().registerEvents(this.regainHealth, this);
		getServer().getPluginManager().registerEvents(this.death, this);

		this.log.info("MarriagePlus has been enabled!");
	}
	 
	public void onDisable()
	{ 
		this.log.info("MarriagePlus has been disabled.");
	}
	
	private boolean setupEconomy() 
	{
		if(getServer().getPluginManager().getPlugin("Vault") == null)
		{
			return false;
	    }
		
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        
        if (rsp == null) 
        {
            return false;
        }
    
        this.econ = rsp.getProvider();
        return this.econ != null;
    }
	  
	private boolean setupPermissions() 
	{
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        this.perms = rsp.getProvider();
        return this.perms != null;
    }
}
