import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Counter extends Actor
{
    int recursos=0;
    /**
     * Act - do whatever the Counter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Counter()
    {
     setImage(new GreenfootImage("Recursos: "+ recursos, 50, Color.BLACK, null));    
    }   
    public int getRecursos()
    {
    return recursos;
    }    
    public void act() 
    {
        setImage(new GreenfootImage("Recursos: "+ recursos, 50, Color.BLACK, null)); 
        
    }    
    public void addRecursos(int almacenar)
    {
        recursos = recursos + almacenar;
    }
}
