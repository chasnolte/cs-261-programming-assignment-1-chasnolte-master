import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * MyWorld places down four types of object that all interact with eachother on certain collision events.
 * 
 * @author (Chas Nolte) 
 * @version (February 11 2020)
 */
public class MyWorld extends World
{   
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public static boolean twoPlayer = false;
    public int curState;
    public int curRound = 1;
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        // Add player 1 in the center
        GreenfootImage bg = new GreenfootImage("startscreen.jpg");
        setBackground(bg);
        curState = 0;
    }
    public void act()
    {
        //Starts one player game
        if (curState == 0 && Greenfoot.isKeyDown("1"))
        {
            twoPlayer = false;
            GreenfootImage bg2 = new GreenfootImage("background.jpg");
            setBackground(bg2);
            Player1.isAlive = true;
            //Puts player 2 in the game if a two player game is selected
            if (twoPlayer == true){
                Player2.isAlive = true;
            }
            this.addObject(new Player1(), 50, 50);
            this.addObject(new Player2(), 750, 50);
            curState = 2;
        }
        //Starts two player game
        if (curState == 0 && Greenfoot.isKeyDown("2"))
        {
            twoPlayer = true;
            GreenfootImage bg2 = new GreenfootImage("background.jpg");
            setBackground(bg2);
            Player1.isAlive = true;
            if (twoPlayer == true){
                Player2.isAlive = true;
            }
            this.addObject(new Player1(), 50, 50);
            this.addObject(new Player2(), 750, 50);
            curState = 2;
        }
        //Playing Screen
        if (curState == 1)
        {
            //Creates a count of zombies and displays it on screen
            List<Zombie> zombieList = this.getObjects(Zombie.class);
            int zombieCount = zombieList.size();
            GreenfootImage bg2 = new GreenfootImage("background.jpg");
            setBackground(bg2);
            GreenfootImage bckgrnd = this.getBackground();
            bckgrnd.setColor(new Color(255,0,0));
            bckgrnd.drawString("Zombies Alive: "+zombieCount,25,575);
            bckgrnd.drawString("Round: "+curRound,25,25);
            //If all zombies have been killed, start the next round
            if(zombieCount == 0){
                curState += 1;
                curRound += 1;
            }
            //If both players are dead, display the lose screen
            if (Player1.isAlive == false && Player2.isAlive == false){
                curState = 3;
            }
            //If 10 rounds are completed, display win screen
            if (curRound == 11){
                curState = 4;
            }
        }
        //Adding zombies in
        if (curState == 2)
        {
           //Randomly place the zombies towards the left side of the screen at the start of each round
           for (int i=0; i<3+curRound; i++) {
               addObject(new Zombie(), Greenfoot.getRandomNumber(500), Greenfoot.getRandomNumber(800));
           }
            curState = 1;
        }
        //Lose Screen
        if (curState == 3)
        {
           //Remove all objects
           List objects = getObjects(null);
           removeObjects(objects);
           //Set the background to the lose screen
           GreenfootImage bg = new GreenfootImage("losescreen.jpg");
           setBackground(bg);
           //Return to the main screen if the spacebar is pressed
           if (Greenfoot.isKeyDown("SPACE")){
               GreenfootImage start = new GreenfootImage("startscreen.jpg");
               setBackground(start);
               curState = 0;
           }
        }
        //Win Screen
        if (curState == 4)
        {
           //Remove all objects
           List objects = getObjects(null);
           removeObjects(objects);
           //Set the background to the lose screen
           GreenfootImage bg = new GreenfootImage("winscreen.jpg");
           setBackground(bg);
           //Return to the main screen if the space bar is pressed
           if (Greenfoot.isKeyDown("SPACE")){
               GreenfootImage start = new GreenfootImage("startscreen.jpg");
               setBackground(start);
               curState = 0;
           }
        }
    }
}