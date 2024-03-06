/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Objects;

/**
 *
 * @author bbjpni
 */
abstract class Component {
    /**
     * The coordinates of the top left corner of the sprite
     */
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;
    protected String color;

    public Component(int x, int y, int width, int height, Image image, String color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
        this.color = color;
    }
    /**
     * Draws the component
     * @param g 
     */
    public void draw(Graphics g) {
        g.drawImage(image, x*height, y*width, width, height, null);
    }
    /**
     * Checks the cover with other component
     * @param other
     * @return 
     */
    public boolean collides(Component other) {
        Rectangle rect = new Rectangle(x*height, y*width, width, height);
        Rectangle otherRect = new Rectangle(other.x*other.height, other.y*other.width, other.width, other.height);        
        return rect.intersects(otherRect) && !other.equals(this);
    }
    /**
     * Retuns the hashCode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    /**
     * Retuns true if parameter is the object
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return false;
    }
    
    

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public String getColor() {
        return color;
    }
}
