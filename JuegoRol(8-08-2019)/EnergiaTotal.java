import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Esta clase es la encargada de llevar el contador de los recursos que son recogidos por los Constructores de cada especie.
 * 
 * @author (Jose Silva) 
 * @version (a version number or a date)
 */
public class EnergiaTotal extends Actor
{
    /**
     * Act - do whatever the EnergiaTotal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int recurso = 0 ;
    
    public EnergiaTotal()
    {
       setImage(new GreenfootImage("Recursos: "+recurso, 25, Color.BLACK, Color.WHITE));
    }
    public void act() 
    {
        setImage(new GreenfootImage("Recursos: "+recurso, 25, Color.BLACK, Color.WHITE));
        
    }
    //este metodo sirve para ir aumentando el contador y agregar el numero de recursos, notese que el parametro almacenar depende de que raza lo escoge.
    public void addRecursos(int almacenar)
    {
        recurso = recurso + almacenar;
    }
    
    
}

