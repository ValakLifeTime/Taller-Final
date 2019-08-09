import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class All here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class All extends Actor
{
    /**
     * Act - do whatever the All wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    } 
    int horizScale;
    int vertiScale;
       
    public void imageScaleDown(int x, int y)
    {
        horizScale=x;
        vertiScale=y;
        getImage().scale(getImage().getWidth()/horizScale, getImage().getHeight()/vertiScale);
        
    }
    public void imageScaleUp(int x, int y)
    {
        horizScale=x;
        vertiScale=y;
        getImage().scale(getImage().getWidth()*horizScale, getImage().getHeight()*vertiScale);
        
    }
}
