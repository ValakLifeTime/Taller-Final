import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;
import java.lang.*;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (Jose Silva) 
 * @version (1.2 5/08/2019)
 */
public class Koprulu extends World
{
    //los atributos p1Raza y p2Raza contienen la especie del jugador 1 y el jugador2 respectivamente, esta se les fue asignada durante la anterior pantalla de seleccion de personaje
    public String p1Raza,p2Raza;
    private long pauseTime = 0;
    //Contadores de los recursos, estos son globales, es decir, cuentan los recursos que agarran todos los constructores de un jugador
    Counter Gcont1 = new Counter();
    Counter Gcont2 = new Counter();
    Counter Gcont3 = new Counter();
    //contadores para contar la energia de cada miembro de la raza, puesto que hay una condicion y es que un miembro de una raza puede tener max 160 de energia.
    EnergyCounter cont1 = new EnergyCounter();
    EnergyCounter cont2 = new EnergyCounter();
    EnergyCounter cont3 = new EnergyCounter();
    
    HealthBar bar1; //Aqui los declaro, no se instancian todavia porque no hay la informacion suficiente para llenar los parametros del constructor, se instancian en los for del metodo
    HealthBar bar2; // generarExtraterrestres() 
    HealthBar bar3;
    
    private boolean bar1State = false, bar2State = false, bar3State = false;  //estos atributos es para saber si el objeto HealthBar esta instanciado o no.

    //Listas (se crean aca para poder acceder a ellas en las subclases de Actor)
    List<Deposito> listDeposits = new ArrayList<Deposito>();
    List<Protoss> listProtoss = new ArrayList<Protoss>();
    List<Terran> listTerran = new ArrayList<Terran>();
    List<Zerg> listZerg = new ArrayList<Zerg>();
    List<Comando> listComando = new ArrayList<Comando>();
    
    //de momento estos player1, y player2 no hacen nada (3/08/2019), la idea es emplearlos para hacer el juego por turnos.
    /*
     * actualizacion (5/08/2019) aca estoy creando a los dos jugadores, notese que el parametro p1Raza es la raza que selecciono el jugador 1, y p2Raza es la raza que le fue puesta al 
     jugador 2 al azar.
     */
    Player player1 = new Player(p1Raza, "player1");
    Player player2 = new Player(p2Raza, "player2");
    
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
        crearDepositos(3);
        crearDepositosDeGas(3);
        
        //metodo ya comentado mas abajo.
        crear();
        putCounters();
        putCounters2();
        //este objeto de momento lleva el recuento de los recursos (3/08/2019)
        //Comandos
        Comando comando = new Comando("Comando1");
        addObject(comando, 50 , 400);
        
   
        Comando comando2 = new Comando("Comando2");
        addObject(comando2, 1150 , 400);
        listComando.add(comando);
        listComando.add(comando2);
        
