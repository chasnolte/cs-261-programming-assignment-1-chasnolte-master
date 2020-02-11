import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * The zombie actor is the enemy of the player, and will follow the nearest player if
 * in a two player game. If it comes in contact with a player, the player will die.
 * @author Chas Nolte
 * @version February 11 2020
 */
public class Zombie extends Actor
{
    /**
     * Act - do whatever the Zombie wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int height;
    private int width;
    public boolean twoPlayer = false;
    private int timer = 0;
  
    public void act() 
    {
        Actor player1 = (Player1)getWorld().getObjects(Player1.class).get(0);
        Actor player2 = (Player2)getWorld().getObjects(Player2.class).get(0);
        
        //Finds length from zombie to player 1
        double xLengthPlayer1 = Math.sqrt(Math.abs(this.getX()-player1.getX()));
        double yLengthPlayer1 = Math.sqrt(Math.abs(this.getY()-player1.getY()));
        double player1Length = Math.sqrt((xLengthPlayer1*xLengthPlayer1)+(yLengthPlayer1*yLengthPlayer1));
        //Finds length from zombie to player 2
        double xLengthPlayer2 = Math.sqrt(Math.abs(this.getX()-player2.getX()));
        double yLengthPlayer2 = Math.sqrt(Math.abs(this.getY()-player2.getY()));
        double player2Length = Math.sqrt((xLengthPlayer2*xLengthPlayer2)+(yLengthPlayer2*yLengthPlayer2));
        
        //turns towards the closest player if both players are alive
        if (Player1.isAlive==true && Player2.isAlive==true){
            //If player 2 is closer to the zombie, it will follow player 2
            if (player1Length > player2Length){
                turnTowards(player2.getX(), player2.getY());
            }
            //If player 1 is closer, it will follow player 1
            else{
                turnTowards(player1.getX(), player1.getY());
            }
        }
        // follow player 2
        if (Player1.isAlive==false && Player2.isAlive==true){
            turnTowards(player2.getX(), player2.getY());
        }
        // follow player 1
        if (Player1.isAlive==true && Player2.isAlive==false){
            turnTowards(player1.getX(), player1.getY());
        }
        //If it gets hit by a bullet, the zombie dies
        if (isTouching(Bullet.class))
        {
            getWorld().removeObject(this);
        }
        else if (isTouching(LeftBullet.class))
        {
            getWorld().removeObject(this);
        }
        // turn towards the player
        move(2);
        //If the zombie gets within 7 pixels of either player, they will die
        if (player1Length < 7){
            Player1.isAlive = false;
        }
        if (player2Length < 7){
            Player2.isAlive = false;
        }
    }    
}
