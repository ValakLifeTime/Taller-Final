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
 public String p1Raza,p2Raza,raceWin="";
 private boolean bar1State = false, bar2State = false, bar3State = false;  //estos atributos es para saber si el objeto HealthBar esta instanciado o no.
 //Contadores de los recursos, estos son globales, es decir, cuentan los recursos que agarran todos los constructores de un jugador
 int iteraciones=0;
 final int maxIteraciones =1000;
 HealthBar barIter = new HealthBar("Iteraciones","",0,maxIteraciones,Color.ORANGE);
 List<Deposito> listDeposits ;
 List<Protoss> listProtoss ;
 List<Terran> listTerran ;
 List<Zerg> listZerg;
 List<Comando> listComando ;
 List<HealthCenter> listHealthCenter;
 Counter Gcont1 ;
 Counter Gcont2;
 Counter Gcont3;
 EnergyCounter cont1;
 EnergyCounter cont2;
 EnergyCounter cont3;
    
 HealthBar bar1; //Aqui los declaro, no se instancian todavia porque no hay la informacion suficiente para llenar los parametros del constructor, se instancian en los for del metodo
 HealthBar bar2; // generarExtraterrestres() 
 HealthBar bar3;
 HealthBar barc1;
 HealthBar barc2;
public Koprulu(String p1, String p2,int iteraciones,boolean isLoaded)
{    
 super(1200, 800, 1); 
 this.p1Raza = p1;
 this.p2Raza = p2;     
 this.iteraciones = iteraciones; 
 if(!isLoaded){
     newWorld();
    }
}
public void prepare()
{
 
}    
public void newWorld()
{
 Gcont1 = new Counter();
 Gcont2 = new Counter();
 Gcont3 = new Counter();
 //contadores para contar la energia de cada miembro de la raza, puesto que hay una condicion y es que un miembro de una raza puede tener max 160 de energia.
 EnergyCounter cont1 = new EnergyCounter();
 EnergyCounter cont2 = new EnergyCounter();
 EnergyCounter cont3 = new EnergyCounter();
    
 HealthBar bar1; //Aqui los declaro, no se instancian todavia porque no hay la informacion suficiente para llenar los parametros del constructor, se instancian en los for del metodo
 HealthBar bar2; // generarExtraterrestres() 
 HealthBar bar3;
 HealthBar barc1;
 HealthBar barc2;
 
 //Listas (se crean aca para poder acceder a ellas en las subclases de Actor)
 listDeposits = new ArrayList<Deposito>();
 listProtoss = new ArrayList<Protoss>();
 listTerran = new ArrayList<Terran>();
 listZerg = new ArrayList<Zerg>();
 listComando = new ArrayList<Comando>();
 listHealthCenter = new ArrayList<HealthCenter>();     
 addObject(barIter, 615, 773);   
 creating();
 putCounters();
 putCounters2();
 barc1 = new HealthBar("Cristales","",0,500,Color.CYAN);
 barc1.setBarHeight(10);
 barc1.setBarWidth(40);
 Comando comando = new Comando("Comando1",barc1);
 addObject(comando, 113 , 525);
 addObject(barc1, 113, 500);
 barc2 = new HealthBar("Cristales","",0,500,Color.CYAN);
 barc2.setBarHeight(10);
 barc2.setBarWidth(40);
 Comando comando2 = new Comando("Comando2",barc2);
 addObject(comando2, 1076 , 257);
 addObject(barc2, 1076, 230);
 listComando.add(comando);
 listComando.add(comando2); 
 HealthCenter h = new HealthCenter();
 addObject(h, 150 , 80);
 HealthCenter h1 = new HealthCenter();
 addObject(h1, 1050 , 720);
 listHealthCenter.add(h);
 listHealthCenter.add(h1);
}
public void act()
{
 barIter.add(1);   
 iteraciones++;   
 if(iteraciones == maxIteraciones)
 {
   PlayerChoice winner = null;  
   String message;  
   message = checkWin();  
   if(!message.equals("Empate!"))
   {
    SmallScreen messageBox = new SmallScreen(message,getHeight()/2,getWidth()/2, 999999);
    winner = new PlayerChoice(this.raceWin);
    addObject(messageBox, getWidth()/2, getHeight()/2);
    addObject(winner, 592,462);
    Greenfoot.stop();
   }
   else
   {
    SmallScreen messageBox = new SmallScreen(message,getHeight()/2,getWidth()/2, 999999,"");
    addObject(messageBox, getWidth()/2, getHeight()/2);
    Greenfoot.stop();   
    }    
 }    
 //openFile();
 //agregarRegistro();
 //cerrar();
 checkStatus();
 checkSave();
 pressKey();
}
public void pressKey()
{
 String key = Greenfoot.getKey();
 if ("p".equals(key)) 
 {
  for(int i =0; i<listProtoss.size(); i++)
  {
   listProtoss.get(i).setControlState(false);
  }
 }    
 if ("o".equals(key))
 {
  for(int i =0; i<listProtoss.size(); i++)
  {
   listProtoss.get(i).setControlState(true);
  }  
 }
 if ("z".equals(key)) 
 {
  for(int i =0; i<listZerg.size(); i++)
  {
   listZerg.get(i).setControlState(false);
  }
 }    
 if ("x".equals(key))
 {
  for(int i =0; i<listProtoss.size(); i++)
  {
   listZerg.get(i).setControlState(true);
  }  
 }
 if ("t".equals(key)) 
 {
  for(int i =0; i<listTerran.size(); i++)
  {
   listTerran.get(i).setControlState(false);
  }
 }    
 if ("y".equals(key))
 {
  for(int i =0; i<listTerran.size(); i++)
  {
   listTerran.get(i).setControlState(true);
  }  
 }   
    
}  
public void creating()
{    
 crearObstaculos(8);    
 crearDepositos(3);
 crearDepositosDeGas(3);
 generarExtraterrestres(p1Raza,3,110,120);
 generarExtraterrestres(p2Raza,3,1000,1020);   
} 
public int getIteraciones()
{
 return iteraciones;   
}
public List<HealthCenter> getHealthC()
{
 return listHealthCenter;
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
  addObject(g, getRandomNumberRange(300,700),getRandomNumberRange(100,700));
 }   
}
public void crearObstaculos(int cantidad)
{
 for(int i=0; i<cantidad; i++)
 {
  Obstaculo o = new Obstaculo();
  //aqui utilizo el metodo getRandomNumberRange para agregar los objetos en cierto rango, esto para que no se generen en las esquinas del mundo y puedan ocasionar problemas.
  addObject(o, getRandomNumberRange(300,700),getRandomNumberRange(100,700));
 }   
}    
public void crearDepositos(int num)
{
 for(int i=0; i<num; i++)
 {
  Deposito d = new Deposito(); 
  addObject(d, getRandomNumberRange(300,700),getRandomNumberRange(100,700));
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
 int energiaTotal=0, iterador=0;
 if(raza == "Protoss")
 {
  for(int i=0; i<num; i++)
  {
   //notese que le estoy enviando como parametro a Protoss "generarParametro()" ese metodo retorna un valor tipo string, y su funcion es generar un rol al azar.  
   EnergyCounter cont = new EnergyCounter();
   Protoss p = new Protoss(generarParametro(iterador), cont, Gcont1);
   //el metodo getRandomNumber esta mas abajo del codigo y permite escoger un numero al azar que este entre start y end
   int x = getRandomNumberRange(start,end);
   //le doy al getrandomNumber el parametro getHeight que es la altura del mapa para que me genere un numero al azar de 0 a getHeight. 
   int y = getRandomNumberRange(50,getHeight()-50);
   if(y==525 || y == 257)
   {
     y += 15;   
    }    
   addObject(p,x,y);
   energiaTotal += p.getEnergia();
   cont.setProtoss(p);
   listCont1.add(cont);
   iterador = iterador +1;
   if(iterador>2){
     iterador=0;  
   }    
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
   Terran t = new Terran(generarParametro(iterador), cont2, Gcont2);
   int x = getRandomNumberRange(start,end);
   int y = getRandomNumberRange(50,getHeight()-50);
   if(y==525 || y == 257)
   {
     y += 15;   
    } 
   addObject(t,x,y);
   energiaTotal += t.getEnergia();
   cont2.setTerran(t);
   listCont2.add(cont2);
   iterador = iterador+1;
   if(iterador>2){
     iterador=0;  
   } 
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
   Zerg z = new Zerg(generarParametro(iterador), cont3, Gcont3);
   int x = getRandomNumberRange(start,end);
   int y = getRandomNumberRange(50,getHeight()-50);
   if(y==525 || y == 257)
   {
     y += 15;   
    } 
   addObject(z,x,y);
   energiaTotal += z.getEnergia();
   cont3.setZerg(z);
   listCont3.add(cont3);
   iterador = iterador +1;
   if(iterador>2){
     iterador=0;  
   } 
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
//Este metodo sirve para generar los roles al azar y asignarlos mientras estoy creando un numero de miembros de una especie.
public String generarParametro(int cont)
{
 String rol;
 if(cont == 0)
 {
   rol="Constructor";  
 }
 else if (cont == 1)
 {
  rol = "Guerrero";
 }   
 else
 {
  rol ="Medico";   
 } 
 return rol;   
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
public void checkStatus()
{
 PlayerChoice ganador = null;  
 String message;  
 message = checkWin();  
 if(bar1State) 
 {
  if(bar2State)
  {
   if(bar1.getValue()==0 || getProtossList().isEmpty()) 
   {
    SmallScreen messageBox = new SmallScreen(message,getHeight()/2,getWidth()/2, 999999);
    ganador = new PlayerChoice("Terran");
    addObject(messageBox, getWidth()/2, getHeight()/2);
    addObject(ganador, 592,462);
    Greenfoot.stop();
   }
   else if(bar2.getValue()==0 || getTerranList().isEmpty())
   {
    SmallScreen messageBox = new SmallScreen(message,getHeight()/2,getWidth()/2, 999999);
    ganador = new PlayerChoice("Protoss");
    addObject(messageBox, getWidth()/2, getHeight()/2);
    addObject(ganador, 592,462);
    Greenfoot.stop();  
    }    
  }
  else
  {
    if(bar1.getValue()==0 || getProtossList().isEmpty())
    {
    SmallScreen messageBox = new SmallScreen(message,getHeight()/2,getWidth()/2, 999999);
    ganador = new PlayerChoice("Zerg");
    addObject(messageBox, getWidth()/2, getHeight()/2);
    addObject(ganador, 592,462);
    Greenfoot.stop();   
    }
    else if(bar3.getValue()==0 || getZergList().isEmpty())
    {
    SmallScreen messageBox = new SmallScreen(message,getHeight()/2,getWidth()/2, 999999);
    ganador = new PlayerChoice("Protoss");
    addObject(messageBox, getWidth()/2, getHeight()/2);
    addObject(ganador, 592,462);
    Greenfoot.stop();   
    }    
  }    
 }
 if (bar2State)
 {
  if(bar3State)
  {
   if(bar2.getValue() == 0 || getTerranList().isEmpty())
   {
    SmallScreen messageBox = new SmallScreen(message,getHeight()/2,getWidth()/2, 999999);
    ganador = new PlayerChoice("Zerg");
    addObject(messageBox, getWidth()/2, getHeight()/2);
    addObject(ganador, 592,462);
    Greenfoot.stop();
   }
   else if(bar3.getValue() == 0 || getZergList().isEmpty())
   {
    SmallScreen messageBox = new SmallScreen(message,getHeight()/2,getWidth()/2, 999999);
    ganador = new PlayerChoice("Terran");
    addObject(messageBox, getWidth()/2, getHeight()/2);
    addObject(ganador, 592,462);
    Greenfoot.stop();  
    }    
  }
 }
}
public String checkWin()
{
 String message= "";
 if(bar1State)
 {
  if(bar2State)
  {
   if(bar1.getValue() > bar2.getValue())
   {
    message = "Protoss ganó!";
    this.raceWin = "Protoss";
    } 
   else if (bar1.getValue() == bar2.getValue()) 
   {
    message = "Empate!";    
    }
   else if(bar1.getValue() < bar2.getValue()) 
   {
    message = "Terran ganó!";
    this.raceWin = "Terran";
    }    
  }
  else
  {
   if(bar1.getValue() > bar3.getValue())
   {
    message = "Protoss ganó!";  
    this.raceWin = "Protoss";
    } 
   else if (bar1.getValue() == bar3.getValue()) 
   {
    message = "Empate!";    
    }
   else if(bar1.getValue() < bar3.getValue()) 
   {
    message = "Zerg ganó!";
    this.raceWin = "Zerg";
   }   
  }    
 } 
 if(bar3State)
 {
  if(bar2State)
  {
   if(bar3.getValue() > bar2.getValue())
   {
    message = "Zerg ganó!"; 
    this.raceWin = "Zerg";
    } 
   else if (bar3.getValue() == bar2.getValue()) 
   {
    message = "Empate!"; 
    }
   else if(bar3.getValue() < bar2.getValue()) 
   {
    message = "Terran ganó!";    
    this.raceWin = "Terran";
    }   
    }    
 }
 return message;
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
public void checkSave()
{
 if(Greenfoot.isKeyDown("G"))
 {
     Greenfoot.stop();
     Convert save = new Convert(this);
     save.SaveWorld();
     SmallScreen saving = new SmallScreen("guardando...",this.getHeight()/2,this.getWidth()/2,999);
     addObject(saving,this.getWidth()/2,this.getHeight()/2);
     Greenfoot.start();
     removeObject(saving);
     

            SmallScreen exitoGuardado = new SmallScreen("Mapa guardado!", this.getHeight()/2,this.getWidth()/2,20);
            addObject(exitoGuardado, getWidth()/2, getHeight()/2);
     
 }
 else if(Greenfoot.isKeyDown("J"))
 {
    try{
     Koprulu newWorld = Convert.parseSaveFile("Save.xml");   
     Greenfoot.setWorld(newWorld);
    }
    catch(Exception e){
        SmallScreen error = new SmallScreen("error",400,600,999);
    }
    }
    }   
}
   