        //Creando Obstaculos
        crearObstaculos(8);
        
    }
    public void act()
    {
        openFile();
        agregarRegistro();
        cerrar();
     checkStatus();
     String key = Greenfoot.getKey();
      if ("p".equals(key)) ;
      if ("right".equals(key));   
        
    }  
    public void crear()
    {    
    generarExtraterrestres(p1Raza,3,110,120);
    generarExtraterrestres(p2Raza,3,1000,1020);
    } 
    public List<Comando> getComando()
    {
        return listComando;
    }
    public String getp1Raza()
    {
        return p1Raza;
    }
    public String getp2Raza()
    {
        return p2Raza;
    }
     public void crearDepositosDeGas(int cantidad)
    {
       for(int i=0; i<cantidad; i++)
        {
       GasDeposit g = new GasDeposit();
       //aqui utilizo el metodo getRandomNumberRange para agregar los objetos en cierto rango, esto para que no se generen en las esquinas del mundo y puedan ocasionar problemas.
       addObject(g, getRandomNumberRange(170,920),getRandomNumberRange(20,780));
        }   
    }
    public void crearObstaculos(int cantidad)
    {
       for(int i=0; i<cantidad; i++)
        {
       Obstaculo o = new Obstaculo();
       //aqui utilizo el metodo getRandomNumberRange para agregar los objetos en cierto rango, esto para que no se generen en las esquinas del mundo y puedan ocasionar problemas.
       addObject(o, getRandomNumberRange(170,920),getRandomNumberRange(20,780));
        }   
    }    
    public void crearDepositos(int num)
    {
     for(int i=0; i<num; i++)
        {
       Deposito d = new Deposito();
       
       addObject(d, getRandomNumberRange(170,920),getRandomNumberRange(20,780));
        } 
     listDeposits = getObjects(Deposito.class);   
    } 
    //aca creo los metodos "get" de las listas de objetos para poder interactuar con estos en las subclases de Actor.
    public List getDepositList()
    {
     return listDeposits;   
    }    
    public List getProtossList()
    {
     return listProtoss;   
    } 
    public List getZergList()
    {
     return listZerg;   
    } 
    public List getTerranList()
    {
     return listTerran;   
    } 
    /*El metodo generarExtraterrestres sirve para crear el mismo numero de miembros de una raza para los jugadores
     * el parametro String a corresponde a la raza que queremos crear,
     * el parametro int num corresponde al numero de miembros de una raza que queremos crear (el rol de cada miembro se pone al azar)
     * el paramtro int start corresponde desde que parte del mapa se van a generar los extraterrestres.
     * el parametro int end corresponde hasta que parte del mapa se van a generar los extraterrestres.
    
    */
       public void generarExtraterrestres(String raza, int num,int start, int end)
    {
        ArrayList<EnergyCounter> listCont1 = new ArrayList<EnergyCounter>();
        ArrayList<EnergyCounter> listCont2 = new ArrayList<EnergyCounter>();
        ArrayList<EnergyCounter> listCont3 = new ArrayList<EnergyCounter>();
        int energiaTotal=0;
        if(raza == "Protoss")
        {
        for(int i=0; i<num; i++)
        {
         //notese que le estoy enviando como parametro a Protoss "generarParametro()" ese metodo retorna un valor tipo string, y su funcion es generar un rol al azar.  
         EnergyCounter cont = new EnergyCounter();
         Protoss p = new Protoss(generarParametro(), cont, Gcont1);
         //el metodo getRandomNumber esta mas abajo del codigo y permite escoger un numero al azar que este entre start y end
         int x = getRandomNumberRange(start,end);
         //le doy al getrandomNumber el parametro getHeight que es la altura del mapa para que me genere un numero al azar de 0 a getHeight. 
         int y = Greenfoot.getRandomNumber(getHeight());
         addObject(p,x,y);
         energiaTotal += p.getEnergia();
         cont.setProtoss(p);
         
         listCont1.add(cont);
        } 
        bar1 = new HealthBar("Protoss","",energiaTotal,160*num);
        this.bar1State = true;
        for(int i=0; i<listCont1.size(); i++)
        {
         listCont1.get(i).putHealthBar(bar1);   
        }
        bar1.setState(true);
        //aca inicializo el valor del contador total de energia para los miembros de la raza Protoss
        //aca le envio la instancia total1 de la clase EnergiaTotal, esto es para que la clase EnergyCounter y EnergiaTotal puedan interactuar.
        //aca obtengo todas las instancias de la Clase Protoss y los guardo en una lista denominda listProtoss.
        listProtoss = getObjects(Protoss.class);
    }
        else if(raza == "Terran")
        {
        for(int i=0; i<num; i++)
        {
         //para estos es lo mismo, se puede basar segun lo que escribi en el for del primer if
         EnergyCounter cont2 = new EnergyCounter();
         Terran t = new Terran(generarParametro(), cont2, Gcont2);
         int x = getRandomNumberRange(start,end);
         int y = Greenfoot.getRandomNumber(getHeight());
         addObject(t,x,y);
         energiaTotal += t.getEnergia();
         cont2.setTerran(t);
         
         listCont2.add(cont2);
        } 
        bar2 = new HealthBar("Terran","",energiaTotal,160*num);
        this.bar2State = true;
        for(int i=0; i<listCont2.size(); i++)
        {
         listCont2.get(i).putHealthBar(bar2);   
        }
        bar2.setState(true);
        listTerran = getObjects(Terran.class);
    }
        else if(raza == "Zerg")
        {
        for(int i=0; i<num; i++)
        {
         EnergyCounter cont3 = new EnergyCounter();
         Zerg z = new Zerg(generarParametro(), cont3, Gcont3);
         int x = getRandomNumberRange(start,end);
         int y = Greenfoot.getRandomNumber(getHeight());
         addObject(z,x,y);
         energiaTotal += z.getEnergia();
         cont3.setZerg(z);
         listCont3.add(cont3);
        } 
        bar3 = new HealthBar("Zerg","",energiaTotal,160*num);
        this.bar3State = true;
        for(int i=0; i<listCont3.size(); i++)
        {
         listCont3.get(i).putHealthBar(bar3);   
        }
        bar3.setState(true);
        listZerg = getObjects(Zerg.class);
        
    }
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
   public int getRandomNumberRange(int start,int end)
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
     addObject(bar1,267,42);
    }
 
 else  if(p1Raza == "Zerg")
 {
     addObject(Gcont3,82,42);
     addObject(bar3,267,42);
    }
  else  if(p1Raza == "Terran")
 {
     addObject(Gcont2,82,42);
     addObject(bar2,267,42);
    }
}    
public void putCounters2()
{
 if(p2Raza == "Protoss")
 {
     addObject(Gcont1,924,42);
     addObject(bar1,1102,42);
    }
 
 else  if(p2Raza == "Zerg")
 {
     addObject(Gcont3,924,42);
     addObject(bar3,1102,42);
    }
  else  if(p2Raza == "Terran")
 {
     addObject(Gcont2,924,42);
     addObject(bar2,1102,42);
    }
}
 public boolean running()
    {
        long time = System.currentTimeMillis();
        return time > pauseTime;
    }
    public void pause(long delay)
    {
        long time = System.currentTimeMillis();
        pauseTime = time+delay;
    }
public void checkStatus()
    {
        
     if(this.bar1State) 
     {
      if(bar1.getValue()==0) 
        {
         pause(50000);
         Greenfoot.setWorld(new SelectCharacter("Protoss"));
        }
     }
     if (this.bar2State)
     {
        if(bar2.getValue() == 0)
        {
            pause(50000);
         Greenfoot.setWorld(new SelectCharacter("Zerg"));
        }
        }
     if (this.bar3State) 
     {
        if(bar3.getValue() == 0) 
        {
            pause(50000);
         Greenfoot.setWorld(new SelectCharacter("Terran"));
        }
        }
    }
    
    private Formatter x;
    private Scanner y;
    public void openFile()
    {
        
        try
        {
            x = new Formatter("game.txt");
            y = new Scanner(new File("Koprulu.java"));
        }
        catch (Exception e)
        {
            System.out.println("Hay un error");
        }
        
    }
    public void agregarRegistro()
    {
        while(y.hasNext())
        {
            String a = y.next();
            x.format("%s", a);
        }
        
    }
    public void cerrar()
    {
        x.close();
        y.close();
    }
 
}    

