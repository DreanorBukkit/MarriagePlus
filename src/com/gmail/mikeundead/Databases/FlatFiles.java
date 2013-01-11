package com.gmail.mikeundead.Databases;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.TreeMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.gmail.mikeundead.MarriagePlus;

public class FlatFiles 
{
	private MarriagePlus marriagePlus;
	
	public FlatFiles(MarriagePlus marriagePlus)
	{
		this.marriagePlus = marriagePlus;
		this.FirstRun();
	}
	
	public void FirstRun()
	{
		File file = new File(this.marriagePlus.getDataFolder(), "config.yml");

		if(!file.exists())
		{
			this.marriagePlus.getDataFolder().mkdirs();
				        
			FileConfiguration config = new YamlConfiguration();
			config.set("Permissions", false);
			config.set("Economy.Enable", false);
			config.set("Economy.Divorce", 100.00);
			config.set("Economy.Marry", 100.00);
			config.set("Economy.Tp", 25.00);
			config.set("Economy.HomeTp", 25.00);
			config.set("Economy.SetHome", 100.00);
			config.set("HealthRegain.Enable", true);
			config.set("HealthRegain.Amount", 2);
			config.set("BonusXp.Enable", true);
			config.set("BonusXp.Multiplier", 2);
			config.set("Announcement", true);
			config.set("PriestEnable", true);
			
			try 
			{
				config.save(file);
			}
	  	  	catch (IOException e) 
	  	  	{
	  	  		e.printStackTrace();
	  	  	}
		}
	}
	
	public boolean GetPriestStatus()
	{
		File file = new File(this.marriagePlus.getDataFolder(), "config.yml");

		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getBoolean("PriestEnable");
	}
	
	public boolean GetAnnouncementStatus()
	{
		File file = new File(this.marriagePlus.getDataFolder(), "config.yml");

		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getBoolean("Announcement");
	}
	
	public boolean GetBonusXPEnable()
	{
		File file = new File(this.marriagePlus.getDataFolder(), "config.yml");

		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getBoolean("BonusXp.Enable");
	}
	
	public int GetBonusXPAmount()
	{
		File file = new File(this.marriagePlus.getDataFolder(), "config.yml");

		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getInt("BonusXp.Multiplier");
	}
	
	public boolean GetHealthRegainStatus()
	{
		File file = new File(this.marriagePlus.getDataFolder(), "config.yml");

		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getBoolean("HealthRegain.Enable");
	}
	
	public int GetHealthRegainAmount()
	{
		File file = new File(this.marriagePlus.getDataFolder(), "config.yml");

		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getInt("HealthRegain.Amount");
	}
	
	public boolean GetPermissionsStatus()
	{
		File file = new File(this.marriagePlus.getDataFolder(), "config.yml");

		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getBoolean("Permissions");	
	}
	
	public boolean GetEconomyStatus()
	{
		File file = new File(this.marriagePlus.getDataFolder(), "config.yml");

		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getBoolean("Economy.Enable");
	}
	
	public double GetEconomyDivorce()
	{
		File file = new File(this.marriagePlus.getDataFolder(), "config.yml");

		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getDouble("Economy.Divorce");
	}
	
	public double GetEconomyMarry()
	{
		File file = new File(this.marriagePlus.getDataFolder(), "config.yml");

		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getDouble("Economy.Marry");
	}
	
	public double GetEconomyTp()
	{
		File file = new File(this.marriagePlus.getDataFolder(), "config.yml");

		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getDouble("Economy.Tp");
	}
	
	public double GetEconomyHomeTp()
	{
		File file = new File(this.marriagePlus.getDataFolder(), "config.yml");

		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getDouble("Economy.HomeTp");
	}
	
	public double GetEconomySetHome()
	{
		File file = new File(this.marriagePlus.getDataFolder(), "config.yml");

		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getDouble("Economy.SetHome");
	}
	
	public boolean GetPvPState(String playername)
	{
		File file = new File((new StringBuilder()).append(this.marriagePlus.getDataFolder()).append(File.separator).append("players").append(File.separator).append(playername).append(".yml").toString());
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		return config.getBoolean("PvP");
	}
	
