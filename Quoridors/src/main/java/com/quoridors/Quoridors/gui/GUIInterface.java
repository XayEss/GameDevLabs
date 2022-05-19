package com.quoridors.Quoridors.gui;

import com.quoridors.Quoridors.model.impl.GameEntity;

public interface GUIInterface {
	
	void startFrame();
	
	void drawBoard();

	void drawBoard(GameEntity[][] map);
	
	void printWinner(String player);
	
	void clearFrame();
}
