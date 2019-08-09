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
    public EnergyCounter()
    {
       setImage(new GreenfootImage("Energia: "+energia, 25, Color.BLACK, null));
       
       this.deposit = deposit;
    }
    public void setEnergia(int energia)
    {
        this.energia = energia;
    }    
    public void act() 
    {
        setImage(new GreenfootImage("Energia: "+energia, 25, Color.BLACK, null));
        
        maxEnergy();
        
    }   
    public Deposito getDeposit()
    {
     return this.deposit;   
    }    
    public void addEnergy(int energy /* Deposito deposit*/){  
      energia = energia+energy  ;   
      //deposit.restCant(energy);
      
    }
    public int maxEnergy(){
     if(energia >=160){
     energia = 160;    
        }
        return energia;
    }
    
}
