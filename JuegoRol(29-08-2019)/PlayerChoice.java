import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Esta clase es utilizada en la pantalla de seleccion de personaje, sirve para mostrar las razas del juego
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerChoice extends Actor
{
    public String parametro;
    GreenfootImage myimage;
    public int aux;
    /**
     * Act - do whatever the PlayerChoice wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public PlayerChoice(String p)
    {
     this.parametro = p;   
     if(p == "Terran")
     {
         GreenfootImage image = getImage(); 
         setImage("TerranGuerrero.png");
        }    
     else if(p == "Zerg")
     {
         setImage("ZergConstructor.png");
        }  
     else if(p == "Protoss")
     {
         setImage("ProtossGuerrero.png");
        }    
     GreenfootImage image = getImage();  
     image.scale(100, 100);
        
    }    
    public void act() 
    {
        // Add your action code here.
    } 
    public int getAux()
    {
     return this.aux;   
    }    
    //metodo incompleto, la idea es colocarle la imagen para que represente a la raza correspondiente
    public void setImage()
    {
     if(parametro == "zerg")
     {
      if(Greenfoot.mouseClicked(this))
      {
         aux=0; 
        }  
        }  
     else if(parametro == "Protoss")
     {
         if(Greenfoot.mouseClicked(this))
      {
         aux=1; 
        } 
        }
     else if(parametro == "Terran")
    {
       if(Greenfoot.mouseClicked(this))
      {
         aux=2; 
        }  
    }
    }    
}
