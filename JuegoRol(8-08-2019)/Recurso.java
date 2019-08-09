import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Recurso here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Recurso extends Actor
{
    /**
     * Act - do whatever the Recurso wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int recurso = 0 ;
    
    public Recurso()
    {
       setImage(new GreenfootImage("Recursos: "+recurso, 50, Color.BLACK, Color.WHITE));
    }
    public void act() 
    {
        setImage(new GreenfootImage("Recursos: "+recurso, 50, Color.BLACK, Color.WHITE));
        
    }    
    public void addRecursos(int almacenar)
    {
        recurso = recurso + almacenar;
    }    
}
