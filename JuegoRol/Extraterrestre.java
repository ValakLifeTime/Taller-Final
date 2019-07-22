import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Extraterrestre here.
 * 
 * @author (Jose Silva) 
 * @version (1.0  14/07/2019)
 */
public class Extraterrestre extends Actor
{
    int speed = 4;
    public int energia;
    public String raza;
    /**
     * 
     */
    public Extraterrestre()
    {
        getImage().scale(getImage().getWidth()/2, getImage().getHeight()/2);
    }    
    /**
     * Act - do whatever the Extraterrestre wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        moving();
        corner();
    
    }

    /**
     * 
     */
    public void moving()
    {
     if(Greenfoot.isKeyDown("up"))  
     {
        
         setLocation(getX(), getY() - speed);   
        
     }
     if(Greenfoot.isKeyDown("down"))  
     {
         
         setLocation(getX(), getY() + speed);   
        
         
     }
     if(Greenfoot.isKeyDown("right"))  
     {
         setLocation(getX() + speed, getY());
         
     }
     if(Greenfoot.isKeyDown("left"))  
     {
         setLocation(getX() - speed, getY());
         
     }
    }  
      
    
    /**
     *  Metodo para identificar si se encuentra al limite del mundo
     */
    public void corner()
    {
       isAtEdge();
    }
       
}
