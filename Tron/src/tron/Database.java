/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zsombor
 */
public class Database {
    private int max;
    private ArrayList<Datum> data;
    private String file;
    /**
     * Reads a file and fills a list
     * @param max
     * @param file 
     */
    public Database(int max, String file){
        this.max = max;
        this.file = file;
        try{
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        data = new ArrayList<>();
        while ((line = br.readLine()) != null)
        {
            String[] sep = line.split(" ");
            int n = Integer.parseInt(sep[sep.length-1]);
            String N = "";
            for (int i = 0; i < sep.length-1; i++)
            {
                N+=sep[i]+" ";
            }
            N = N.substring(0, N.length()-1);
            data.add(new Datum(N, n));
        }
        br.close();
        } catch (IOException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        sortHighScores();
    }
    /**
     * Sorts the list of data
     */
    private void sortHighScores() {
        Collections.sort(data, (Datum t, Datum t1) -> t1.getWins() - t.getWins());
    }
    /**
     * Searches the player's name in list if find it then modifies the wins wtth 1, else add player's name with 1 win. Saves in file. Finally sort the list
     * @param p 
     */
    public void write(player p)
    {
        int index = -1;
        for (int i = 0; i < data.size() && index < 0; i++)
        {
            index = data.get(i).getName().compareTo(p.getName()) == 0 ? i : -1;
        }
        if (index < 0)
        {
            data.add(new Datum(p.getName(), 1));
        }
        else
        {
            Datum d = data.get(index);
            d = new Datum(d.getName(), d.getWins()+1);
            data.set(index, d);
        }
        
        try {
            FileWriter fw;
            fw = new FileWriter(file);
        for (int i = 0; i < data.size(); i++)
        {fw.write(data.get(i)+"\n");}
        fw.close();
        } catch (IOException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        sortHighScores();
    }
    /**
     * Returns a list that size is less or match with max value
     * @return 
     */
    public ArrayList<Datum> getList()
    {
        ArrayList<Datum> list = new ArrayList();
        int n = data.size()<max ? data.size():max;
        for (int i = 0; i < n; i++)
        {
            list.add(data.get(i));
        }
        return list;
    }
    
    public class Datum
    {
        private String name; 
        private int wins;

        public Datum(String name, int wins) {
            this.name = name;
            this.wins = wins;
        }

        public int getWins() {
            return wins;
        }
        
        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name + " " + wins;
        }
    }
}
