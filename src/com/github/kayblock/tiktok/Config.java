package com.github.kayblock.tiktok;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.bukkit.scoreboard.DisplaySlot;

public class Config {
	public String scoreboardName; 
	public DisplaySlot displaySlot;
	public int max;
	
	private static Config singleton;
	private static final String CONFIG_FILE = "config.properties";
	
	
	public static Config get() {
		if (singleton == null) {
			try {
				singleton = Config.load();
			} catch (IOException e) {
				System.out.format("Failed to load %s\n", CONFIG_FILE);
				e.printStackTrace();
			}
		}
		return singleton;
	}
	
	private static Config load() throws IOException {
		Properties prop = new Properties();
		InputStream inputStream;
		String propFileName = "config.properties";
		Config config = new Config();
		inputStream = config.getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		
		
		config.scoreboardName = prop.getProperty("scoreboardName");
		switch(prop.getProperty("scoreboardType")) {
		case "sidebar":
			config.displaySlot = DisplaySlot.SIDEBAR;
			break;
		case "playerList":
			config.displaySlot = DisplaySlot.PLAYER_LIST;
			break;
		default:
		case "belowName":
			config.displaySlot = DisplaySlot.BELOW_NAME;
			break;
		
		}
		config.max = Integer.parseInt(prop.getProperty("max"));
		return config;
	}
}
