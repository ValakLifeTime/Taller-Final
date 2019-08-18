import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Comando here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Comando extends Actor
{
    public String nombre;
    /**
     * Act - do whatever the Comando wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Comando(String n)
    {
        nombre = n;
    }
    public void act() 
    {
        // Add your action code here.
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
}
