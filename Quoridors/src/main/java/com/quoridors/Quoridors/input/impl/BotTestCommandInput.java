package com.quoridors.Quoridors.input.impl;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.quoridors.Quoridors.input.Input;
import com.quoridors.Quoridors.model.impl.GameRunner;

public class BotTestCommandInput implements Input {
	private GameRunner game;
	private Scanner scanner;

	public BotTestCommandInput(GameRunner game, Scanner scanner) {
		super();
		this.game = game;
		this.scanner = scanner;
	}

	@Override
	public void waitForCommand() {
		StringBuilder abet = new StringBuilder("ABCDEFGHI");
		StringBuilder wabet = new StringBuilder("STUVWXYZ");
		boolean success = false;
		String firstLetParam = null;
		String param2 = null;
		String letterParam = null;
		//Scanner scanner = new Scanner(System.in);
		do {
			// System.out.println("input command");
			String[] command = scanner.nextLine().split(" ");
			String input = command[0];
			// System.out.println(command[1]);
			if (command[1].length() == 2) {
				firstLetParam = command[1].substring(0, 1);
				param2 = command[1].substring(1);
			} else if (command[1].length() == 3) {
				firstLetParam = command[1].substring(0, 1);
				param2 = command[1].substring(1, 2);
				letterParam = command[1].substring(2);
			}
			switch (input) {
			case "move":
				int y = Integer.valueOf(param2) * 2 - 2;
				int x = abet.indexOf(firstLetParam) * 2;
				// System.out.println(y + " " + x);
				success = game.changePosition(y, x);
				break;
			case "jump":
				int yJ = Integer.valueOf(param2) * 2 - 2;
				int xJ = abet.indexOf(firstLetParam) * 2;
				// System.out.println(yJ + " " + xJ);
				success = game.jumpPosition(yJ, xJ);
				break;
			case "wall":
				// System.out.println(firstLetParam);
				// System.out.println(param2);
				// System.out.println(letterParam);
				int wallX = wabet.indexOf(firstLetParam) * 2 + 1;
				int wallY = Integer.valueOf(param2) * 2 - 1;
				if (letterParam.equals("h")) {
					// System.out.println("equals");
					wallX--;
				} else {
					wallY--;
				}
				// System.out.println(wallY + " " + wallX);
				success = game.placeWall(wallY, wallX);
				break;
			default:
				// System.out.println("Invalid command");
				break;
			}
		} while (!success);
		// scanner.close();

	}
	
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

}
