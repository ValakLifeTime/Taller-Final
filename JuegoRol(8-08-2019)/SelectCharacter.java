import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class SelectCharacter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectCharacter extends World
{
    //el atributo aux es utilizado para hacer un switch, mas abajo se explica.
    int aux;
    //estos objetos se le asignan dependiendo de la eleccion del jugador.
    PlayerChoice p1 = new PlayerChoice("Terran");
    PlayerChoice p2 = new PlayerChoice("Zerg");
    PlayerChoice p3 = new PlayerChoice("Protoss");
    /**
     * Constructor for objects of class SelectCharacter.
     * 
     */
    public SelectCharacter()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 
        //agrego objetos para que el jugador pueda verlos  y al darle click sobre él pueda elegirlo.
        addObject(p1, 272, 384);
        addObject(p2, 587, 384);
        addObject(p3, 880, 384);
        
    }
    public void act()
    {
     changeWorld(); 
    }
    /*
     * Este metodo sirve para cambiar el mundo una vez el jugador haga click sobre un objeto.
     */
    public void changeWorld()
    {
     if(Greenfoot.mouseClicked(p1))
      {
          //si el jugador da click sobre el objeto p1 (el cual es la raza Terran), aux va a valer 0, este valor me va a servir para un metodo que esta mas abajo.
          aux=0;
          /*este metodo setWorld sirve para crear un objeto de tipo mundo y cambiar de pantalla, notese que le doy dos parametros (*IMPORTANTE*: estos dos parametros son las razas que van
            a tener los jugadores) ,"Terran" pq el objeto al que se le fue dado click corresponde a la raza Terran, y el otro parametro es un metodo "azar()" el cual va a elegir una de las
           dos razas restantes al azar para asignarsela al otro jugador
          */
  
          Greenfoot. setWorld(new Koprulu("Terran",azar()));
          
        } 
      else  if(Greenfoot.mouseClicked(p2))
      {
          //aqui es similar a lo que comente anteriormente, aqui aux vale 1, esto es para luego utilizarlo en el metodo azar().
          aux=1;
          Greenfoot. setWorld(new Koprulu("Zerg", azar()));
          
        } 
       else if(Greenfoot.mouseClicked(p3))
      {
          //igual...
          aux=2;
          Greenfoot. setWorld(new Koprulu("Protoss", azar()));
        } 
        
    } 
    //este metodo va a elegir una raza al azar para el jugador 2 dentro de las dos que quedan despues de la eleccion del jugador 1.
    public String azar()
    {
     int a;
     String raza="";
     a = Greenfoot.getRandomNumber(2);
     if(aux==0)
     {
     switch(a)
     {
     
     case 0: raza = "Zerg"; break;
     case 1: raza = "Protoss"; break;
     }     
    }  
    else if(aux==1)
     {
     switch(a) 
     {   
     case 0: raza = "Terran"; break;
     case 1: raza = "Protoss"; break;
     }     
    } 
    else if(aux==2)
     {
     switch(a)
     {
     
     case 0: raza = "Zerg"; break;
     case 1: raza = "Terran"; break;
     }     
    } 
    return raza;
}
}
