package com.github.kayblock.tiktok;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public void onEnable() {
		System.out.println("Loading Jump in a Cadillac scoreboard");
		Jumpboard jumpboard = new Jumpboard();
		getServer().getPluginManager().registerEvents(new PlayerListener(jumpboard), this);
		System.out.println("Jump in a Cadillac scoreboard ready");
	}
	
	public static void main(String[] args) {
		Config config = Config.get();
		System.out.println(config.scoreboardName);
	}
}
