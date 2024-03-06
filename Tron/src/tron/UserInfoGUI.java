/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Zsombor
 */
public class UserInfoGUI extends Window
{
    private String name;
    private String color;
    private ArrayList<String> savedData;
    /**
     * Adds the fields of panel and add ActionListener to the button
     * @param p 
     */
    public UserInfoGUI(GUI p)
    {
        super(p);
        this.name = "";
        this.color = "";
        this.savedData = new ArrayList<>();
        String[] colors = new String[] {"red", "yellow", "blue"};
        JLabel nL = new JLabel("Player name:");
        JTextField n = new JTextField(16);
        JLabel cL = new JLabel("Player color:");
        JComboBox c = new JComboBox(colors);
        JLabel error = new JLabel("");
        error.setPreferredSize(new Dimension(250,20));
        error.setForeground(Color.RED);
        JButton b = new JButton("Submit");
        b.addActionListener((ActionEvent e) -> {
            error.setText("");
            error.setForeground(Color.RED);
            String s = n.getText().trim().toLowerCase();
            String ss = c.getSelectedItem().toString();
            if(name.compareTo(s) == 0 && name.length()>0){ error.setText(s+" is taken!"); }
            else if(s.length() < 3 || s.length() > 65 ){ error.setText("Name must be beetween 3 and 64 character!"); }
            else if(ss == color){ error.setText("Selected color is taken!"); }
            else
            {
                boolean first = savedData.size()/2==0;
                this.name = s;
                this.color = ss;
                savedData.add(name);
                savedData.add(color);
                error.setForeground(Color.GREEN);
                error.setText("Data are saved!");
                if(savedData.size()/2==2)
                {
                    error.setText("");
                    parent.feedback(savedData);
                    savedData = new ArrayList<>();
                    this.name = "";
                    this.color = "";
                }
                n.setText("");
            }
        });
        add(nL);
        add(n);
        add(cL);
        add(c);
        add(b);
        add(error);
    }
    
}
