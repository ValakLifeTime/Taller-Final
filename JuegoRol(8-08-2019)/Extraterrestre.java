import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Extraterrestre here.
 * 
 * @author (Jose Silva) 
 * @version (1.0  14/07/2019)
 */
public class Extraterrestre extends Actor
{
    int speed = 2;
    public String rol;
    public int energia;
    public EnergyCounter contEnergia;
    public Counter contRecursos;
    public Player player;
    /**
     * 
     */
    public Extraterrestre()
    {
        getImage().scale(getImage().getWidth()/2, getImage().getHeight()/2);
        
    }    
     public String getRol(){
     return rol;   
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
            turn(45); move(speed);
        }
         if(choqueObstaculo())
        {
             turn(180); move(speed);
        }
        if(choqueBase())
        {
             turn(180); move(speed);
        }
        if(choqueRaza())
        {
            turn(180);
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

    public boolean choqueRaza()
    {
        if(isTouching(Extraterrestre.class)){
            return true;
        }
        else
        {
            return false;
        }
    }
        
       
}