	public void SetPvPState(String playername, boolean state)
	{
		File file = new File((new StringBuilder()).append(this.marriagePlus.getDataFolder()).append(File.separator).append("players").append(File.separator).append(playername).append(".yml").toString());
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		config.set("PvP", state);
		
		try
        {
            config.save(file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}
	
	public void SaveMarriedPlayerDivorce(String playername, String otherPlayer, String priester)
	{
		File file = new File((new StringBuilder()).append(this.marriagePlus.getDataFolder()).append(File.separator).append("players").append(File.separator).append(playername).append(".yml").toString());
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		config.set("MarriedStatus", "Single");
		config.set("MarriedTo", "");
		config.set("MarriedBy", "");
		config.set("MarriedDay", "");
		config.set("MarriedHome", "");
		config.set("MarriedHome.location.World" , "");
		config.set("MarriedHome.location.X" , "");
		config.set("MarriedHome.location.Y" , "");
		config.set("MarriedHome.location.Z" , "");
		
		try
        {
            config.save(file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}

	public void SaveMarriedPlayer(String playername, String otherPlayer, String priester)
	{
		File file = new File((new StringBuilder()).append(this.marriagePlus.getDataFolder()).append(File.separator).append("players").append(File.separator).append(playername).append(".yml").toString());
		 
		if(!file.exists())
		{
			this.marriagePlus.getDataFolder().mkdir();

		    try
		    {
		        file.createNewFile();
		    }
		    catch(Exception e)
		    {
		        e.printStackTrace();
		    }
		}

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
        Calendar cal = Calendar.getInstance();
        
        config.set("MarriedStatus", "Married");
        config.set("MarriedTo", otherPlayer);
        config.set("MarriedBy", priester);
		config.set("MarriedDay", cal.getTime());
		config.set("MarriedHome", "");
		config.set("PvP", false);
			
        try
        {
            config.save(file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}
	
	public void SaveMarriedHome(Location loc, String playername)
	{
		File file = new File((new StringBuilder()).append(this.marriagePlus.getDataFolder()).append(File.separator).append("players").append(File.separator).append(playername).append(".yml").toString());
		 
		if(!file.exists())
		{
			this.marriagePlus.getDataFolder().mkdir();

		    try
		    {
		        file.createNewFile();
		    }
		    catch(Exception e)
		    {
		        e.printStackTrace();
		    }
		}
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		String status = config.getString("MarriedStatus");
		
		if(status.equalsIgnoreCase("Married"))
		{
			config.set("MarriedHome.location.World" , loc.getWorld().getName());
			config.set("MarriedHome.location.X" , loc.getX());
			config.set("MarriedHome.location.Y" , loc.getY());
			config.set("MarriedHome.location.Z" , loc.getZ());
		}
		
		try
        {
            config.save(file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}
	
	public void SavePriester(String priestname, Player player)
	{
		File file = new File((new StringBuilder()).append(this.marriagePlus.getDataFolder()).append(File.separator).append("priests").append(File.separator).append(priestname).append(".yml").toString());

		if(!file.exists())
		{
			this.marriagePlus.getDataFolder().mkdir();

			FileConfiguration config = YamlConfiguration.loadConfiguration(file);
			
			config.set("Name", priestname);
			
			player.sendMessage(ChatColor.GREEN + player.getName() + " is now a Priester!");
			
			try 
			{
				config.save(file);
			}
	  	  	catch (IOException e) 
	  	  	{
	  	  		e.printStackTrace();
	  	  	}
		}
		
		player.sendMessage(ChatColor.RED + priestname + " is already a Priest.");
	}
	
	public boolean IsPriester(String playername)
	{
		File file = new File((new StringBuilder()).append(this.marriagePlus.getDataFolder()).append(File.separator).append("priests").append(File.separator).append(playername).append(".yml").toString());
		if(!file.exists())
		{
			return false;
		}
		
		return true;
	}
	
	public Location LoadMarriedHome(String playername)
	{
		File file = new File((new StringBuilder()).append(this.marriagePlus.getDataFolder()).append(File.separator).append("players").append(File.separator).append(playername).append(".yml").toString());
		
		if(!file.exists())
		{
			this.marriagePlus.getDataFolder().mkdir();

		    try
		    {
		        file.createNewFile();
		    }
		    catch(Exception e)
		    {
		        e.printStackTrace();
		    }
		}
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		

		
		Location loc = null;
		
		try
		{
			World world = this.marriagePlus.getServer().getWorld(config.getString("MarriedHome.location.World"));
			double x = (Double) config.get("MarriedHome.location.X");
			double y = (Double) config.get("MarriedHome.location.Y");
			double z = (Double) config.get("MarriedHome.location.Z");
			
			loc = new Location(world, x, y, z);
		}
		catch(Exception e)
		{
			return null;			
		}
		
		return loc;
	}
	
	public String LoadMarriedPlayer(String playername)
	{
		File file = new File((new StringBuilder()).append(this.marriagePlus.getDataFolder()).append(File.separator).append("players").append(File.separator).append(playername).append(".yml").toString());
		
		if(file.exists())
		{
			FileConfiguration config = YamlConfiguration.loadConfiguration(file);
			
			String x = config.getString("MarriedTo");
			
			if(!x.equalsIgnoreCase("") || x != null)
			{
				return x;
			}			
		}
		
		return null;
	}
	
	public TreeMap<String, String> LoadAllMarriedPlayers()
	{
		TreeMap<String, String> map = new TreeMap<String, String>();
		
		File file = new File((new StringBuilder()).append(this.marriagePlus.getDataFolder()).append(File.separator).append("players").toString());
		 
		if(file.exists())
		{
			File[] allFiles = file.listFiles();
			
			for (File item : allFiles)
			{
				File marriedPlayer = new File((new StringBuilder()).append(this.marriagePlus.getDataFolder()).append(File.separator).append("players").append(File.separator).append(item.getName()).toString());
				
				FileConfiguration config = YamlConfiguration.loadConfiguration(marriedPlayer);
				
				String marriedTo = config.getString("MarriedTo");
				
				if(marriedTo != null && !marriedTo.equalsIgnoreCase(""))
				{
					if(!map.containsKey(item.getName()) && !map.containsKey(marriedTo) && !map.containsValue(marriedTo) && !map.containsValue(item.getName()))
					{
						map.put(item.getName().replaceAll(".yml", ""), marriedTo);
					}
				}
			}
		}
		
		return map;
	}
}
