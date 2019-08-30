import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnergyCounter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnergiaTotal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnergyCounter extends Actor
{
    /**
     * Act - do whatever the EnergiaTotal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int energia;
    int speed = 4;
    Deposito deposit;
    Protoss p;
    Terran t;
    Zerg z;
    HealthBar bar;
    public EnergyCounter()
    {
       setImage(new GreenfootImage("Energia: "+energia, 25, Color.BLACK, null));
       
       this.deposit = deposit;
    }
    public void setProtoss(Protoss protoss)
    {
     this.p = protoss;   
    }  
    public void setTerran(Terran terran)
    {
     this.t = terran;   
    } 
    public void setZerg(Zerg zerg)
    {
     this.z = zerg;   
    } 
    public void setEnergia(int energia)
    {
        this.energia = energia;
    }    
    public void act() 
    {
        setImage(new GreenfootImage("Energia: "+energia, 25, Color.BLACK, null));
       
        setEnergia(this.p.energia);
        setEnergia(this.t.energia);
        setEnergia(this.z.energia);
       
        
    }   
    public Deposito getDeposit()
    {
     return this.deposit;   
    }    
    public void putHealthBar(HealthBar bar)
    {
     this.bar = bar;   
    }    
    public void addEnergia(int energy ){    
      bar.add(energy);
      
    }
    public void restEnergia(int energy ){    
      bar.subtract(energy);
      
    }
 
    
}
