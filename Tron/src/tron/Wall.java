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
public class Wall extends Component {
    
    public Wall(int x, int y, int width, int height) {
        super(x, y, width, height, new ImageIcon("data/block.png").getImage(), null);
    }
    
}
