package tron;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bbjpni
 */
public class GUI
{
    private JFrame frame;
    private JFrame uframe;
    private JFrame sframe;
    private PlayGround pg;
    private UserInfoGUI ui;
    private scoreGUI si;
    /**
     * Create a GUI with frames and function of menu
     * @throws IOException 
     */
    public GUI() throws IOException {
        frame = new JFrame("Tron | BBJPNI");
        uframe = new JFrame("Players' data");
        sframe = new JFrame("Top 10");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        uframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sframe.setDefaultCloseOperation(0);
        ui = new UserInfoGUI(this);
        si = new scoreGUI(this);
        uframe.getContentPane().add(ui);
        sframe.getContentPane().add(si);
        frame.setPreferredSize(new Dimension(814, 660));
        sframe.setPreferredSize(new Dimension(300, 220));
        uframe.setPreferredSize(new Dimension(300, 200));
        frame.setResizable(false);
        uframe.setResizable(false);
        sframe.setResizable(false);
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenuItem restart = new JMenuItem("Restart");
        JMenuItem neu = new JMenuItem("New");
        JMenuItem topItem = new JMenuItem("Top 10");
        menuBar.add(neu);
        menuBar.add(restart);
        menuBar.add(topItem);
        topItem.addActionListener((ActionEvent e) -> {
            if (!pg.getPause()) { pg.pause(); }
            //frame.setVisible(false);
            sframe.setVisible(true);
            si.reload();
        });
        restart.addActionListener((ActionEvent e) -> {
            if (pg.getPause()) { pg.pause(); }
            pg.restart();
        });
        neu.addActionListener((ActionEvent e) -> {
            pg.pause();
            frame.getContentPane().remove(pg);
            ui = new UserInfoGUI(this);
            frame.setVisible(false);
            sframe.setVisible(false);
            uframe.setVisible(true);
        });
        frame.pack();
        frame.setVisible(false);
        sframe.pack();
        sframe.setVisible(false);
        uframe.pack();
        uframe.setVisible(true);
    }
    /**
     * Forwards user data to PlayGround, closes and opens frames
     * @param data 
     */
    public void feedback(ArrayList<String> data)
    {
        uframe.setVisible(false);
        try {
            pg = new PlayGround(data, this);
        } catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
        frame.getContentPane().add(pg);
                    frame.setVisible(true);
    }
    /**
     * Saves the player in file
     * @param p 
     */
    public void save(player p)
    {
            si.save(p);
    }
    /**
     * Closes top 10 frame and open the game
     */
    public void back()
    {
        sframe.setVisible(false);
        frame.setVisible(true);
        pg.pause();
    }

    public JFrame getFrame() {
        return frame;
    }
}
