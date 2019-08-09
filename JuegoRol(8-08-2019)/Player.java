import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Esta clase va a ser la encargada de llevar el control de los turnos durante las partidas (de momento sin funcionar 3/08/2019)
 * 
 * @author (José Silva) 
 * @version (3/08/2019)
 */
public class Player extends Actor
{
    public boolean estado=true;
    public String raza;
    /**
     * Al constructor de Player se le envia el parametro raza, el cual es la raza que lleva el jugador.
     */
    public Player(String raza)
    {
        
    }    
    public void act() 
    {
        // Add your action code here.
    }    
    public boolean turno()
    {
     if(estado){
     return false;
        }
     else{
         return true;
        }    
    }
    public void setRaza(String a)
    {
        this.raza = a;
    } 
    public String getRaza()
    {
     return this.raza;  
    }    
}
