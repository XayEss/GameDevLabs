package com.quoridors.Quoridors.model;

public interface PlayerI {
	
	void move(int x, int y);
	void checkWalls();
	int getX();
	int getY();
	void setX(int x);
	void setY(int y);
}
