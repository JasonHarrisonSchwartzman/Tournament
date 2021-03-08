import java.util.*;
public class Tournament
{
    ArrayList<Player> players;
    int rounds;
    int currentRound;
    Player[][] groups;
    int [][] results;
    public Tournament(int rounds)
    {
        this.rounds = rounds;
        this.currentRound = 0;
        players = new ArrayList<Player>();
    }
    public void addPlayer(Player player)
    {
        players.add(player);
    }
    public void removePlayer(Player player)
    {
        players.remove(player);
    }
    public void sortPlayersByRating() // instead of creating an actual sorting method im lazy and the methods finds the highest value and puts it first in list and then removes it and continues this process
    { 
        ArrayList<Player> tempPlayers = players;
        int numPlayers = tempPlayers.size();
        players = new ArrayList<Player>();
        for (int j = 0; j < numPlayers; j++)
        {
            int max = tempPlayers.get(0).getRating();
            Player maxPlayer = tempPlayers.get(0);
            for (int i = 1; i < tempPlayers.size(); i ++)
            {
                if (tempPlayers.get(i).getRating() > max)
                {
                   maxPlayer = tempPlayers.get(i);
                   max = tempPlayers.get(i).getRating();
                }
            }
            players.add(maxPlayer);
            tempPlayers.remove(maxPlayer);
        }
        
    }
    public void sortPlayersByPoints() 
    {
        ArrayList<Player> tempPlayers = players;
        int numPlayers = tempPlayers.size();
        players = new ArrayList<Player>();
        for (int j = 0; j < numPlayers; j++)
        {
            double max = tempPlayers.get(0).getPoints();
            Player maxPlayer = tempPlayers.get(0);
            for (int i = 1; i < tempPlayers.size(); i ++)
            {
                if (tempPlayers.get(i).getPoints() > max)
                {
                    maxPlayer = tempPlayers.get(i);
                    max = tempPlayers.get(i).getPoints();
                }
                if (tempPlayers.get(i).getPoints() == max)
                {
                    if (tempPlayers.get(i).getTieBreakPoints() > maxPlayer.getTieBreakPoints())
                    {
                        maxPlayer = tempPlayers.get(i);
                        max = tempPlayers.get(i).getPoints();
                    }
                    if (tempPlayers.get(i).getTieBreakPoints() == maxPlayer.getTieBreakPoints())
                    {
                        if (tempPlayers.get(i).getTieBreakRating() > maxPlayer.getTieBreakRating())
                        {
                            maxPlayer = tempPlayers.get(i);
                            max = tempPlayers.get(i).getPoints();
                        }
                    }
                }
            }
            players.add(maxPlayer);
            tempPlayers.remove(maxPlayer);
      }
    }
    public void printPlayersByRating() // spacing is a bit wack but its ok im not doing it now
    {
        sortPlayersByRating();
        System.out.println("-----------------------------------------------------");
        System.out.println("| Rank |     Player Name     |  Rating   |   Rank   |");
        System.out.println("-----------------------------------------------------");
        for (int i = 0; i < players.size(); i++)
        {
            System.out.println("| " + (i + 1) + " | " + players.get(i).getName() + " | " + players.get(i).getRating() + " | " + players.get(i).getRank() + " | ");
            System.out.println("-----------------------------------------------------");
        }
    }
    public void printPlayersByPoints()
    {
      sortPlayersByPoints();
      System.out.println("-----------------------------------------------------");
      System.out.println("| Rank |     Player Name     |  Points   |   TieBreakPoints  | TieBreakRating | Rating");
      System.out.println("-----------------------------------------------------");
      for (int i = 0; i < players.size(); i++)
      {
          System.out.println("| " + (i + 1) + " | " + players.get(i).getName() + " | " + players.get(i).getPoints() + " | " + players.get(i).getTieBreakPoints() + " | " + players.get(i).getTieBreakRating() + " | " + players.get(i).getRating()+ " | ");
          System.out.println("-----------------------------------------------------");
      }
    }
    public void makeGroups()
    {
        Player fill = new Player("fill","I4 0");
        //round 1 right now it only works for 2 or 3 groups
        groups = new Player[players.size()/8][8];
        if (currentRound == 1)
        {
            for (int i = 0; i < players.size() / 8; i ++) //group 1, 2, 3, etc
            {
                for (int j = 0; j < 8; j++) //player 1, 2, 3, etc
                {
                    if (i == 0) 
                    {
                        if (j % 2 == 0) //odd num player when put into group (there is a player 0)
                        { 
                            groups[i][j] = players.get(i + groups.length * j); 
                        }
                        else
                        {
                            groups[i][j] = players.get(i + groups.length * (j + 1) - 1); 
                        }
                    }
                    else if (i == groups.length - 1)
                    {
                        if (j % 2 == 0)
                        {
                            groups[i][j] = players.get(i + groups.length * j);
                        }
                        else
                        {
                            groups[i][j] = players.get(i + groups.length * (j - 1) + 1);
                        }
                    }
                    else if (i == groups.length / 2)
                    {
                        groups[i][j] = players.get(i + groups.length * j);
                    }
                    else
                    {
                        groups[i][j] = fill;
                    }
                }
            }
            currentRound++;
        }
        else
        {
            sortPlayersByPoints();
            int x = 0;
            for (int i = 0; i < groups.length; i++)
            {
                for (int j = 0; j < groups[i].length; j++)
                {
                    groups[i][j] = players.get(x);
                    x++;
                }
            }
        }
       
    }
    public void enterResults()
    {
        Scanner sc = new Scanner(System.in);
        results = new int[groups.length][groups[0].length];
        for (int i = 0; i < groups.length; i++)
        {
            System.out.println("Group " + (char)(65 + i));
            for (int j = 0; j < groups[i].length; j++)
            {
                results[i][j] = sc.nextInt();
            }
            System.out.println();
        }
       // sc.close();
    }
    public void calculatePoints() //subject to change amount of points given
    {
        for (int i = 0; i < results.length; i++)
        {
            for (int j = 0; j < results[i].length; j++)
            {
                int num = 0;
                if (results[i][j] == 1)
                {
                    num = 12;
                }
                else if (results[i][j] == 2)
                {
                    num = 10;
                }
                else if (results[i][j] == 3)
                {
                    num = 8;
                }
                else if (results[i][j] == 4)
                {
                    num = 6;
                }
                else if (results[i][j] == 5)
                {
                    num = 4;
                }
                else if (results[i][j] == 6)
                {
                    num = 3;
                }
                else if (results[i][j] == 7)
                {
                    num = 2;
                }
                else if (results[i][j] == 8)
                {
                    num = 1;
                }
                if (num != 0)
                {
                    num = num + groups.length - i; //bonus points for higher groups subject to change
                    groups[i][j].addPoints(num);
                }
            }
        }
        /*
         * 1st: 12
         * 2nd: 10
         * 3rd: 8
         * 4th: 6
         * 5th: 4
         * 6th: 3
         * 7th: 2
         * 8th: 1
         *
         */
    }
    public void calculateTieBreaks()
    {
        for (int i = 0; i < groups.length; i++)
        {
            for (int j = 0; j < groups[i].length; j++)
            {
                double tieBreakRating = 0.0;
                double tieBreakPoints = 0.0;
                for (int x = 0; x < groups[i].length; x++)
                {
                    if (x != j)
                    {
                        tieBreakPoints+= groups[i][j].getPoints();
                        tieBreakRating+= groups[i][j].getRating();
                    }
                }
                groups[i][j].setTieBreakRating(tieBreakRating);
                groups[i][j].setTieBreakPoints(tieBreakPoints);
            }
        }
    }
    public void calculatePointsAndTieBreaks()
    {
        calculatePoints();
        calculateTieBreaks();
    }
    public void printGroups() 
    {
        for (int i = 0; i < groups.length; i++)
        {
            System.out.println("Group " + (char)(65 + i));
            for (int j = 0; j < groups[0].length; j++)
            {
                System.out.println(groups[i][j]);
            }
            System.out.println();
        }
    }
    public void printPlayerInGroup(int i, int j) // method only used for testing
    {
        System.out.println(groups[i][j]);
    }
    public static void main(String[]args)
    {
        Player jason = new Player("Jason","G3 73");
        Player mega = new Player("mega", "G4 90");
        Player linkdomc = new Player("linkdomc", "I4 1");
        Player warsmen7 = new Player("warsmen7", "P3 70");
        Player CloneCaster = new Player("CloneCaster", "G3 55");
        Player paincaster = new Player("paincaster", "B2 56");
        Player DeepExhale = new Player("DeepExhale", "B1 32");
        Player dennis = new Player("Dennis", "P1 99");
        Player andrew = new Player("Andrew", "S3 65");
        Player trent = new Player("Trent", "B1 86");
        Player lunan = new Player("Lunan","G4 1");
        Player frenchie = new Player("Frenchie", "G1 9");
        Player alwaysfocused = new Player("always focused", "B4 9");
        Player minecraft = new Player("minecraft","G4 38");
        Player fortnite = new Player("fortnite","S4 78");
        Player tft = new Player("tft","D1 99");
        Player A = new Player("A","B5 7");
        Player B = new Player("B","B5 8");
        Player C = new Player("C","B5 9");
        Player D = new Player("D","B5 10");
        Player E = new Player("E","B5 11");
        Player F = new Player("F","B5 12");
        Player G = new Player("G","B5 13");
        Player H = new Player("H","B5 14");
        Tournament tour = new Tournament(5);
        tour.addPlayer(jason);
        tour.addPlayer(mega);
        tour.addPlayer(linkdomc);
        tour.addPlayer(warsmen7);
        tour.addPlayer(CloneCaster);
        tour.addPlayer(paincaster);
        tour.addPlayer(DeepExhale);
        tour.addPlayer(dennis);
        tour.addPlayer(andrew);
        tour.addPlayer(trent);
        tour.addPlayer(lunan);
        tour.addPlayer(frenchie);
        tour.addPlayer(alwaysfocused);
        tour.addPlayer(minecraft);
        tour.addPlayer(fortnite);
        tour.addPlayer(tft);
        
        tour.addPlayer(A);
        tour.addPlayer(B);
        tour.addPlayer(C);
        tour.addPlayer(D);
        tour.addPlayer(E);
        tour.addPlayer(F);
        tour.addPlayer(G);
        tour.addPlayer(H); 
        
        tour.printPlayersByRating();
        //Round 1
        tour.makeGroups();
        tour.printGroups();
        tour.enterResults();
        tour.calculatePointsAndTieBreaks();
        tour.printPlayersByPoints();
        //Round 2
        tour.makeGroups();
        tour.printGroups();
        tour.enterResults();
        tour.calculatePointsAndTieBreaks();
        tour.printPlayersByPoints();
        //Round 3
        tour.makeGroups();
        tour.printGroups();
        tour.enterResults();
        tour.calculatePointsAndTieBreaks();
        tour.printPlayersByPoints();
        
    }
}
//Group 1: 1, 4, 5, 8, 9, 12, 13, 16
//Group 2: 2, 3, 6, 7, 10, 11 14, 15

