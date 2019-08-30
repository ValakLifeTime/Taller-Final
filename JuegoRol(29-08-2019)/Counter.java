import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Counter extends Actor
{
    int gas;
    Deposito deposit;
    /**
     * Act - do whatever the Counter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Counter()
    {
     setImage(new GreenfootImage("Gas: "+ gas, 25, Color.WHITE, null));   
     this.deposit = deposit;
    }   
    public int getGas()
    {
    return gas;
    } 
    public void setGas(int put)
    {
        gas = put; 
    }
    public void act() 
    {
        setImage(new GreenfootImage("Gas: "+ gas, 25, Color.WHITE, null)); 
    }    
    public void addGas(int almacenar)
    {
        this.gas = this.gas + almacenar;
    }
    public void quitarGas(int quitar)
    {
        gas = gas - quitar;
    }
}
