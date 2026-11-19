package com.GUI;

import com.STIFAPI.STIFAPIRequest;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SNCFStyleDisplay extends JComponent implements Runnable{
    private int width = 250;
    private int height = 40;
    private int numberOfTrain = 8;
    private ArrayList<String> information;
    private STIFAPIRequest stifapiRequest;

    public SNCFStyleDisplay() {
        stifapiRequest = new STIFAPIRequest();
        information = new ArrayList<>();
    }

    @Override
    public void run(){
        this.refresh();
    }
    public Dimension getPreferredSize() {
        return new Dimension(width, height*numberOfTrain);
    }
    public void refresh(){
        information = stifapiRequest.getInformation();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color[] colors = {Color.BLUE,Color.BLACK};

        for (int i=0;i<numberOfTrain;i++){
            g.setColor(colors[i%2]);
            g.fillRect(0,i*height,width,height);
            try{
                g.setColor(Color.WHITE);
                g.setFont(g.getFont().deriveFont(Font.BOLD));
                g.drawString(information.get(i),5,(int)(height*i+height/2+g.getFont().getSize()/2));
            }
            catch (Exception e){
                // Empty or insufficient information
            }
        }
    }
}