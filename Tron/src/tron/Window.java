/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron;

import javax.swing.JPanel;

/**
 *
 * @author Zsombor
 */
abstract class Window extends JPanel{
    protected GUI parent;

    public Window(GUI parent)
    {
        this.parent = parent;
    }
    
    
}
