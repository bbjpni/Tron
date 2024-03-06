/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 *
 * @author bbjpni
 */
public class PlayGround extends Window{
    private final int FPS = 5;
    private int levelNum = 0;
    private Level level;
    private Timer timer;
    private player p1;
    private player p2;
    private int waiting;
    private boolean isOver;
    private boolean pause;
    private ArrayList<String> data;
    /**
     * Describes the keys behaves and sets the parameters
     * @param data
     * @param p
     * @throws IOException 
     */
    public PlayGround(ArrayList<String> data, GUI p) throws IOException
    {
        super(p);
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p2.setVecX(1);
                p2.setVecY(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p2.setVecX(0);
                p2.setVecY(1);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "pressed up");
        this.getActionMap().put("pressed up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p2.setVecX(0);
                p2.setVecY(-1);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "pressed left");
        this.getActionMap().put("pressed left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p2.setVecX(-1);
                p2.setVecY(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed d");
        this.getActionMap().put("pressed d", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p1.setVecX(1);
                p1.setVecY(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("S"), "pressed s");
        this.getActionMap().put("pressed s", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p1.setVecX(0);
                p1.setVecY(1);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("W"), "pressed w");
        this.getActionMap().put("pressed w", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p1.setVecX(0);
                p1.setVecY(-1);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed a");
        this.getActionMap().put("pressed a", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p1.setVecX(-1);
                p1.setVecY(0);
            }
        });
        this.data = data;
        this.waiting = 3;
        level = new Level("data/lvl"+levelNum+".txt", data);
        p1 = level.getP1();
        p2 = level.getP2();
        isOver = false;
        pause = false;
        timer = new Timer(1000 / FPS, new NewFrameListener());
        timer.start();
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        level.draw(grphcs);
    }
    /**
     * Moves the player and check the state of game and waits and save
     */
    class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if(!pause){
            if(p1.getIsAlive() && p2.getIsAlive() && !isOver)
            {
                level.test(p1);
                level.test(p2);
                repaint();
                int death = (p1.getIsAlive() ? 0 : 1)+(p2.getIsAlive() ? 0 : 1);
                if(death == 1 && levelNum<3)
                {
                    levelNum++;
                    player winner = p1.getIsAlive() ? p1 : p2;
                    winner.win();
                    JOptionPane.showMessageDialog(parent.getFrame(), "Winner is "+winner.getColor()+". Step to the next level.", "Game > Tron",
                    JOptionPane.WARNING_MESSAGE);
                    isOver = levelNum > 2;
                }
                else if(death == 2)
                { JOptionPane.showMessageDialog(parent.getFrame(), "Nobody wins, the level will reload.", "Game > Tron",
                    JOptionPane.WARNING_MESSAGE); }
            }
            else if(!isOver){ waiting--; }
            if(waiting == 0 && levelNum < 3)
            { restart(); repaint(); waiting =3; }
            if(isOver && levelNum == 3)
            {
                player p = p1.getWins()>p2.getWins() ? p1 : p2;
                JOptionPane.showMessageDialog(parent.getFrame(), "Game Over! Winner is "+p.getName()+" with "+p.getColor()+" laser.", "Game > Tron",
                    JOptionPane.WARNING_MESSAGE);
                parent.save(p);
                levelNum = 0;
                isOver = false;
                pause();
            }
            }
            
        }

    }
    /**
     * Stop and continues the game by pauae
     */
    public void pause()
    { pause = !pause; }
    
    public boolean getPause()
    { return pause; }
    /**
     * Resstrts the current level of game
     */
    public void restart(){
        try {
            level = new Level("data/lvl"+levelNum+".txt", data);
            p1 = level.getP1();
            p2 = level.getP2();
            repaint();
        } catch (IOException ex) {
            Logger.getLogger(PlayGround.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
    }
    
    
}
