package com.quoridors.Quoridors.input.impl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.quoridors.Quoridors.input.Input;
import com.quoridors.Quoridors.model.impl.GameRunner;

public class ButtonInput implements Input, MouseListener {
	private int x;
	private int y;
	private GameRunner runner;

	
	public ButtonInput(int x, int y, GameRunner runner) {
		super();
		this.x = x;
		this.y = y;
		this.runner = runner;
	}

	@Override
	public void movePlayer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void placeHWall() {
		// TODO Auto-generated method stub

	}

	@Override
	public void placeVWall() {
		// TODO Auto-generated method stub

	}

	@Override
	public void waitForCommand() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(x % 2 != 0 || y % 2 != 0) {
			runner.placeWall(x, y);
		} else {
			runner.changePosition(x, y);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
