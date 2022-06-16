package com.quoridors.Quoridors.model.ai;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.quoridors.Quoridors.input.Input;
import com.quoridors.Quoridors.model.PlayerI;
import com.quoridors.Quoridors.model.impl.GameRunner;
import com.quoridors.Quoridors.model.impl.PathFinder;
import com.quoridors.Quoridors.model.impl.Point;

public class Bot implements Input {
	private PathFinder finder;
	private LinkedList<Point> path;
	private LinkedList<Point> opponentPath;
	private GameRunner gameRunner;
	private boolean botPlayer1 = false;
	private Random rand;
	private int wallAmount = 10;
	private boolean noWallmovement;
	private StringBuilder abet = new StringBuilder("ABCDEFGHI");
	private StringBuilder wabet = new StringBuilder("STUVWXYZ");

	public Bot(PathFinder finder, GameRunner gameRunner, boolean botFirst) {
		this.finder = finder;
		this.gameRunner = gameRunner;
		this.botPlayer1 = botFirst;
		rand = new Random();
	}

	private void getPaths() {
		if (botPlayer1) {
			path = finder.getPlayer1Path();
			opponentPath = finder.getPlayer2Path();
		} else {
			path = finder.getPlayer2Path();
			opponentPath = finder.getPlayer1Path();
		}
	}

	public void decideMovement() {
		getPaths();
		// System.out.println("Trying to make movement");
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
		// System.out.print(nextMovement);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// gameRunner.changePosition(nextMovement.getX(), nextMovement.getY());
		Point p = new Point(nextMovement.getX() - location.getX(), nextMovement.getY() - location.getY());
		// System.out.print("Movement dir " + p);
		return p;
	}

	@Override
	public void waitForCommand() {
		getPaths();
		// System.out.println("Trying to make movement");
		String commandToOutput = "";
		double wallChance = rand.nextDouble();
		int step = 2;
		boolean jump = false;
		if ((path.size() <= opponentPath.size() || wallChance < 0 || wallAmount <= 0 || noWallmovement)
				&& !path.isEmpty()) {
			move();
		} else {
			try {
				int locPos = opponentPath.size() - 1;
				int nextPos = locPos - 1;
				Point location = opponentPath.get(locPos);
				Point next = opponentPath.get(nextPos);
				Point wallCoords = new Point((next.getX() + location.getX()) / 2, (next.getY() + location.getY()) / 2);
				int counter = 0;
				while (!gameRunner.placeWall(wallCoords.getX(), wallCoords.getY())) {
					locPos = locPos - 1;
					nextPos = locPos - 1;
					location = opponentPath.get(locPos);
					next = opponentPath.get(nextPos);
					wallCoords = new Point((next.getX() + location.getX()) / 2, (next.getY() + location.getY()) / 2);
					try {
						Files.write(Path.of("log.txt"),
								("trying new wall " + wallCoords.getX() + " " + wallCoords.getY() + "\n").getBytes(),
								StandardOpenOption.APPEND);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (wallCoords.getY() % 2 == 0 && wallCoords.getY() >= 2) {
						wallCoords.decrementY();
						wallCoords.decrementY();
					} 

					counter++;
					if(counter > 6) {
						noWallmovement = true;
						move();
					}
				}
				wallAmount--;
				int y = (wallCoords.getX() + 2) / 2;
				int xPos = wallCoords.getY() / 2;
				char additional;
				if (wallCoords.getX() % 2 != 0) {
					// xPos--;
					additional = 'h';
				} else {
					// y++;
					additional = 'v';
				}
				char x = wabet.charAt(xPos);
				commandToOutput = "wall " + x + y + additional;
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
		System.out.println(commandToOutput);

	}

	public void move() {
		getPaths();
		// System.out.println("Trying to make movement");
		String commandToOutput = "";
		double wallChance = rand.nextDouble();
		int step = 2;
		boolean jump = false;
		noWallmovement = false;
		Point location = path.getLast();
		Point nextMovement = path.get(path.size() - step);
		while (!gameRunner.changePosition(nextMovement.getX(), nextMovement.getY())) {
			step += 1;
			nextMovement = path.size() - step < 0 ? path.get(0) : path.get(path.size() - step);
			jump = true;
		}
//			System.out.println("wallam" + wallAmount);
		Point opponent = opponentPath.getLast();
		// System.out.println(path);
		if ((nextMovement.getX() == opponent.getX() && nextMovement.getY() == opponent.getY())) {
			Point botPosition = path.getLast();
			Point opponentPosition = opponentPath.getLast();
			Point vector = new Point(opponentPosition.getX() - botPosition.getX(),
					opponentPosition.getY() - botPosition.getY());
			Point jumpPoint = new Point(opponentPosition.getX() + vector.getX(),
					opponentPosition.getY() + vector.getY());
			// System.out.println("jp " + jumpPoint);
			int y = (jumpPoint.getX() + 2) / 2;
			char x = abet.charAt(jumpPoint.getY() / 2);
			commandToOutput = "jump " + x + y;
		} else if (jump) {
			Point jumpPoint = path.get(path.size() - (3));
			// System.out.println("jp " + jumpPoint);
			int y = (jumpPoint.getX() + 2) / 2;
			char x = abet.charAt(jumpPoint.getY() / 2);
			commandToOutput = "jump " + x + y;

		} else {
			int y = (nextMovement.getX() + 2) / 2;
			char x = abet.charAt(nextMovement.getY() / 2);
			commandToOutput = "move " + x + y;
		}
	}
	
}
