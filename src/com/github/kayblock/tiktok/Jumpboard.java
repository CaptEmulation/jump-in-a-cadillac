package com.github.kayblock.tiktok;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Jumpboard {
	
	private Scoreboard board;
	private Objective objective;
	
	public Jumpboard() {
		super();
		init();
	}

	private void init() {
		this.board = this.createScoreboard();
		this.objective = this.createobjective(this.board);
	}
	
	private Scoreboard createScoreboard() {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		return board;
	}
	
	private Objective createobjective(Scoreboard board) {
		Config config = Config.get();
		String displayName = config.displaySlot == DisplaySlot.BELOW_NAME ?  String.format("/ %d", config.max) : config.scoreboardName;
		Objective objective = board.registerNewObjective("jump_in_boat", "dummy", displayName);
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		return objective;
	}
	public Scoreboard getScoreboard() {
		return this.board;
	}
	
	public Score getPlayerScore(Player player) {
		String scoreName = player.getDisplayName();
		Score score = this.objective.getScore(scoreName);
		return score;
	}
	public void jumpedInBoat(Player player) {
		String scoreName = player.getDisplayName();
		Score score = this.objective.getScore(scoreName);
		score.setScore(score.getScore() + 1);
	}
}
