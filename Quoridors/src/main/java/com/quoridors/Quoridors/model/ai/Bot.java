package com.quoridors.Quoridors.model.ai;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.quoridors.Quoridors.model.impl.GameRunner;
import com.quoridors.Quoridors.model.impl.PathFinder;
import com.quoridors.Quoridors.model.impl.Point;

public class Bot {
	private PathFinder finder;
	private LinkedList<Point> path;
	private LinkedList<Point> opponentPath;
	private GameRunner gameRunner;
	private Random rand;

	public Bot(PathFinder finder, GameRunner gameRunner) {
		this.finder = finder;
		this.gameRunner = gameRunner;
		rand = new Random();
	}

	private void getPaths() {
		path = finder.getPlayer2Path();
		opponentPath = finder.getPlayer1Path();
	}

	public void decideMovement() {
		getPaths();
		System.out.println("Trying to make movement");
		double wallChance = rand.nextDouble();
		int step = 2;
		if ((path.size() <= opponentPath.size() || wallChance < 0.4) && !path.isEmpty()) {
			Point location = path.getLast();
			Point nextMovement = path.get(path.size() - step);
			while (!gameRunner.movePlayer(nextMovement.getX() - location.getX(),
					nextMovement.getY() - location.getY())) {
				step += 2;
				nextMovement = path.get(path.size() - step);
			}
		} else {
			try {
				int locPos = opponentPath.size() - 1;
				int nextPos = locPos - 1;
				Point location = opponentPath.get(locPos);
				Point next = opponentPath.get(nextPos);
				Point wallCoords = new Point((next.getX() + location.getX()) / 2, (next.getY() + location.getY()) / 2);
				while (!gameRunner.placeWall(wallCoords.getX(), wallCoords.getY())) {
					locPos = locPos - 1;
					nextPos = locPos - 1;
					location = opponentPath.get(locPos);
					next = opponentPath.get(nextPos);
					wallCoords = new Point((next.getX() + location.getX()) / 2, (next.getY() + location.getY()) / 2);
					if (wallCoords.getX() % 2 == 0) {
					} else if (wallCoords.getY() % 2 == 0 && wallCoords.getY() >= 2) {
						wallCoords.decrementY();
						wallCoords.decrementY();
					}
				}
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}

	}

	public Point decideNextPoint() {
		getPaths();
//		if (opponentPath.size() < path.size()) {
//			Point first = opponentPath.poll();
//			Point second = opponentPath.poll();
//			Point wallPlace = new Point(Math.abs(second.getX() - first.getX()), Math.abs(second.getY() - first.getY()));
//			gr.placeWall(wallPlace.getX(), wallPlace.getY());
//		} else {
//			Point nextMovement = path.poll();
//			gr.changePlayerPosition(nextMovement.getX(), nextMovement.getY());
//		}
		Point location = path.getLast();
		Point nextMovement = path.get(path.size() - 2);
		System.out.print(nextMovement);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// gameRunner.changePosition(nextMovement.getX(), nextMovement.getY());
		Point p = new Point(nextMovement.getX() - location.getX(), nextMovement.getY() - location.getY());
		System.out.print("Movement dir " + p);
		return p;
	}
}
