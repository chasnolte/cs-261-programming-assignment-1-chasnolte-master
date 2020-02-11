import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The player actor is what the user controlls throughout the game, and has the ability to 
 * shoot bullets, and will be killed if it comes in contact with a zombie
 * @author Chas Nolte
 * @version February 11 2020
 */
public class Player1 extends Actor
{
    private final int DIST_TO_MOVE = 7;
    private int timer = 0;
    private boolean right = true;
    public static boolean isAlive = true;
    /**
     * Act - the act method moves the Seahorse as follows:
     *   left cursor key - move the seahorse backward
     *   right cursor key - move the seahorse forward
     *   up cursor key - turn the seahorse counter clockwise DEG_TO_TURN
     *   down cursor key - turn the seahorse clockwise DEG_TO_TURN
     */
    public void act() 
    {   // Move forward
        if (Greenfoot.isKeyDown("right") && isAlive == true) {
            this.move(DIST_TO_MOVE);
            setImage("player1right.png");
            right = true;
        }
        
        // Move backward
        if (Greenfoot.isKeyDown("left") && isAlive == true) {
            this.move(-DIST_TO_MOVE);
            setImage("player1left.png");
            right = false;
        }
        
        // Move Up
        if (Greenfoot.isKeyDown("up")&& isAlive == true) {
            setLocation(getX(), getY()-DIST_TO_MOVE);
        }
        
        // Move Down
        if (Greenfoot.isKeyDown("down")&& isAlive == true) {
            setLocation(getX(), getY()+DIST_TO_MOVE);
        }
        // Resets the timer (used to stop the gun from firing constantly)
        if (timer > 0){
            timer-=1;
        }
        //If the player presses space and the player is alive, a bullet will spawn on their character
        if (timer == 0 && Greenfoot.isKeyDown("space") && isAlive == true){
            //If they are facing right, the right bullet will fire and vise versa
            if (right == true){
            getWorld().addObject(new Bullet(), getX(), getY());
            }
            if (right == false){
            getWorld().addObject(new LeftBullet(), getX(), getY());
            } 
            timer = 15;
        }
        //If the player dies, make the actor invisible to zombies and the user
        if (isAlive == false){
            setImage("dead.png");
        }
    }    
}