//Group 1: 1, 6, 7, 12, 13, 18, 19, 24
//Group 2: 2, 5, 8, 11, 14, 17, 20, 23
//Group 3: 3, 4, 9, 10, 15, 16, 21, 22

//Group 1: 1, 8, 9, 16, 17, 24, 25, 32
//Group 2: 2, 7, 10, 15, 18, 23, 26, 31
//Group 3: 3, 6, 11, 14, 19, 22, 27, 30
//Group 4: 4, 5, 12, 13, 20, 21, 28, 29

//Group 1: 1, 10, 11, 20, 21, 30, 31, 40
//Group 2: 2, 9,  12, 19, 22, 29, 32, 39 
//Group 3: 3, 8,  13, 18, 23, 28, 33, 38
//Group 4: 4, 7,  14, 17, 24, 27, 34, 37
//Group 5: 5, 6,  15, 16, 25, 26, 35, 36

//Group 1: 1 12 13 24 25 36 37 48
//Group 2: 2 11 14 23 26 35 38 47
//Group 3: 3 10 15 22 27 34 39 46
//Group 4: 4 9  16 21 28 33 40 45
//Group 5: 5 8  17 20 29 32 41 44
//Group 6: 6 7  18 19 30 31 42 43

//Group 1: 1 14 15 28 29 42 43 56
//Group 2: 2 13 16 27 30 41 44 55
//Group 3: 3 12 17 26 31 40 45 54
//Group 4: 4 11 18 25 32 39 46 53
//Group 5: 5 10 19 24 33 38 47 52
//Group 6: 6 9  20 23 34 37 48 51
//Group 7: 7 8  21 22 35 36 49 50

//middle groups start at their number and add by total groups + 1
//first group starts at their number and increases by 
