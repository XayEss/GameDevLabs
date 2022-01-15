package com.quoridors.Quoridors.model;

import com.quoridors.Quoridors.model.impl.GameEntity;
import com.quoridors.Quoridors.model.impl.Point;

public interface GameMap {
	
public void createMap(int size);
	boolean placeHorizontalWall(int x, int y);
	boolean placeVerticalWall(int x, int y);
	Point movePlayer(GameEntity ge, int x, int y, int futureX, int futureY);
	void setPlayerPosition(GameEntity ge, int x , int y);
	GameEntity[][] getMap();
	int getLength();
	Point getPlayerPosition(GameEntity entity);
	Point findMovementDirection(int x, int y, int futureX, int futureY);
	Point findCellBetween(int x, int y, int futureX, int futureY);
	Point getVector(Point point);
	boolean placeWall(int x, int y);
	GameEntity findEntityBetween(GameEntity[][] map, int x, int y, int futureX, int futureY);
}
