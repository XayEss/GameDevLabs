package com.quoridors.Quoridors.gui.impl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.quoridors.Quoridors.gui.GUIInterface;
import com.quoridors.Quoridors.input.impl.ButtonInput;
import com.quoridors.Quoridors.input.impl.KeyInput;
import com.quoridors.Quoridors.model.impl.GameEntity;
import com.quoridors.Quoridors.model.impl.GameRunner;

import javax.swing.*;

public class GUI extends JFrame implements GUIInterface {
    private JPanel panel;
    private GridLayout gridLayout = new GridLayout(17,17);
    private JLabel[][] labels = new JLabel[17][17];
    private GameRunner runner;
    private Icon icon, icon2;

    public GUI(GameRunner runner) {
        super("Quoridors");
        this.runner = runner;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setBoard();
        //startFrame();
        //setVisible(true);
    }
    
    

    @Override
    public void drawBoard(GameEntity[][] map) {
        icon = new ImageIcon("D:\\IntelegendIdeaProjects\\Quoridors\\images\\bear.gif");
        icon2 = new ImageIcon("D:\\IntelegendIdeaProjects\\Quoridors\\images\\turtle.png");
        for(int i=0; i<17; i++)
        {
            for(int j=0; j<17; j++)
            {
                if(map[i][j].equals(GameEntity.WallPlace))
                {
                    labels[i][j].setBackground(Color.gray);
                }
                if(map[i][j].equals(GameEntity.Player))
                {
                    labels[i][j].setBackground(Color.BLUE);
                    //labels[i][j].setIcon(icon);
                }
                if(map[i][j].equals(GameEntity.Space))
                {
                    labels[i][j].setBackground(Color.WHITE);
                }
                if(map[i][j].equals(GameEntity.Player2))
                {
                    labels[i][j].setBackground(Color.red);
                    //labels[i][j].setIcon(icon2);
                }
                if(map[i][j].equals(GameEntity.Wall))
                {
                    labels[i][j].setBackground(Color.ORANGE);
                }
            }
        }

    }
    public void setBoard()
    {
    	setSize(1000, 1000);
        panel = new JPanel();
        panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        panel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        panel.setLayout(gridLayout);
        for(int i=0; i<17; i++)
        {
            for(int j=0; j<17; j++)
            {
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setBackground(Color.cyan);
                label.setVisible(true);
                label.addMouseListener(new ButtonInput(i, j, runner));
                panel.add(label);
                labels[i][j] = label;
            }
        }
        addKeyListener(new KeyInput(runner, this));
        add(panel);
		setLocationRelativeTo(null);
        setVisible(true);
        requestFocus();
    }

	@Override
	public void drawBoard() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void startFrame() {
		//repaint();
        //revalidate();
		JButton button = new JButton("Start Game");
		panel = new JPanel();
		JCheckBox checkBox = new JCheckBox("Play with a bot");
		JLabel label = new JLabel("Quoridors");
		panel.setLayout(new FlowLayout());
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkBox.isSelected()) {
					runner.playWithABot();
				}
				clearFrame();
				setBoard();
				runner.initBoard();
				//runner.runGame();
			}
		});
		panel.add(label);
		panel.add(checkBox);
		panel.add(button);
		setSize(180,200);
		add(panel);
		setLocationRelativeTo(null);
		setVisible(true);
	}



	@Override
	public void printWinner(String player) {
		JOptionPane.showMessageDialog(this, "Player " + player + " has won");
		
	}



	@Override
	public void clearFrame() {
		remove(panel);
		repaint();
		revalidate();
		
	}
}