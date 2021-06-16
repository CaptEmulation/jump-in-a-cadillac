package com.github.kayblock.tiktok;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;

public class PlayerListener implements Listener {
	private Jumpboard jumpboard;
	private List<Player> loggedInPlayers;
	
	
	public PlayerListener(Jumpboard jumpboard) {
		super();
		this.jumpboard = jumpboard;
		this.loggedInPlayers = new LinkedList<Player>();
	}


	public void listenToPlayer(Player player) {
		// Getting also creates it, if needed
		this.jumpboard.getPlayerScore(player);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		System.out.println("JUMPBOAT - Player joined");
		Player player = event.getPlayer();
		this.listenToPlayer(player);
		this.loggedInPlayers.add(player);
		Scoreboard board = this.jumpboard.getScoreboard();
		player.setScoreboard(board);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		System.out.println("JUMPBOAT - Player quit");
		Player player = event.getPlayer();
		this.loggedInPlayers.remove(player);
	}
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		System.out.println("JUMPBOAT - Player right interaction");
		if (event.getRightClicked() instanceof Boat) {
			System.out.println("JUMPBOAT - is boat");
			Boat boat = (Boat)event.getRightClicked();
			this.jumpboard.jumpedInBoat(event.getPlayer());
			for (Entity passenger: boat.getPassengers()) {
				if (this.loggedInPlayers.contains(passenger) && event.getPlayer() == passenger) {
					System.out.println("JUMPBOAT - Player is in boat");
					this.jumpboard.jumpedInBoat(event.getPlayer());
				}
			}
			
		}
	}
}
