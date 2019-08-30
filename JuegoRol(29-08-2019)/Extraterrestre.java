import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Extraterrestre here.
 * 
 * @author (Jose Silva) 
 * @version (1.0  14/07/2019)
 */
public class Extraterrestre extends Actor
{
    protected String rol;
    protected int energia, gas,cristales, capacidadCuracion = 0,speed = 2;
    protected boolean lifeState = true, controlState = true;
    public EnergyCounter contEnergia;
    public Counter contRecursos;
    /**
     * 
     */
    public Extraterrestre()
    {
        getImage().scale(getImage().getWidth()/2, getImage().getHeight()/2);
        
    } 
    public EnergyCounter getContador()
    {
     return contEnergia;   
    }    
    public Counter getcontRecursos()
    {
     return contRecursos;   
    }  
    public boolean getLifeState(){
     return lifeState;   
    }
    public boolean getControlState(){
     return controlState;   
    }
     public String getRol(){
     return rol;   
    } 
     public int getEnergia(){
     return energia;   
    }  
    public int getCristales()
    {
     return cristales;   
    }
    public int getGas()
    {
     return gas;   
    }
     public int getCapacidadCuracion()
    {
     return capacidadCuracion;   
    }
    /**
     * Act - do whatever the Extraterrestre wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        moving();
        corner();
    }   
    /**
     * metodo para moverse con las teclas.
     */
   public void moving()
    {
     move(speed);
     if(Greenfoot.getRandomNumber(100)<20)
     {
      turn(Greenfoot.getRandomNumber(40)-20);
            
     }
     if(corner())
     {
      turn(180); move(20);
     }
     if(choqueObstaculo())
     {
      turn(180); move(20);
     }
    }  
      
    
    /**
     *  Metodo para identificar si se encuentra al limite del mundo
     */
   public boolean corner()
    {
       return isAtEdge();
    }  
    public boolean choqueObstaculo()
    {
        if(isTouching(Obstaculo.class)){
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean choqueBase()
    {
        if(isTouching(Comando.class)){
            return true;
        }
        else
        {
            return false;
        }
    }

    
        
       
}
