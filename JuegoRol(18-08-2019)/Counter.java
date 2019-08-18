import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Counter extends Actor
{
    int recursos;
    Deposito deposit;
    /**
     * Act - do whatever the Counter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Counter()
    {
     setImage(new GreenfootImage("Recursos: "+ recursos, 25, Color.BLACK, null));   
     this.deposit = deposit;
    }   
    public int getRecursos()
    {
    return recursos;
    } 
    public void setRecursos(int put)
    {
        recursos = put; 
    }
    public void act() 
    {
        setImage(new GreenfootImage("Recursos: "+ recursos, 25, Color.BLACK, null)); 
    }    
    public void addRecursos(int almacenar)
    {
        this.recursos = this.recursos + almacenar;
    }
    public void quitarRecursos(int quitar)
    {
        recursos = recursos - quitar;
    }
}
