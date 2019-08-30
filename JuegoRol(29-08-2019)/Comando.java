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
    public HealthBar bar;
    /**
     * Act - do whatever the Comando wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Comando(String n, HealthBar bar)
    {
        nombre = n;
        this.bar = bar;
     GreenfootImage image = getImage();  
     image.scale(30, 30);
    }
     
    public Comando(String nombre)
    {
     this.nombre = nombre;
     GreenfootImage image = getImage();  
     image.scale(30, 30);
    }
    public String getNombre()
    {
     return nombre;   
    }
    public HealthBar getBar()
    {
     return bar;   
    }    
    public void act() 
    {
     GreenfootImage image = getImage();  
     image.scale(30, 30);
    }
    
}
