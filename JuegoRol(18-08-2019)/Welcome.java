import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Welcome here.
 * 
 * @author (Jose Silva) 
 * @version (version 1.0 28/07/2019)
 */
public class Welcome extends World
{
    private GreenfootSound music = new GreenfootSound("background.mp3");
    /**
     * Constructor for objects of class Welcome.
     * 
     */
    public Welcome()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1);
        //music.playLoop(); 
    }
    public void act()
    { 
      startMusic();
       if(Greenfoot.isKeyDown("enter"))
      {
         Greenfoot. setWorld(new SelectCharacter()); 
         music.stop();
        }  
    }
    public void startMusic()
    {
     music.playLoop();   
    }    
}
