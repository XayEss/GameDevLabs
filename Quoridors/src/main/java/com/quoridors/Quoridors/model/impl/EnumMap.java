package com.quoridors.Quoridors.model.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import com.quoridors.Quoridors.gui.GUIInterface;
import com.quoridors.Quoridors.gui.impl.ConsoleOutput;
import com.quoridors.Quoridors.model.GameMap;

public class EnumMap implements GameMap {
	private GameEntity[][] map;
	private PathFinder pathFinder;

	public EnumMap(GameEntity[][] map, PathFinder pathFinder) {
		this.map = map;
		this.pathFinder = pathFinder;
	}

	public EnumMap(PathFinder pathFinder) {
		this.pathFinder = pathFinder;
	}

	public void createMap(int size) {
		// int[][] mapI = new int[3][4];
		// map = new GameEntity[2][3];
		map = new GameEntity[][] {
				{ GameEntity.Space, GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Player,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space },
				{ GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace },
				{ GameEntity.Space, GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space },
				{ GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace },
				{ GameEntity.Space, GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space },
				{ GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace },
				{ GameEntity.Space, GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space },
				{ GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace },
				{ GameEntity.Space, GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space },
				{ GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace },
				{ GameEntity.Space, GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space },
				{ GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace },
				{ GameEntity.Space, GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space },
				{ GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace },
				{ GameEntity.Space, GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space },
				{ GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace, GameEntity.WallPlace,
						GameEntity.WallPlace },
				{ GameEntity.Space, GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Player2,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space,
						GameEntity.WallPlace, GameEntity.Space, GameEntity.WallPlace, GameEntity.Space } };
	}

	public boolean placeHorizontalWall(int x, int y) {
		GameEntity[][] testMap = cloneMap();
		if (testMap[x][y] == GameEntity.WallPlace && testMap[x][y + 1] == GameEntity.WallPlace
				&& testMap[x][y + 2] == GameEntity.WallPlace) {
			testMap[x][y] = GameEntity.Wall;
			testMap[x][y + 1] = GameEntity.Wall;
			testMap[x][y + 2] = GameEntity.Wall;
			if (pathFinder.allowWallPlacement(testMap)) {
				map = testMap;
				return true;
			}
		}
		return false;

	}

	public boolean placeVerticalWall(int x, int y) {
		GameEntity[][] testMap = cloneMap();
		if (testMap[x][y] == GameEntity.WallPlace && testMap[x + 1][y] == GameEntity.WallPlace
				&& testMap[x + 2][y] == GameEntity.WallPlace) {
			testMap[x][y] = GameEntity.Wall;
			testMap[x + 1][y] = GameEntity.Wall;
			testMap[x + 2][y] = GameEntity.Wall;
			if (pathFinder.allowWallPlacement(testMap)) {
				map = testMap;
				return true;
			}
		}
		return false;
	}

