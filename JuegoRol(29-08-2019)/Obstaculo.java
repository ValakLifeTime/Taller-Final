import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Esta clase van a hacer las paredes del mundo (de momento sin uso 3/08/2019)
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Obstaculo extends Actor
{
    /**
     * Act - do whatever the Obstaculo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Obstaculo()
    { 
     GreenfootImage image = getImage();  
     image.scale(30, 30);   
    }    
    public void act() 
    {
     GreenfootImage image = getImage();  
     image.scale(30, 30);
    }    
}
