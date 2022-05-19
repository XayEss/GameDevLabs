package com.quoridors.Quoridors.input.impl;

import java.util.Scanner;

import com.quoridors.Quoridors.input.Input;
import com.quoridors.Quoridors.model.impl.GameRunner;

public class ConsoleInput implements Input {
	private GameRunner game;

	public ConsoleInput(GameRunner game) {
		this.game = game;
	}


	@Override
	public void waitForCommand() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input your command");
		String[] command = scanner.nextLine().split(" ");
		String param1 = null;
		String param2 = null;
		String input = command[0];
		if (command.length > 1 && command.length < 4) {
			param1 = command[1];
			param2 = command[2];
		}
		switch (input) {
		case "up":
			game.changePosition(-2, 0);
			break;
		case "down":
			game.changePosition(+2, 0);
			break;
		case "right":
			game.changePosition(0, +2);
			break;
		case "left":
			game.changePosition(0, -2);
			break;
		case "diagRU":
			game.changePosition(-2, 2);
			break;
		case "diagLU":
			game.changePosition(-2, -2);
			break;
		case "diagRD":
			game.changePosition(2, 2);
			break;
		case "diagLD":
			game.changePosition(2, -2);
			break;
		case "wall":
			if (command.length > 1) {
				game.placeWall(Integer.parseInt(param1), Integer.parseInt(param2));
			}
			break;
		default:
			System.out.println("Invalid command");
			break;
		}
		//scanner.close();
	}

}
