import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Constructor here.
 * 
 * @author (José Silva) 
 * @version (21/07/2019)
 */
public class Protoss extends Extraterrestre
{
 private boolean estado = true;
 /**
  * Notese que para crear un Protoss, Terran o Zerg hay que darle un rol en especifico.
 */
public Protoss(String rol, EnergyCounter cont, Counter gcont)
{
 GreenfootImage image = getImage();  
 image.scale(30, 30);   
 this.rol = rol; 
 //esto es para ajustar la escala de la imagen. Pronto se haran los diseños para darselas con este metodo.
 this.contEnergia = cont;
 this.contRecursos = gcont;
 this.gas = 0;
 if(rol == "Medico")
 {
  this.energia = 120;
  this.capacidadCuracion = 100;
  setImage("ProtossMedico.png");
 }
 else{
  this.energia = 100;
 }  
 if(rol=="Constructor")
 { 
  setImage("ProtossConstructor.png");
 }
 else if(rol=="Guerrero")
 { 
  setImage("ProtossGuerrero.png");
 }
}
public Protoss(String rol,boolean controlState, boolean lifeState, int cristales, int gas, int energia, int capacidadCuracion)
{
 this.rol = rol;  this.controlState = controlState; this.lifeState = lifeState; this.cristales = cristales; this.gas = gas;
 this.energia = energia;   this.capacidadCuracion = capacidadCuracion;
}
public void act() 
{
 if(!this.estado)
 {
  return;
 }   
 maxEnergy();
 corner();
 collectDeposit();
 collectGasDeposit();
 controlMoving();
 Combate();
 tocarComandoP();
 checkLife();
 GreenfootImage image = getImage();  
 image.scale(30, 30);
 if(this.lifeState)
 {
  curarP();
  tocarHCP();
 }
}
public boolean maxCristals()
{
 if(this.cristales <30)
 {
  return false;
}
 else
 {
   return true;  
    }
}    
public boolean getLifeState()
{
 return lifeState;   
}    
public void setLifeState(boolean lifeState)
{
 this.lifeState = lifeState;   
} 
public boolean getControlState()
{
 return controlState;   
}    
public void setControlState(boolean controlState)
{
 this.controlState = controlState;   
} 
public void controlMoving()
{
 if(this.controlState)
 {
  moving();
 }
 else 
 {
  int x = getX(), y = getY();
  if(Greenfoot.isKeyDown("up"))
  {
   setLocation(x, y-speed);
  }    
  if(Greenfoot.isKeyDown("left"))
  {
   setLocation(x-speed, y);
  } 
  if(Greenfoot.isKeyDown("down"))
  {
   setLocation(x, y+speed);
  } 
  if(Greenfoot.isKeyDown("right"))
  {
   setLocation(x+speed, y);
  }
 }
}
public void checkLife()
{
 if(this.energia <=0)
 {  
  World world = getWorld();
  Koprulu koprulu = (Koprulu)world;   
  koprulu.getProtossList().remove(this);   
  getWorld().removeObject(this);
  this.lifeState = false;
 }
}       
public void curarP()
{
 List<Protoss> lista = new ArrayList<Protoss>(); 
 World world;
 world = getWorld();
 Koprulu koprulu = (Koprulu)world;
 lista = koprulu.getProtossList();
 int aux = lista.size(), retroceso =20, curacion=15;
 if(!lista.isEmpty())
 {
  if(isTouching(Protoss.class))
  {
   if(this.getRol() == "Medico")
   {
    for(int i=0; i<aux; i++)
    {
     if(intersects(lista.get(i)))
     {
      if(!lista.get(i).getRol().equals("Medico"))
      {
       if(!lista.get(i).maxEnergy())
       {
        lista.get(i).energia += curacion;
        this.capacidadCuracion -= 20;
        contEnergia.addEnergia(curacion);
        turn(180);
        move(retroceso);
       }
      }                
     }
    }
   }
  }
 } 
}
/*
* Este metodo sirve para interactuar con los depositos que dan cristales de energia
*/
public void collectDeposit()
{
/*aqui creo un world de tipo World, y lo igualo a getWorld() para obtener el mundo en el que esta, luego creo un mundo de tipo MyWorld y casteo world a MyWorld
* todo eso para poder interactuar entre la especie y el contador energyCounter el cual lleva el conteo de la energia del extraterrestre
*/
List<Deposito> lista = new ArrayList<Deposito>(); 
World world;
world = getWorld();
Koprulu koprulu = (Koprulu)world;
lista = koprulu.getDepositList();
int aux = lista.size(), retroceso = 15;
 if(isTouching(Deposito.class))
 {
  int aux2 =25;
  if(this.rol.equals("Constructor"))
  {
   if(!maxCristals())
   {
    this.cristales+=30;
    for(int i=0; i<aux; i++)
   {
    if(intersects(lista.get(i)))
    {
     lista.get(i).restCant(30);
    }    
   }
    }   
  }   
  if(!maxEnergy())
  {
   if(this.energia+aux2 <= 160)
   {
    contEnergia.addEnergia(aux2);
    this.energia += aux2;
   }
   else
   {
    aux2 = 160 - this.energia;   
    contEnergia.addEnergia(aux2); 
    this.energia += aux2; 
   }
  }
  turn(180); move(retroceso);
 }
}
public void collectGasDeposit()
{
  int retroceso = 15, aux;
  if(isTouching(GasDeposit.class))
  {
   if(this.getRol() == "Constructor")
   {
    if(this.gas <=50)
    {
     if(this.gas+40<=50)
     {
     this.contRecursos.addGas(40);
     this.gas += 40;
     turn(180); move(retroceso);
    }
    else{
     aux = 50-this.gas; 
     this.contRecursos.addGas(aux);
     this.gas += aux;
     turn(180);
     move(retroceso);
    }
    }
   }
   if(getRol() == "Medico")
   {
    turn(180); move(retroceso);
   }
   if(getRol() == "Guerrero")
   {
    turn(180); move(retroceso);
   }
  }
}   
//este metodo sirve para poner un tope de energia de 160.
public boolean maxEnergy()
{
 if(this.energia >= 160)
 {
  this.energia =160;
  return true;
 }
 else{
  return false;   
 }
}
//en este metodo se encuentran las dinamicas del combate entre las razas.
public void Combate()
{
 List<Terran> terranL = new ArrayList<Terran>();   //En este bloque se obtiene la lista con todas las instancias de la clase Terran
 World world =  getWorld();
 Koprulu koprulu = (Koprulu)world;
 terranL = koprulu.getTerranList();  
 int aux = terranL.size(); //esta variable va a ser utilizada en el for para poder recorrer toda la lista, terranL.size() es el numero de objetos que tiene la lista.
 int hit = 20, calibrarGolpe, retroceso = 15; //la variable hit es el daño que se asigna a cada enfrentamiento.
  if(isTouching(Terran.class))
  {
   //en este for se recorre toda la lista
   for(int i=0; i<aux; i++)
   {
    //esta condición es para saber si el objeto terranL.get(i) se esta tocando con algun objeto de la clase Protoss, terranL.get(i) es el objeto numero i de la lista.
    if(intersects(terranL.get(i)))
    {
     Greenfoot.playSound("golpe.mp3");
     //en estas dos variables se realiza la probabilidad de quie ganara el encuentro, el metodo getRandomNumber(100) obtendrá un numero entre 0 y 99.
     int random = Greenfoot.getRandomNumber(100);
     int probabilidad;
     //si el numero generado por getRandomNumber es mayor o igual que 50 la variable probabilidad la igualo a 1 para utilizarlo mas adelante en el switch.
     if(random >= 50)
     {
      probabilidad = 1; 
     }
     else
     {
      probabilidad = 0; 
     }    
      switch(probabilidad)
      {
       case 0:
       turn(180); move(retroceso); //esto es para que despues de cada enfrentamiento se alejen un poco.
       /*en el caso en el que probabilidad vale 0, se tiene lo siguiente. Si la energia de la instancia de protoss es mayor que 0 le restamos el daño tanto al atributo energia, como
       al contador*/
       if(this.energia >0)
       {
        if(this.energia-hit >= 0) //si al restarle"hit" a la energia del organismo esta es mayor que 0, se le resta a los contadores y a los atributos
        {
         this.energia -= hit;
         this.contEnergia.restEnergia(hit);
        }
        else //de lo contrario, se le baja hit-energia,esto se hace para que no se baje energia de mas a los contadores.
        {
         calibrarGolpe = hit-this.energia;
         this.energia -= calibrarGolpe;
         this.contEnergia.restEnergia(calibrarGolpe);
        }
       }     
        break;
       case 1:  
       turn(180); move(retroceso);
       if(terranL.get(i).energia >0)
       {
        if(terranL.get(i).energia-hit >= 0)
        {
         terranL.get(i).energia -= hit;
         terranL.get(i).contEnergia.restEnergia(hit);     
        } 
        else
        {
         calibrarGolpe = hit- terranL.get(i).energia;
         terranL.get(i).energia -= calibrarGolpe;
         terranL.get(i).contEnergia.restEnergia(calibrarGolpe);
        }
       }
         break;
      }             
     }
    }
   }
 List<Zerg> zergL = new ArrayList<Zerg>();  //aca creo otra lista, para las instancias de la clase Zerg
 zergL = koprulu.getZergList();
 aux = zergL.size();
 if(isTouching(Zerg.class))
 {
  for(int i=0; i<aux; i++)
  {
   if(intersects(zergL.get(i)))
   {
    Greenfoot.playSound("golpe.mp3");
    int random = Greenfoot.getRandomNumber(100);
    int probabilidad;
    //si el numero generado por getRandomNumber es mayor o igual que 50 la variable probabilidad la igualo a 1 para utilizarlo mas adelante en el switch.
    if(random >= 50)
    {
     probabilidad = 1; 
    }
    else
    {
     probabilidad = 0; 
    }  
    switch(probabilidad)
    {
     case 0:
     turn(180); move(retroceso); 
     if(this.energia >0)
     {
      if(this.energia-hit >= 0) //si al restarle"hit" a la energia del organismo esta es mayor que 0, se le resta a los contadores y a los atributos
      {
       this.energia -= hit;
       this.contEnergia.restEnergia(hit);
      }
      else //de lo contrario, se le baja hit-energia,esto se hace para que no se baje energia de mas a los contadores.
      {
       calibrarGolpe = hit-this.energia;
       this.energia -= calibrarGolpe;
       this.contEnergia.restEnergia(calibrarGolpe);
      }
     }     
      break;
     case 1: 
     turn(180); move(retroceso);
     if(zergL.get(i).energia >0)
     {
      if(zergL.get(i).energia-hit >= 0)
      {
       zergL.get(i).energia -= hit;
       zergL.get(i).contEnergia.restEnergia(hit);
      }
      else
      {
       calibrarGolpe = hit-zergL.get(i).energia;  
       zergL.get(i).energia -= calibrarGolpe;
       zergL.get(i).contEnergia.restEnergia(calibrarGolpe);  
      }
     }     
      break;
    }
   }    
  }  
 }
    
}
public void tocarComandoP() 
{
 List<Comando> comandos = new ArrayList<Comando>();
 List<Protoss> listProtoss = new ArrayList<Protoss>();
 World world;
 world = getWorld();
 Koprulu koprulu = (Koprulu)world;
 comandos = koprulu.getComando();
 listProtoss = koprulu.getProtossList();
 int aux, calcEnergia=0, retroceso = 15;
 if(isTouching(Comando.class))
  {
   if(intersects(comandos.get(0)))
   {
    if(koprulu.getp1Raza()=="Protoss")
    {
     if(getRol() == "Constructor")
     {
      if(contRecursos.getGas()>0)
       {
        aux = this.gas/5;
        for(int i=0; i<listProtoss.size(); i++)
        { 
         if(aux>0)
         {
          if(!listProtoss.get(i).maxEnergy())
          {
           if(listProtoss.get(i).energia + aux <= 160)
           {
            contEnergia.addEnergia(aux); 
            listProtoss.get(i).energia += aux;
            aux=0;
           }
           else{
            calcEnergia = 160-listProtoss.get(i).energia;
            aux = aux-calcEnergia;
           }
          }
         }
        }
        contRecursos.quitarGas(gas);
        this.gas =0;
       }
      if(this.cristales>0)
      {
        comandos.get(0).bar.add(this.cristales);
        this.cristales=0; 
        }  
        turn(180); move(retroceso);
       }
    }
   }
   if(intersects(comandos.get(1)))
   {
    if(koprulu.getp2Raza()=="Protoss")
    {
     if(this.rol == "Constructor")
     {
      if(contRecursos.getGas()>0)
       {
        aux = gas/5;
        for(int i=0; i<listProtoss.size(); i++) //recorro todos las instancias de la clase Protoss para darle la energia que fue obtenida mediante la transformacion
        //de recursos a cada instancia de la clase, o hasta que esa cantidad de energia se acabe.
        { 
         if(aux>0)
         {
          if(!listProtoss.get(i).maxEnergy()) //si las instancia numero i no tiene la maxima energia, entra 
          {
           if(listProtoss.get(i).energia+aux <= 160) //aqui es para ver si al sumarle la energia esta llega a superar el maximo, si no lo supera entra.
           {
            contEnergia.addEnergia(aux); 
            listProtoss.get(i).energia += aux;
            aux = 0; //puesto que aux vale 0, seguira haciendo iteraciones, pero la energia que va a añadir en todos los casos será 0.
           }
           else{ //si al sumarle la energia supera el maximo, se le sumara la resta entre el maximo que es 160, y la energia que tenga
            // si por ejemplo tiene 150 de energia, y al sumarle la cantidad de recursos transformados supera los 160, se le agregara 160-150, osea 10.
            calcEnergia = 160-listProtoss.get(i).energia;
            aux = aux-calcEnergia; //En este caso el for hara otra iteracion porque el aux no ha llegado a 0;
           }
          }
         }     
        }
        contRecursos.quitarGas(gas);
        this.gas =0;
       }
       if(this.cristales>0)
      {
        comandos.get(1).bar.add(this.cristales);
        this.cristales=0; 
        }
      turn(180); move(retroceso);
     }
    }
   }
   if(intersects(comandos.get(1)))
   {
    if(this.rol == "Constructor")
    {
     if(koprulu.getp1Raza()=="Protoss")
     {
       turn(180); move(retroceso);
     }
    }
   }
   if(intersects(comandos.get(0)))
   {
    if(this.rol == "Constructor")
    {
      if(koprulu.getp2Raza()=="Protoss")
      {
      turn(180); move(retroceso);
      }
    }
   }
   if(this.rol == "Medico")
   {
   turn(180); move(retroceso);
   }
   if(this.rol == "Guerrero")
   {
   turn(180); move(retroceso);
   }
  }   
}
public void tocarHCP()
{
 List<HealthCenter> healthCenter = new ArrayList<HealthCenter>();
 World world;
 world = getWorld();
 Koprulu koprulu = (Koprulu)world;
 healthCenter = koprulu.getHealthC();
 int retroceso = 20;
 if(isTouching(HealthCenter.class))
 {
  if(getRol().equals("Medico") && capacidadCuracion < 120)
  {
   for (int i=0; i<2; i++)
   {
    if(intersects(healthCenter.get(i)))
    {
     healthCenter.get(i).restarCelda();
     capacidadCuracion +=20;
     turn(180);
     move(retroceso);
    }         
   }                  
  }                   
 }
}
}
