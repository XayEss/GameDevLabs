package com.quoridors.Quoridors.model.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.quoridors.Quoridors.model.PlayerI;

public class PathFinder {
	private PlayerI player1;
	private PlayerI player2;
	private GameEntity currentPlayer;
	private GameEntity[][] map;
	private int[][] pathMap;
	private Tree<Point> player1Tree;
	private Tree<Point> player2Tree;
	private LinkedList<Point> player1Path;
	private LinkedList<Point> player2Path;

	public boolean allowWallPlacement(GameEntity[][] map) {
		this.map = map;
		int[][] intMap1 = new int[map.length][map[0].length];
		int[][] intMap2 = new int[map.length][map[0].length];
		player1Path = new LinkedList<>();
		player2Path = new LinkedList<>();
		player1Tree = new Tree<>(getPlayerPosition(GameEntity.Player));
		player2Tree = new Tree<>(getPlayerPosition(GameEntity.Player2));
		for (int i = 0; i < intMap1.length; i++) {
			for (int j = 0; j < intMap1[0].length; j++) {
				intMap1[i][j] = 0;
				intMap2[i][j] = 0;
			}
		}
		currentPlayer = GameEntity.Player;
		boolean p1Result = pathAlgorythm(getPlayerPosition(GameEntity.Player), map, intMap1, intMap1.length - 1);
		currentPlayer = GameEntity.Player2;
		boolean p2Result = pathAlgorythm(getPlayerPosition(GameEntity.Player2), map, intMap2, 0);
		return p1Result && p2Result;

	}
	
	public void findPlayersPaths(GameEntity[][] map) {
		this.map = map;
		int[][] intMap1 = new int[map.length][map[0].length];
		int[][] intMap2 = new int[map.length][map[0].length];
		player1Path = new LinkedList<>();
		player2Path = new LinkedList<>();
		player1Tree = new Tree<>(getPlayerPosition(GameEntity.Player));
		player2Tree = new Tree<>(getPlayerPosition(GameEntity.Player2));
		for (int i = 0; i < intMap1.length; i++) {
			for (int j = 0; j < intMap1[0].length; j++) {
				intMap1[i][j] = 0;
				intMap2[i][j] = 0;
			}
		}
		currentPlayer = GameEntity.Player;
		boolean p1Result = pathAlgorythm(getPlayerPosition(GameEntity.Player), map, intMap1, intMap1.length - 1);
		currentPlayer = GameEntity.Player2;
		boolean p2Result = pathAlgorythm(getPlayerPosition(GameEntity.Player2), map, intMap2, 0);
		//return p1Result && p2Result;
	}
	
	

	public boolean pathAlgorythm(Point player, GameEntity[][] map, int[][] intMap, int finalDestination) {
		Point pos = player;
		Tree<Point> currentTree = currentPlayer == GameEntity.Player ? player1Tree : player2Tree;
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
				currentTree.addLeaf(checkPoint, new Point(checkPoint.getX()-2, checkPoint.getY()));
				checkPoint.addX(-2);
				intMap[checkPoint.getX()][checkPoint.getY()] = 1;
				lookPoints.add(checkPoint);
				if (checkPoint.getX() == finalDestination) {
					hasFoundPath = true;
					findShortestPath(checkPoint, currentTree);
				}
			}
			checkPoint = new Point(lookPoints.get(0).getX(), lookPoints.get(0).getY());
			if (checkPoint.getX() + 2 < intMap.length && intMap[checkPoint.getX() + 2][checkPoint.getY()] != 1
					&& findEntityBetween(map, checkPoint.getX(), checkPoint.getY(), checkPoint.getX() + 2,
							checkPoint.getY()) == GameEntity.WallPlace) {
				currentTree.addLeaf(checkPoint, new Point(checkPoint.getX()+2, checkPoint.getY()));
				checkPoint.addX(2);
				intMap[checkPoint.getX()][checkPoint.getY()] = 1;
				lookPoints.add(checkPoint);
				if (checkPoint.getX() == finalDestination) {
					hasFoundPath = true;
					findShortestPath(checkPoint, currentTree);
				}
			}
			checkPoint = new Point(lookPoints.get(0).getX(), lookPoints.get(0).getY());
			if (checkPoint.getY() - 2 >= 0 && intMap[checkPoint.getX()][checkPoint.getY() - 2] != 1
					&& findEntityBetween(map, checkPoint.getX(), checkPoint.getY(), checkPoint.getX(),
							checkPoint.getY() - 2) == GameEntity.WallPlace) {
				currentTree.addLeaf(checkPoint, new Point(checkPoint.getX(), checkPoint.getY()-2));
				checkPoint.addY(-2);
				intMap[checkPoint.getX()][checkPoint.getY()] = 1;
				lookPoints.add(checkPoint);
			}
			checkPoint = new Point(lookPoints.get(0).getX(), lookPoints.get(0).getY());
			if (checkPoint.getY() + 2 < intMap[0].length && intMap[checkPoint.getX()][checkPoint.getY() + 2] != 1
					&& findEntityBetween(map, checkPoint.getX(), checkPoint.getY(), checkPoint.getX(),
							checkPoint.getY() + 2) == GameEntity.WallPlace) {
				currentTree.addLeaf(checkPoint, new Point(checkPoint.getX(), checkPoint.getY()+2));
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
		printMovementTree();
		return hasFoundPath;

	}
	
	public void findShortestPath(Point finalPoint, Tree<Point> playerTree) {
		LinkedList<Point> path = new LinkedList<>();
		path.add(finalPoint);
		Point checkPoint = finalPoint;
		System.out.println("lol:" + finalPoint.toString());
		while(playerTree.getTree(checkPoint).getParent() != null && !path.contains(playerTree.getTree(checkPoint).getParent().getHead()) ) {
			checkPoint = playerTree.getTree(checkPoint).getParent().getHead();
			path.add(checkPoint);
			//System.out.println("running");
			System.out.println(Arrays.toString(path.toArray()));
		}
		decideWhichPlayer(path);
		System.out.println(Arrays.toString(path.toArray()));
		
	}
	
	public void decideWhichPlayer(LinkedList<Point> path) {
		if(currentPlayer == GameEntity.Player) {
			player1Path = path;
		} else {
			player2Path = path;
		}
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
	
	public void printMovementTree() {
		System.out.println(player1Tree.toString());
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

	public LinkedList<Point> getPlayer2Path() {
		LinkedList<Point> path = null;
		if(player2Path == null) {
			System.out.print("path 2 ");
		}else {
			path = player2Path;
		}
		return path;
	}
	
	public LinkedList<Point> getPlayer1Path() {
		LinkedList<Point> path = null;
		if(player1Path == null) {
			System.out.print("path 1 ");

		}else {
			path = player1Path;
		}
		return path;
	}
	

}
