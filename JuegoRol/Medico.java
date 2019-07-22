import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Medico here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Medico extends Extraterrestre
{
    int energia = 120;
    /**
     * Act - do whatever the Medico wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Medico(String raza)
    {
     this.raza = raza;   
    }
        
    public String getRaza()
    {
     return raza;   
    } 
    public void act() 
    {
        maxEnergy();
        collectDeposit();
        moving();
    }
   public void collectDeposit(){
     if(isTouching(Deposito.class))
     {
     if(getRaza() == "Protoss")
     {   
        energia += 25; 
        }
     else if (getRaza() == "Terran")
     {    
        energia += 20;  
        }
     else if(getRaza() == "Zerg")
     {        
        energia += 15; 
        }    
        }
        removeTouching(Deposito.class);
    } 
    public void maxEnergy(){
     if(energia >=160){
     energia = 160;    
        }
    }
}
