package com.quoridors.Quoridors.gui;

import com.quoridors.Quoridors.model.impl.GameEntity;

public interface GUIInterface {
	
	void drawBoard();

	void drawBoard(GameEntity[][] map);
}
