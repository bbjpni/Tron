/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron;

import javax.swing.ImageIcon;

/**
 *
 * @author Zsombor
 */
public class Laser extends Component{

    public Laser(int x, int y, int width, int height, String color) {
        super(x, y, width, height, new ImageIcon("data/"+color+".jpg").getImage(), color);
    }
    
    
    
    
    
}
