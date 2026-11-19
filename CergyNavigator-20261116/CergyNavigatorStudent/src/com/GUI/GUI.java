package com.GUI;

import javax.swing.*;

import java.awt.*;

public class GUI extends JFrame {
    JPanel jPanelNorth = new JPanel();
    JPanel jPanelSouth = new JPanel();
    SNCFStyleDisplay sncfStyleDisplay = new SNCFStyleDisplay();
    Indicator indicatorAlive = new Indicator();
    boolean alive=false;

    public GUI(){
        super("TP Thread feat SNCF");
        drawGUI();
        this.setVisible(true);
        Timer timer = new Timer(150,(t)->blinkAlive());
        timer.start();
        new Thread(sncfStyleDisplay).start();

    }

    private void drawGUI(){
        this.setSize(350,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jPanelNorth.add(sncfStyleDisplay);
        JButton refreshButton = new JButton("Refresh");
        jPanelSouth.setLayout(new FlowLayout());
        jPanelSouth.add(indicatorAlive);
        jPanelSouth.add(refreshButton);
        refreshButton.addActionListener((l)->refresh());
        this.getContentPane().add(jPanelNorth,BorderLayout.NORTH);
        this.getContentPane().add(jPanelSouth,BorderLayout.SOUTH);
        this.setResizable(false);
    }

    private void blinkAlive(){
        alive=!alive;
        indicatorAlive.setValue(alive);
    }

    private void refresh(){
            new Thread(sncfStyleDisplay).start();
            }
}