	public GameEntity[][] cloneMap() {
		GameEntity[][] result = null;// = new GameEntity[map.length][map[0].length];
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream ous = new ObjectOutputStream(baos);
			ous.writeObject(map);
			ous.close();
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			result = (GameEntity[][]) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		for (int i = 0; i < map.length; i++) {
//			for (int j = 0; j < map[0].length; j++) {
//				
//			}
//		}
		return result;
	}

	@Override
	public boolean placeWall(int x, int y) {
		boolean result;
		if (y % 2 != 0) {
			result = placeVerticalWall(x, y);
		} else {
			result = placeHorizontalWall(x, y);
		}
		System.out.println("Can place wall:" + result);
		return result;
	}

	public boolean moveUp(GameEntity ge, int x, int y) {
		GameEntity field = map[x][y];
		if (x - 1 >= 0) {
			if (field == GameEntity.Space) {
				map[x][y] = GameEntity.Space;
				map[x - 1][y] = ge;
				return true;
			} else if (x - 2 >= 0 && (field == GameEntity.Player || field == GameEntity.Player2)) {
				map[x][y] = GameEntity.Space;
				map[x - 2][y] = ge;
				return true;

			}
		}
		return false;
	}

	public Point movePlayer(GameEntity ge, int x, int y, int futureX, int futureY) {
		Point resultCoords = null;
		if (futureX >= 0 && futureX < map.length && futureY >= 0 && futureY < map[0].length) {
			Point cell = findCellBetween(x, y, futureX, futureY);
			if ((Math.abs(futureX - x) == 2 && y == futureY || Math.abs(futureY - y) == 2 && x == futureX)) {
				if (map[futureX][futureY] == GameEntity.Space && map[cell.getX()][cell.getY()] != GameEntity.Wall) {
					setPlayerPosition(ge, futureX, futureY);
					nullifyPreviousPosition(x, y);
					resultCoords = new Point(futureX, futureY);
				} else if (map[futureX][futureY] == GameEntity.Player || map[futureX][futureY] == GameEntity.Player2) {
					Point p = findMovementDirection(x, y, futureX, futureY);
					futureX += p.getX();
					futureY += p.getY();
					Point vector = findCellBetween(x + p.getX(), y + p.getY(), futureX, futureY);
					if (map[vector.getX()][vector.getY()] != GameEntity.Wall) {
						setPlayerPosition(ge, futureX, futureY);
						nullifyPreviousPosition(x, y);
						resultCoords = new Point(futureX, futureY);
					}
				}
			} else if (Math.abs(futureX - x) == 2 && Math.abs(futureY - y) == 2
					&& isAnotherPlayerOnPlace(ge, x, y, futureX, futureY)) {
				Point p = findMovementDirection(x, y, futureX, futureY);
				Point cellBetween = findCellBetween(x, y, futureX, futureY);
				if (map[cellBetween.getX()][cellBetween.getY()] != GameEntity.Wall) {
					setPlayerPosition(ge, futureX, futureY);
					nullifyPreviousPosition(x, y);
					resultCoords = new Point(futureX, futureY);
				}

			}
		}
		return resultCoords;
	}

	public boolean isAnotherPlayerOnPlace(GameEntity play, int x, int y, int futureX, int futureY) {
		GameEntity player = play == GameEntity.Player ? GameEntity.Player2 : GameEntity.Player;
		if (map[futureX][y] == player && map[futureX > x ? futureX + 1 : futureX - 1][y] == GameEntity.Wall
				&& map[futureX][futureY > y ? futureY - 1 : futureY + 1] != GameEntity.Wall
				|| map[x][futureY] == player && map[x][futureY > y ? futureY + 1 : futureY - 1] == GameEntity.Wall
						&& map[futureX > x ? futureX - 1 : futureX + 1][futureY] != GameEntity.Wall) {
			return true;
		}

		return false;

	}

	public void nullifyPreviousPosition(int x, int y) {
		map[x][y] = GameEntity.Space;
	}

	public void setPlayerPosition(GameEntity ge, int x, int y) {
		map[x][y] = ge;
	}

	public void checkWinner() {
		// if()
	}

	@Override
	public GameEntity[][] getMap() {
		return map;
	}

	public int getLength() {
		return map.length;
	}

	@Override
	public Point getPlayerPosition(GameEntity entity) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == entity) {
					return new Point(i, j);
				}
			}
		}
		return null;
	}

	@Override
	public Point findMovementDirection(int x, int y, int futureX, int futureY) {
		int xMovement = futureX - x;
		int yMovement = futureY - y;
		return new Point(xMovement, yMovement);
	}

	@Override
	public Point findCellBetween(int x, int y, int futureX, int futureY) {
		return new Point((x + futureX) / 2, (y + futureY) / 2);
	}

	@Override
	public Point getVector(Point point) {
		return new Point(point.getX() > 0 ? 1 : point.getX() == 0 ? 0 : -1,
				point.getY() > 0 ? 1 : point.getY() == 0 ? 0 : -1);
	}

	public boolean coordsInPlace(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean pathCondition() {
		return false;
	}

	@Override
	public GameEntity findEntityBetween(GameEntity[][] map, int x, int y, int futureX, int futureY) {
		Point cell = findCellBetween(x, y, futureX, futureY);
		return map[cell.getX()][cell.getY()];
	}

}
