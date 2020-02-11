import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The left bullet actor is created when a player fires a gun, and will kill zombies on impact
 * 
 * @author Chas Nolte
 * @version February 11 2020
 */
public class LeftBullet extends Actor
{
    /**
     * Act - do whatever the LeftBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //Sets the location of the bullet to where the player is, and constantly moves left when fired
        setLocation(getX()-30, getY());
        //Bullet is killed if it hits the edge of the world
        if (isAtEdge())
        {
            getWorld().removeObject(this);
        }
    }    
}
