import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (Jose Silva) 
 * @version (1.0 14/07/2019)
 */
public class MyWorld extends World
{
    Counter cont = new Counter();
    EnergyCounter econt = new EnergyCounter();
    int x=Greenfoot.getRandomNumber(1200), y = Greenfoot.getRandomNumber(800);
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
       
    public MyWorld()
    {    
        
        super(1200, 900, 1); 
        addObject(new Deposito(), Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Deposito(), Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Deposito(), Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new GasDeposit(), Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new GasDeposit(), Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        prepare();
    }
    public Counter getCounter()
    {
     return cont;   
    } 
    public EnergyCounter getEcounter()
    {
     return econt;   
    }    
    public void prepare()
    {
     addObject(new Constructor("Protoss"),x,y);
     addObject(cont, 1050,50);  
     addObject(econt,x,y-20); 
    }    
       
}
