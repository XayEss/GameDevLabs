package com.quoridors.Quoridors;

import com.quoridors.Quoridors.input.impl.CommandInput;
import com.quoridors.Quoridors.model.PlayerI;
import com.quoridors.Quoridors.model.impl.EnumMap;
import com.quoridors.Quoridors.model.impl.GameEntity;
import com.quoridors.Quoridors.model.impl.GameRunner;
import com.quoridors.Quoridors.model.impl.PathFinder;
import com.quoridors.Quoridors.model.impl.Player;

public class QuoridorMain {

	public static void main(String[] args) {
		PathFinder pathFinder = new PathFinder();
		EnumMap eMap = new EnumMap(pathFinder);
		eMap.createMap(2);
		PlayerI pl1 = new Player();
		PlayerI pl2 = new Player();
		pathFinder.setPlayer1(pl1);
		pathFinder.setPlayer1(pl2);
		GameRunner gr = new GameRunner(pl1, pl2, eMap);
		CommandInput ci = new CommandInput(gr);
		ci.waitForCommand();
	}

}
