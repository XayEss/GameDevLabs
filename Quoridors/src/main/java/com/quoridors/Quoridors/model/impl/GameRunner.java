package com.quoridors.Quoridors.model.impl;

import java.util.Objects;

import com.quoridors.Quoridors.gui.GUIInterface;
import com.quoridors.Quoridors.gui.impl.ConsoleOutput;
import com.quoridors.Quoridors.gui.impl.GUI;
import com.quoridors.Quoridors.input.Input;
import com.quoridors.Quoridors.input.impl.ConsoleInput;
import com.quoridors.Quoridors.input.impl.KeyInput;
import com.quoridors.Quoridors.model.GameMap;
import com.quoridors.Quoridors.model.PlayerI;
import com.quoridors.Quoridors.model.ai.Bot;

public class GameRunner {
	private PlayerI player1;
	private PlayerI player2;
	private Input gameInput;
	private GameMap map;
	private GUIInterface output;
	private GameEntity player;
	private Bot bot;
	private boolean winner;
	private boolean isAnotherPlayerABot = false;
	private boolean botMove = false;

	public GameRunner(PlayerI player1, PlayerI player2, GameMap map) {
		super();
		this.player1 = new Player();
		this.player2 = new Player();
		this.map = map;
		setPlayersPosition();
		player = GameEntity.Player;
		GUI gui = new GUI(this);
		output = gui;
		gameInput = new ConsoleInput(this);
		bot = new Bot(map.getPathFinder(), this);
	}

	public GameRunner(PlayerI player1, PlayerI player2, GameMap map, boolean isAnotherPlayerABot) {
		super();
		this.player1 = new Player();
		this.player2 = new Player();
		this.map = map;
		setPlayersPosition();
		player = GameEntity.Player;
		GUI gui = new GUI(this);
		output = gui;
		bot = new Bot(map.getPathFinder(), this);
	}

	private void setPlayersPosition() {
		Point point1 = map.getPlayerPosition(GameEntity.Player);
		Point point2 = map.getPlayerPosition(GameEntity.Player2);
		player1.setX(point1.getX());
		player1.setY(point1.getY());
		player2.setX(point2.getX());
		player2.setY(point2.getY());
	}

	public void startGame() {
		output.startFrame();
	}

	public void runGame() {
		boolean game = true;
		do {
			if (!isAnotherPlayerABot || !botMove) {
				// gameInput.waitForCommand();
				map.waitForMovement();
			} else {
				// moveBot();
				bot.decideMovement();
			}
			// gameFinished();
		} while (game);
		// printWinner();
	}

	public void moveBot() {
		Point next = bot.decideNextPoint();
		System.out.print(next);
		changePosition(next.getX(), next.getY());
	}

	public void decideBotMovement() {
		bot.decideMovement();
	}

	public void movePlayer(int diffx, int diffy) {
		PlayerI movingPlayer = player == GameEntity.Player ? player1 : player2;
		changePosition(movingPlayer.getX() + diffx, movingPlayer.getY() + diffy);
	}

	public void changePosition(int x, int y) {
		PlayerI movingPlayer = player == GameEntity.Player ? player1 : player2;
		Point position = null;
		if (!winner) {
			position = map.movePlayer(player, movingPlayer.getX(), movingPlayer.getY(), x, y);
		}
		if (position != null) {
			changePlayerPosition(position.getX(), position.getY());
			gameFinished();
			changePlayer();
			map.releaseMovement();
			System.out.println(position);
		}
		output.drawBoard(map.getMap());
	}

	public boolean placeWall(int x, int y) {
		if (!winner && map.placeWall(x, y)) {
			changePlayer();
			output.drawBoard(map.getMap());
			return true;
		} else {
			return false;
		}
	}

	private void changePlayer() {
		if (player == GameEntity.Player) {
			player = GameEntity.Player2;
		} else {
			player = GameEntity.Player;
		}
		if (isAnotherPlayerABot) {
			reverseBotMove();
		}
	}

	private GameEntity checkWinner(GameEntity player) {
		GameEntity winner = null;
		System.out.println("StarttingWinnercheck" + player);
		System.out.println("player1x: " + player1.getX());
		System.out.println("player2x: " + player2.getX());
		if (player == GameEntity.Player && player1.getX() == map.getMap().length - 1) {
			winner = GameEntity.Player;
		} else if (player == GameEntity.Player2 && player2.getX() == 0) {
			winner = GameEntity.Player2;
		}
		return winner;
	}

	public PlayerI getCurrentPlayer() {
		return player1;
	}

	public boolean gameFinished() {
		GameEntity winnerPlayer = checkWinner(player);
		winner = winnerPlayer != null ? true : false;
		if (winner) {
			printWinner();
		}
		return winner;
	}

	public void changePlayerPosition(int x, int y) {
		PlayerI movingPlayer = player == GameEntity.Player ? player1 : player2;
		movingPlayer.setX(x);
		movingPlayer.setY(y);
	}

	public void resetGame() {
		System.out.println("R Key pressed");
		map.createMap();
		setPlayersPosition();
		output.drawBoard(map.getMap());
		if (winner) {
			winner = false;
		}
	}

	public void initBoard() {
		output.drawBoard(map.getMap());
	}

	private void printWinner() {
		output.printWinner(player == GameEntity.Player ? "Player 2" : "Player 1");

	}

	public void playWithABot() {
		isAnotherPlayerABot = true;
	}

	public boolean isWinner() {
		return winner;
	}

	public boolean getIsABot() {
		return isAnotherPlayerABot;
	}

	public boolean getIsBotMoving() {
		return botMove;
	}

	private void reverseBotMove() {
		botMove = !botMove;
	}

}
