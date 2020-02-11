import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The player two actor is what the user controlls throughout the game, and has the ability to 
 * shoot bullets, and will be killed if it comes in contact with a zombie
 * @author Chas Nolte
 * @version February 11 2020
 */
public class Player2 extends Actor
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
    {   
        if (MyWorld.twoPlayer == false){
            isAlive = false;
            setImage("dead.png");
        }
        // Move forward
        if (Greenfoot.isKeyDown("d") && isAlive == true) {
            this.move(DIST_TO_MOVE);
            setImage("player2right.png");
            right = true;
        }
        
        // Move backward
        if (Greenfoot.isKeyDown("a") && isAlive == true) {
            this.move(-DIST_TO_MOVE);
            setImage("player2left.png");
            right = false;
        }
        
        // Turn counterclockwise
        if (Greenfoot.isKeyDown("w") && isAlive == true) {
            setLocation(getX(), getY()-DIST_TO_MOVE);
        }
        
        // Turn clockwise
        if (Greenfoot.isKeyDown("s") && isAlive == true) {
            setLocation(getX(), getY()+DIST_TO_MOVE);
        }
        // Resets the timer (used to stop the gun from firing constantly)
        if (timer > 0){
            timer-=1;
        }
        //If the player presses space and the player is alive, a bullet will spawn on their character

        if (timer == 0 && Greenfoot.isKeyDown("shift") && isAlive == true){
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
        if(isTouching(Zombie.class)){
            isAlive = false;
            setImage("dead.png");
        }
    }    
}
