import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Esta clase va a representar los depositos de gas, los cuales le brindan recursos a las especies.
 * 
 * @author (Jose Silva) 
 * @version (15/07/2019)
 */
public class GasDeposit extends Actor
{
    /**
     * Act - do whatever the GasDeposit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public GasDeposit()
    {
     GreenfootImage image = getImage();  
     image.scale(30, 30);   
    }  
    public GasDeposit(int CantMax)
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
