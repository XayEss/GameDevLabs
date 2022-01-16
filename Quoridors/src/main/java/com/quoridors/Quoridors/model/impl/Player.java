package com.quoridors.Quoridors.model.impl;

import com.quoridors.Quoridors.model.PlayerI;

public class Player implements PlayerI {
	private int x;
	private int y;
	
	@Override
	public void move(int x, int y) {
		setX(x);
		setY(y);
	}

	@Override
	public void checkWalls() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

}
