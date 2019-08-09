import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (Jose Silva) 
 * @version (1.2 5/08/2019)
 */
public class Koprulu extends World
{
    //los atributos p1Raza y p2Raza contienen la especie del jugador 1 y el jugador2 respectivamente, esta se les fue asignada durante la anterior pantalla de seleccion de personaje
    String p1Raza,p2Raza;
    //notese que creo el contador cont aqui, y no en el constructor del mundo, esto para poder interactuar con el entre las demas clases.
    Counter Gcont1 = new Counter();
    Counter Gcont2 = new Counter();
    Counter Gcont3 = new Counter();
    EnergyCounter cont1 = new EnergyCounter();
    EnergyCounter cont2 = new EnergyCounter();
    EnergyCounter cont3 = new EnergyCounter();
    //de momento estos player1, y player2 no hacen nada (3/08/2019), la idea es emplearlos para hacer el juego por turnos.
    /*
     * actualizacion (5/08/2019) aca estoy creando a los dos jugadores, notese que el parametro p1Raza es la raza que selecciono el jugador 1, y p2Raza es la raza que le fue puesta al 
     jugador 2 al azar.
     */
    Player player1 = new Player(p1Raza);
    Player player2 = new Player(p2Raza);
    
    /**
     * Notese que para crear un mundo de tipo MyWorld hay que enviarle dos parametros, estos parametros correspoden a la raza de los dos jugadores, siendo p1 la raza del jugador1 y p2 la 
     raza del jugador 2.     
     */
       
