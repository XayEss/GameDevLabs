package com.quoridors.Quoridors.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import com.quoridors.Quoridors.model.impl.GameEntity;

import javax.swing.*;

public class GUI extends JFrame implements GUIInterface {
    JPanel panel;
    GridLayout gridLayout = new GridLayout(17,17);
    JLabel[][] labels = new JLabel[17][17];
    Icon icon, icon2;

    public GUI() {
        super("Quoridors");
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBoard();
        setVisible(true);


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
                panel.add(label);
                labels[i][j] = label;
            }
        }
        add(panel);
        setVisible(true);
    }
}
