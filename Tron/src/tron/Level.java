/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * 
 * @author Zsombor
 */
public class Level{

    // each brick is 40x20, so there can be at most 20 bricks side by side
    // the last 10 rows will be empty, so there can be at most 20 rows of bricks
    private final int WIDTH = 20;
    private final int HEIGHT = 20;
    private ArrayList<Component> comps;
    private player p1;
    private player p2;

    public player[] getPlayers() {
        return new player[] {p1, p2};
    }

    public Level(String levelPath, ArrayList<String> data) throws IOException {
        comps = new ArrayList<>();
        loadLevel(levelPath);
        //Side note: Oszd el a frame magasság szélességét a HEIGHT és WIDTH-dal => tudsz fájlt csinálni
        p1 = new player(0,0,WIDTH,HEIGHT,data.get(1), data.get(0), true);
        comps.add(new Laser(p1.getX(), p1.getY(), WIDTH, HEIGHT, p1.getColor()));
        p2 = new player(39,29,WIDTH,HEIGHT,data.get(3), data.get(2), false);
        comps.add(new Laser(p2.getX(), p2.getY(), WIDTH, HEIGHT, p2.getColor()));
    }
    /**
     * Read a file that contains x:y sample blocks line by line
     * @param levelPath
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void loadLevel(String levelPath) throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(levelPath));
        String line;
        while ((line = br.readLine()) != null)
        {
            String[] sep = line.split(":");
            int x = Integer. parseInt(sep[0]);
            int y = Integer. parseInt(sep[1]);
            comps.add(new Wall(x,y,WIDTH,HEIGHT));
        }
    }
    /**
     * Tests all block which is covers the player. If it finds  cover kill the player, otherwise create a block 
     * @param p
     * @return 
     */
    public boolean test(player p)
    {
        int preX = p.getX();
        int preY = p.getY();
        int index = 0;
        boolean cover = false;
        p.move();
        
        for (int i = 0; i < comps.size() && !cover; i++)
        {
            cover = p.collides(comps.get(i));
            index = i;
        }
        if(cover)
        {
            p.setX(preX);
            p.setY(preY);
            p.lose();
            if(p.equals(p2))
            {
                player temp = p2;
                p2 = p1;
                p1 = temp;
            }
            int x = p1.getX()+p1.getVecX();
            int y = p1.getY()+p1.getVecY();
            if(p2.getX() == x && p2.getY() == y) { p2.lose(); }
        }
        else if(p.x != preX || p.y != preY)
        {
            Laser l = new Laser(p.x, p.y, WIDTH, HEIGHT, p.getColor());
            comps.add(l);
            
        }
        return cover;
    }
    /**
     * Draws the level
     * @param g 
     */
    public void draw(Graphics g)
    {
        for (Component b : comps)
        {
            b.draw(g);
        }
        p1.draw(g);
        p2.draw(g);
    }
    
    public player getP1() { return p1; }
    public player getP2() { return p2; }

}
