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
public class player extends Component{
    private final String name;
    private int vecX;
    private int vecY;
    private int wins;
    private boolean isAlive;
    
    public player(int x, int y, int width, int height, String color, String name, boolean first) {
        super(x, y, width, height, new ImageIcon("data/"+color+".png").getImage(), color);
        this.name = name;
        this.isAlive = true;
        this.vecX = first ? 0 : 0;
        this.vecY = first ? 1 : -1;
        this.wins = 0;
    }
    /**
     * Moves the not dead player to a direction, if player is over the limit the player will dead
     */
    public void move()
    {
        //vecY == -1 && y > 0 && y < 600/height-1
        if(isAlive)
        {
            y += vecY;
            x += vecX;
            if(x < 0 || x > 800/width-1 ) { x += -1*vecX; lose(); }
            if(y < 0 || y > 600/height-1 ) { y += -1*vecY; lose(); }
        } 
    }

    public void setVecX(int vecX) {
        this.vecX = vecX;
    }

    public void setVecY(int vecY) {
        this.vecY = vecY;
    }
    
    public int getVecX()
    { return vecX; }
    
    public int getVecY()
    { return vecY; }

    public String getName() {
        return name;
    }
    
    public boolean getIsAlive()
    { return isAlive; }
    /**
     * Sets player to death and chnges  the image
     */
    public void lose()
    {
        image = new ImageIcon("data/"+color+"x.png").getImage();
        isAlive = false;
    }
    /**
     * Increases the number of wins
     */
    public void win()
    { wins++; }
    
    public int getWins()
    { return wins; }
    
}
