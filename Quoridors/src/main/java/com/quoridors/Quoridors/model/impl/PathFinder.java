package com.quoridors.Quoridors.model.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.quoridors.Quoridors.model.PlayerI;

public class PathFinder {
	PlayerI player1;
	PlayerI player2;
	GameEntity[][] map;
	int[][] pathMap;

	public boolean allowWallPlacement(GameEntity[][] map) {
		this.map = map;
		int[][] intMap1 = new int[map.length][map[0].length];
		int[][] intMap2 = new int[map.length][map[0].length];
		for (int i = 0; i < intMap1.length; i++) {
			for (int j = 0; j < intMap1[0].length; j++) {
				intMap1[i][j] = 0;
				intMap2[i][j] = 0;
			}
		}
		return pathAlgorythm(getPlayerPosition(GameEntity.Player), map, intMap1);

	}

	public boolean pathAlgorythm(Point player, GameEntity[][] map, int[][] intMap) {
		Point pos = player;
		System.out.println(player.getX() + " " + player.getY());
		intMap[pos.getX()][pos.getY()] = 1;
		boolean hasFoundPath = false;
		boolean checkedAllPossiblePaths = false;
		List<Point> lookPoints = new ArrayList<>();
		lookPoints.add(pos);
		while (!hasFoundPath && !checkedAllPossiblePaths) {
			Point checkPoint = new Point(lookPoints.get(0).getX(), lookPoints.get(0).getY());
			if (checkPoint.getX() - 2 >= 0 && intMap[checkPoint.getX() - 2][checkPoint.getY()] != 1
					&& findEntityBetween(map, checkPoint.getX(), checkPoint.getY(), checkPoint.getX() - 2,
							checkPoint.getY()) == GameEntity.WallPlace) {
				checkPoint.addX(-2);
				intMap[checkPoint.getX()][checkPoint.getY()] = 1;
				lookPoints.add(checkPoint);
			}
			checkPoint = new Point(lookPoints.get(0).getX(), lookPoints.get(0).getY());
			if (checkPoint.getX() + 2 < intMap.length && intMap[checkPoint.getX() + 2][checkPoint.getY()] != 1
					&& findEntityBetween(map, checkPoint.getX(), checkPoint.getY(), checkPoint.getX() + 2,
							checkPoint.getY()) == GameEntity.WallPlace) {
				checkPoint.addX(2);
				intMap[checkPoint.getX()][checkPoint.getY()] = 1;
				lookPoints.add(checkPoint);
				if (checkPoint.getX() == intMap.length - 1) {
					hasFoundPath = true;
				}
			}
			checkPoint = new Point(lookPoints.get(0).getX(), lookPoints.get(0).getY());
			if (checkPoint.getY() - 2 >= 0 && intMap[checkPoint.getX()][checkPoint.getY() - 2] != 1
					&& findEntityBetween(map, checkPoint.getX(), checkPoint.getY(), checkPoint.getX(),
							checkPoint.getY() - 2) == GameEntity.WallPlace) {
				checkPoint.addY(-2);
				intMap[checkPoint.getX()][checkPoint.getY()] = 1;
				lookPoints.add(checkPoint);
			}
			checkPoint = new Point(lookPoints.get(0).getX(), lookPoints.get(0).getY());
			if (checkPoint.getY() + 2 < intMap[0].length && intMap[checkPoint.getX()][checkPoint.getY() + 2] != 1
					&& findEntityBetween(map, checkPoint.getX(), checkPoint.getY(), checkPoint.getX(),
							checkPoint.getY() + 2) == GameEntity.WallPlace) {
				checkPoint.addY(2);
				intMap[checkPoint.getX()][checkPoint.getY()] = 1;
				lookPoints.add(checkPoint);
			}
			lookPoints.remove(0);
			if (lookPoints.isEmpty()) {
				checkedAllPossiblePaths = true;
			}

		}
		for (int i = 0; i < intMap.length; i++) {
			System.out.println(Arrays.toString(intMap[i]));
//			for (int j = 0; j < intMap[0].length; j++) {
//
//			}
		}
		System.out.println("Has found path:" + hasFoundPath);
		return hasFoundPath;

	}

	public GameEntity findEntityBetween(GameEntity[][] map, int x, int y, int futureX, int futureY) {
		Point cell = new Point((x + futureX) / 2, (y + futureY) / 2);
		return map[cell.getX()][cell.getY()];
	}

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

	public PlayerI getPlayer1() {
		return player1;
	}

	public void setPlayer1(PlayerI player1) {
		this.player1 = player1;
	}

	public PlayerI getPlayer2() {
		return player2;
	}

	public void setPlayer2(PlayerI player2) {
		this.player2 = player2;
	}

}