    public Koprulu(String p1, String p2)
    {    
        
        super(1200, 800, 1); 
        this.p1Raza = p1;
        this.p2Raza = p2;
        //creando depositos y depositos de gas al azar en el mundo.
        addObject(new Deposito(), Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Deposito(), Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Deposito(), Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new GasDeposit(), Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new GasDeposit(), Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        //metodo ya comentado mas abajo.
        crear();
        putCounters();
        putCounters2();
        //este objeto de momento lleva el recuento de los recursos (3/08/2019)
        //Comandos
        Comando comando = new Comando();
        addObject(comando, 100 , 400);
        Comando comando2 = new Comando();
        addObject(comando2, 1100 , 400);
        //Creando Obstaculos
        addObject(new Obstaculo(),Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Obstaculo(),Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Obstaculo(),Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Obstaculo(),Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Obstaculo(),Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Obstaculo(),Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Obstaculo(),Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Obstaculo(),Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Obstaculo(),Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Obstaculo(),Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Obstaculo(),Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Obstaculo(),Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Obstaculo(),Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Obstaculo(),Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Obstaculo(),Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Obstaculo(),Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        addObject(new Obstaculo(),Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
       
    }
        
    public void crearDepositos(int num)
    {
     for(int i=0; i<num; i++)
        {
       Deposito d = new Deposito();
       addObject(new Deposito(), Greenfoot.getRandomNumber(1200),Greenfoot.getRandomNumber(800));
        } 
    } 
   
    /*El metodo generarExtraterrestres sirve para crear el mismo numero de miembros de una raza para los jugadores
     * el parametro String a corresponde a la raza que queremos crear,
     * el parametro int num corresponde al numero de miembros de una raza que queremos crear (el rol de cada miembro se pone al azar)
     * el paramtro int start corresponde desde que parte del mapa se van a generar los extraterrestres.
     * el parametro int end corresponde hasta que parte del mapa se van a generar los extraterrestres.
    
    */
    public void generarExtraterrestres(String raza, int num,int start, int end)
    {
        if(raza == "Protoss")
        {
        for(int i=0; i<num; i++)
        {
         //notese que le estoy enviando como parametro a Protoss "generarParametro()" ese metodo retorna un valor tipo string, y su funcion es generar un rol al azar.   
         Protoss p = new Protoss(generarParametro(), cont1, Gcont1);
         //el metodo getRandomNumber esta mas abajo del codigo y permite escoger un numero al azar que este entre start y end
         int x = getRandomNumber(start,end);
         //le doy al getrandomNumber el parametro getHeight que es la altura del mapa para que me genere un numero al azar de 0 a getHeight. 
         int y = Greenfoot.getRandomNumber(getHeight());
         addObject(p,x,y);
        } 
    }
        else if(raza == "Terran")
        {
        for(int i=0; i<num; i++)
        {
         //para estos es lo mismo, se puede basar segun lo que escribi en el for del primer if
         Terran t = new Terran(generarParametro(), cont2, Gcont2);
         int x = getRandomNumber(start,end);
         int y = Greenfoot.getRandomNumber(getHeight());
         addObject(t,x,y);
        } 
    }
        else if(raza == "Zerg")
        {
        for(int i=0; i<num; i++)
        {
         Zerg z = new Zerg(generarParametro(), cont3, Gcont3);
         int x = getRandomNumber(start,end);
         int y = Greenfoot.getRandomNumber(getHeight());
         addObject(z,x,y);
        }
    }
    }    
    /*En este metodo ya estoy creando el numero de extraterrestres que va a tenr cada jugador, el parametro p1Raza contiene el tipo de raza que el jugador 1 escogio previamente
     en la pantalla de seleccionar una raza, el numero 5 (de momento) significa que voy a crear 5 extraterrestres para cada jugador, el 0 y el getWidth()/2 significa que voy a estar generando
     sus personajes hasta la mitad del mapa por eso getWidth()/2,
     Para los personajes del segundo jugador se realizar de manera semejante, el parametro p2Raza contiene el tipo de raza del jugador 2, el cual fue determinado al azar (de momento 3/08/2019)
     , getWith()/2 y getWidth() significa que voy a estar generando sus extraterrestres de la mitad del mapa hasta el final del mismo.
     */
    public void crear()
    {    
    generarExtraterrestres(p1Raza,5,0,getWidth()/2);
    generarExtraterrestres(p2Raza,5,getWidth()/2,getWidth());
    }  
    /*Este metodo sirve para generar los roles al azar y asignarlos mientras estoy creando un numero de miembros de una especie.
     * 
     */
    public String generarParametro()
    {
     int opc = Greenfoot.getRandomNumber(3);
     String aux;
     switch(opc)
     {
     case 0: aux = "Guerrero";
     break;
     case 1: aux = "Constructor";
     break;
     case 2: aux="Medico";
     break;
     default: aux = "Guerrero"; break;
    }
    return aux;
}
/*este metodo tiene el nombre parecido a el metodo de greenfoot getRandomNumber (de hecho tiene el mismo nombre), pero ojo, que a este metodo hay que enviarle dos parametros, es como sobrecarga
 , este metodo lo que hace es segun el parametro inicial y final que yo le mande, me genera un numero dentro de ese rango, es bastante mejor que el metodo de greenfoot getRandomNumber que solo
 genera un numero de 0 al parametro que ud le de.
*/
public int getRandomNumber(int start,int end)
{
       int normal = Greenfoot.getRandomNumber(end-start+1);
       return normal+start;
}
public void combate()
{  
    List<Protoss> p = new ArrayList<Protoss>();
    p = getObjects(Protoss.class);
 
} 
public void putCounters()
{
 if(p1Raza == "Protoss")
 {
     addObject(Gcont1,82,42);
     addObject(cont1,200,42);
    }
 
 else  if(p1Raza == "Zerg")
 {
     addObject(Gcont3,82,42);
     addObject(cont3,200,42);
    }
  else  if(p1Raza == "Terran")
 {
     addObject(Gcont2,82,42);
     addObject(cont2,200,42);
    }
}    
public void putCounters2()
{
 if(p2Raza == "Protoss")
 {
     addObject(Gcont1,1000,42);
     addObject(cont1,1120,42);
    }
 
 else  if(p2Raza == "Zerg")
 {
     addObject(Gcont3,1000,42);
     addObject(cont3,1120,42);
    }
  else  if(p2Raza == "Terran")
 {
     addObject(Gcont2,1000,42);
     addObject(cont2,1120,42);
    }
} 
}    

