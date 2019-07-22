import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Guerrero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Guerrero extends Extraterrestre
{
    /**
     * Act - do whatever the Guerrero wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public String getRaza(){
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
