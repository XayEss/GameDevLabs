package com.quoridors.Quoridors.gui.impl;

import com.quoridors.Quoridors.gui.GUIInterface;
import com.quoridors.Quoridors.model.impl.GameEntity;

public class ConsoleOutput implements GUIInterface{
	private static final Enum player1 = GameEntity.Player;
	private static final Enum space = GameEntity.Space;
	
	
	@Override
	public void drawBoard(GameEntity[][] map) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				String output = "~";
				switch (map[i][j]) {
					case Player:
						output = "1";
						break;
					case Player2:
						output = "2";
						break;
					case Space:
						output = ".";
						break;
					case WallPlace:
						output = "_";
						break;
					case Wall:
						output = "#";
						break;
				}
				System.out.print(output);
			}
			System.out.println();
		}
		
	}


	@Override
	public void drawBoard() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void startFrame() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void printWinner(String player) {
		System.out.println("winner: " + player);
	}


	@Override
	public void clearFrame() {
		// TODO Auto-generated method stub
		
	}

}
