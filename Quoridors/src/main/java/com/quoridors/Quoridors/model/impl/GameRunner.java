package com.quoridors.Quoridors.model.impl;

import java.util.Objects;

import com.quoridors.Quoridors.gui.impl.GUI;
import com.quoridors.Quoridors.gui.GUIInterface;
import com.quoridors.Quoridors.gui.impl.ConsoleOutput;
import com.quoridors.Quoridors.model.GameMap;
import com.quoridors.Quoridors.model.PlayerI;

public class GameRunner {
	private PlayerI player1;
	private PlayerI player2;
	private GameMap map;
	private GUIInterface output;
	private GameEntity player;


	
	public GameRunner(PlayerI player1, PlayerI player2, GameMap map) {
		super();
		this.player1 = new Player();
		this.player2 = new Player();
		this.map = map;
		setPlayersPosition();
		player = GameEntity.Player;
		output = new GUI();
		output.drawBoard(map.getMap());
	}
	
	private void setPlayersPosition() {
		Point point1 = map.getPlayerPosition(GameEntity.Player);
		Point point2 = map.getPlayerPosition(GameEntity.Player2);
		player1.setX(point1.getX());
		player1.setY(point1.getY());
		player2.setX(point2.getX());
		player2.setY(point2.getY());
	}
	
	public void changePosition(int x, int y) {
		PlayerI movingPlayer = player == GameEntity.Player ? player1 : player2;
		Point position = map.movePlayer(player, movingPlayer.getX(), movingPlayer.getY(), movingPlayer.getX()+x, movingPlayer.getY()+y);
		if(position != null) {
			changePlayerPosition(position.getX(), position.getY());
			changePlayer();
		}
		output.drawBoard(map.getMap());
	}
	
	public void placeWall(int x, int y) {
		if(map.placeWall(x, y)) {
			changePlayer();
			output.drawBoard(map.getMap());
		}
	}
	
	private void changePlayer() {
		if (player == GameEntity.Player) {
			player = GameEntity.Player2;
		} else {
			player = GameEntity.Player;
		}
	}
	
	private GameEntity checkWinner(GameEntity player) {
		GameEntity winner = null;
		if(player == GameEntity.Player && player1.getX() == map.getMap().length-1) {
			winner = GameEntity.Player; 
		} else if(player == GameEntity.Player2 && player2.getX() == 0) {
			winner = GameEntity.Player2; 
		}
		return winner;
	}
	
	public PlayerI getCurrentPlayer() {
		return player1;
	}
	
	public boolean gameFinished() {
		System.out.println("winner: " + (!Objects.equals(checkWinner(player), null) ? true : false));
		return (checkWinner(player) == GameEntity.Player || checkWinner(player) == GameEntity.Player2) ? true : false;
	}
	
	public void changePlayerPosition(int x, int y) {
		PlayerI movingPlayer = player == GameEntity.Player ? player1 : player2;
		movingPlayer.setX(x);
		movingPlayer.setY(y);
	}

}
