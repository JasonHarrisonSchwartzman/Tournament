

import java.util.*;
public class Player
{
    private String name;
    private int rating;
    private String rank;
    private double points;
    private double tieBreakRating;
    private double tieBreakPoints;
    public Player(String name, String rank)
    {
        this.name = name;
        this.rank = rank;
        this.points = 0;
        this.tieBreakRating = 0;
        this.tieBreakPoints = 0;
        this.rating = rankToRating(rank);
    } 
    public int rankToRating(String rank)
    {
        char border = rank.charAt(0);
        int division = Integer.parseInt(rank.substring(1,2));
        int lp = Integer.parseInt(rank.substring(3));
        int rating = 0;
        if (border == 'I')
        {
            rating = Math.abs(division - 4) * 100 + lp;
        }
        if (border == 'B')
        {
            rating = 400 + Math.abs(division - 4) * 100 + lp;


        }
        if (border == 'S')
        {
            rating = 800 + Math.abs(division - 4) * 100 + lp;
        }
        if (border == 'G')
        {
            rating = 1200 + Math.abs(division - 4) * 100 + lp;
        }
        if (border == 'P')
        {
            rating = 1600 + Math.abs(division - 4) * 100 + lp;
        }
        if (border == 'D')
        { 
            rating = 2000 + Math.abs(division - 4) * 100 + lp;
        }
        // I don't know how Master and above LP works, so I'm not doing it yet
        return rating;
    }
    public int getRating()
    {
        return this.rating;
    }
    public String getRank()
    {
        return this.rank;
    }
    public String getName()
    {
        return this.name;
    }
    public double getTieBreakRating()
    {
        return this.tieBreakRating;
    }
    public double getTieBreakPoints()
    {
        return this.tieBreakPoints;
    }
    public double getPoints()
    {
        return this.points;
    }
    public void addPoints(double numPoints)
    {
        points+= numPoints;
    }
    public void setTieBreakRating(double tieBreakRating)
    {
        this.tieBreakRating = tieBreakRating;
    }
    public void setTieBreakPoints(double tieBreakPoints)
    {
        this.tieBreakPoints = tieBreakPoints;
    }
    public String toString()
    {
        return getName();
    }
    public static void main(String[]args)
    {
        Player jason = new Player("Jason","G3 73");
        System.out.print(jason.getRating());
    }
}


