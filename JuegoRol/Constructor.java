import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Constructor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Constructor extends Extraterrestre
{
    int energia = 100;
    /**
     * Act - do whatever the Constructor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Constructor(String raza){
     this.raza = raza;   
    }    
    public String getRaza(){
     return raza;   
    } 
    public void act() 
    {
        maxEnergy();
        corner();
        collectDeposit();
        collectGasDeposit();
        moving();
    }  
    public void collectDeposit(){
         World world;
         world = getWorld();
        MyWorld mundo = (MyWorld)world;
        EnergyCounter econt = mundo.getEcounter();
     if(isTouching(Deposito.class))
     {
     if(getRaza() == "Protoss")
     {   
        econt.addEnergy(25); 
        }
     else if (getRaza() == "Terran")
     {    
        econt.addEnergy(20);   
        }
     else if(getRaza() == "Zerg")
     {        
        econt.addEnergy(15); 
        }    
        }
        removeTouching(Deposito.class);
    } 
    public void collectGasDeposit()
    {
        World world;
        world = getWorld();
        MyWorld mundo = (MyWorld)world;
        Counter cont = mundo.getCounter();
       if(isTouching(GasDeposit.class))
     {
     if(getRaza() == "Protoss")
     {   
       cont.addRecursos(35);
        }
     else if (getRaza() == "Terran")
     {    
        cont.addRecursos(40);
        }
     else if(getRaza() == "Zerg")
     {        
        cont.addRecursos(45); 
        }    
        }
        removeTouching(GasDeposit.class);
    }    
    public void maxEnergy(){
     if(energia >=160){
     energia = 160;    
        }
    }
}
