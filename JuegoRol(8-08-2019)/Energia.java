import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Esta clase es la encargada de llevar el contador de nergia de los miembros de las razas (de momento 3/08/2019)
 * 
 * @author (José Silva) 
 * @version (17/07/2019)
 */
public class Energia extends Actor
{
    int energy = 100;
    int speed = 4;
    /**
     * 
     */
      
    public Energia()
    {
        setImage(new GreenfootImage("Energia: "+energy, 25, Color.BLACK, null));
    }
    public void act() 
    {
       setImage(new GreenfootImage("Energia: "+energy, 25, Color.BLACK, null));
       moving();
    }    
    // tiene el mismo metodo de movimiento de las razas para que esta se muestre arriba del personaje en movimiento.
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
}
