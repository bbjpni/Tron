/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tron.Database.Datum;

/**
 *
 * @author Zsombor
 */
public class scoreGUI extends Window{
    private Database db;
    private JLabel label;
    /**
     * Creates scoreGUI and add listener to the button
     * @param p 
     */
    public scoreGUI(GUI p)
    {
        super(p);
        this.db = new Database(10, "data/highscores.txt");
        label = new JLabel();
        JButton b = new JButton("Back");
        add(label);
        add(b);
        b.addActionListener((ActionEvent e) -> {
            parent.back();
        });
        reload();
        
    }
    /**
     * Updates the text of label
     */
    public void reload()
    {
        String text = "<html>";
        ArrayList<Datum> list = db.getList();
        for (int i = 0; i < list.size(); i++)
        {
            text += (i+1)+". "+list.get(i).getName()+" ("+list.get(i).getWins()+" wins)<br/>";
        }
        text += "</html>";
        label.setText(text);
    }
    /**
     * Invites the function that write player to the file
     * @param p 
     */
    public void save(player p)
    {
        db.write(p);
    }
    
}
