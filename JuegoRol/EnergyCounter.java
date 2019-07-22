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
    int energia = 100 ;
    int speed = 4;
    public EnergyCounter()
    {
       setImage(new GreenfootImage("Energia: "+energia, 50, Color.BLACK, null));
       getImage().scale(getImage().getWidth()/2, getImage().getHeight()/2);
    }
    public void act() 
    {
        setImage(new GreenfootImage("Energia: "+energia, 50, Color.BLACK, null));
        getImage().scale(getImage().getWidth()/2, getImage().getHeight()/2);
        moving();
        maxEnergy();
        
    }    
    
    public void moving()
    {
     if(Greenfoot.isKeyDown("up"))  
     {
        
         setLocation(getX(), getY() - speed);   
        
     }
     if(Greenfoot.isKeyDown("down"))  
     {
         
         setLocation(getX(), getY() + speed);   
        
         
     }
     if(Greenfoot.isKeyDown("right"))  
     {
         setLocation(getX() + speed, getY());
         
     }
     if(Greenfoot.isKeyDown("left"))  
     {
         setLocation(getX() - speed, getY());
         
     }
    }
    public void addEnergy(int energy){
      energia = energia+energy  ;
    }
    public void maxEnergy(){
     if(energia >=160){
     energia = 160;    
        }
    }
    
}
