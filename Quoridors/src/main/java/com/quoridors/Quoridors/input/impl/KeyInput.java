package com.quoridors.Quoridors.input.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Base64;

import com.quoridors.Quoridors.gui.impl.GUI;
import com.quoridors.Quoridors.model.impl.GameRunner;

public class KeyInput implements KeyListener {
	private GameRunner runner;
	private GUI gui;
	
	public KeyInput(GameRunner runner, GUI gui) {
		super();
		this.runner = runner;
		this.gui = gui;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_R) {
			runner.resetGame();
		} else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gui.clearFrame();
			gui.startFrame();
			runner.resetGame();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		

	}

}
