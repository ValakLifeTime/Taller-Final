import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Guerrero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Terran extends Extraterrestre
{   
 /**
 * Notese que para crear un Protoss, Terran o Zerg hay que darle un rol en especifico.
 */
public Terran(String rol, EnergyCounter cont, Counter gcont)
{
 GreenfootImage image = getImage();  
 image.scale(30, 30);   
 this.rol = rol; 
 this.contEnergia = cont;
 this.contRecursos = gcont;
 this.gas =0;
 if(rol == "Medico")
 {
  this.energia = 120;
  this.capacidadCuracion = 100;
  setImage("TerranMedico.png");
 }
 else
 {
  this.energia = 100;
 }  
 if(rol=="Constructor")
 { 
  setImage("TerranConstructor.png");
 }
 else if(rol=="Guerrero")
 { 
  setImage("TerranGuerrero.png");
 }
}
public Terran(String rol,boolean controlState, boolean lifeState, int cristales, int gas, int energia, int capacidadCuracion)
{
 this.rol = rol;  this.controlState = controlState; this.lifeState = lifeState; this.cristales = cristales; this.gas = gas;
 this.energia = energia;   this.capacidadCuracion = capacidadCuracion;
}
public void act() 
{
 if(!this.lifeState)
 {
  return;
 }
 maxEnergy();
 corner();
 collectDeposit();
 collectGasDeposit();
 controlMoving();
 Combate();
 tocarComandoT();
 checkLife();
 GreenfootImage image = getImage();  
 image.scale(30, 30);
 if(this.lifeState)
 {
  curarT();
  tocarHCT();
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
public void checkLife()
{
 if(this.energia <=0)
 {
 World world;
 world = getWorld();
 Koprulu koprulu = (Koprulu)world;
 koprulu.getTerranList().remove(this);
 getWorld().removeObject(this);
 this.lifeState = false;
 }
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
public void curarT()
{
 List<Terran> lista = new ArrayList<Terran>(); 
 World world;
 world = getWorld();
 Koprulu koprulu = (Koprulu)world;
 lista = koprulu.getTerranList();
 int aux = lista.size(), retroceso =20, curacion = 20;
 if(!lista.isEmpty())
 {
  if(isTouching(Terran.class))
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
public void collectDeposit(){
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
  int aux2 =20;
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
   for(int i=0; i<aux; i++)
   {
    if(intersects(lista.get(i)))
    {
     lista.get(i).restCant(aux2);
    }    
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
 else
 {
     return false;   
 }
}
public void Combate()
{
 World world =  getWorld();
 Koprulu koprulu = (Koprulu)world;
 List<Zerg> zergL = new ArrayList<Zerg>();  
 zergL = koprulu.getZergList();
 int aux = zergL.size(), hit =20, calibrarGolpe, retroceso =15;
 if(isTouching(Zerg.class))
 {
  for(int i=0; i<aux; i++)
  {
   if(intersects(zergL.get(i)))
   {
    Greenfoot.playSound("golpe.mp3"); 
    int random = Greenfoot.getRandomNumber(100);
    int probabilidad;
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
     turn(180);move(retroceso);
     if(zergL.get(i).energia >0)
     {
      if(zergL.get(i).energia - hit >= 0)
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
public void tocarComandoT() 
{
 List<Comando> comandos = new ArrayList<Comando>();
 List<Terran> listTerran = new ArrayList<Terran>();
 World world;
 world = getWorld();
 Koprulu koprulu = (Koprulu)world;
 comandos = koprulu.getComando();
 listTerran = koprulu.getTerranList();
 int aux, calcEnergia = 0, retroceso =15;
 if(isTouching(Comando.class))
 {
  if(intersects(comandos.get(0)))
  {
   if(koprulu.getp1Raza()=="Terran")
   {
    if(this.rol == "Constructor")
    {
     if(contRecursos.getGas()>0)
     {
      aux = this.gas/5;
      for(int i=0; i<listTerran.size(); i++) //recorro todos las instancias de la clase Protoss para darle la energia que fue obtenida mediante la transformacion
      //de recursos a cada instancia de la clase, o hasta que esa cantidad de energia se acabe.
      { 
       if(aux > 0)
       {
        if(!listTerran.get(i).maxEnergy()) //si las instancia numero i no tiene la maxima energia, entra 
        {
         if(listTerran.get(i).energia+aux <= 160) //aqui es para ver si al sumarle la energia esta llega a superar el maximo, si no lo supera entra.
         {
          contEnergia.addEnergia(aux); 
          listTerran.get(i).energia += aux;
          aux = 0; //puesto que aux vale 0, seguira haciendo iteraciones, pero la energia que va a añadir en todos los casos será 0.
         }
         else
         { //si al sumarle la energia supera el maximo, se le sumara la resta entre el maximo que es 160, y la energia que tenga
         // si por ejemplo tiene 150 de energia, y al sumarle la cantidad de recursos transformados supera los 160, se le agregara 160-150, osea 10.
         calcEnergia = 160-listTerran.get(i).energia;
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
        comandos.get(0).bar.add(this.cristales);
        this.cristales=0; 
        }
     turn(180); move(retroceso);
    }
   }
  }
  if(intersects(comandos.get(1)))
  {
   if(koprulu.getp2Raza()=="Terran")
   {
    if(this.rol == "Constructor")
    {
     if(contRecursos.getGas()>0)
     {
      aux = gas/5;
      for(int i=0; i<listTerran.size(); i++) //recorro todos las instancias de la clase Protoss para darle la energia que fue obtenida mediante la transformacion
      //de recursos a cada instancia de la clase, o hasta que esa cantidad de energia se acabe.
      { 
       if(aux > 0)
       {
        if(!listTerran.get(i).maxEnergy()) //si las instancia numero i no tiene la maxima energia, entra 
        {
         if(listTerran.get(i).energia+aux <= 160) //aqui es para ver si al sumarle la energia esta llega a superar el maximo, si no lo supera entra.
         {
          contEnergia.addEnergia(aux); 
          listTerran.get(i).energia += aux;
          aux = 0; //puesto que aux vale 0, seguira haciendo iteraciones, pero la energia que va a añadir en todos los casos será 0.
         }
         else
         { //si al sumarle la energia supera el maximo, se le sumara la resta entre el maximo que es 160, y la energia que tenga
          // si por ejemplo tiene 150 de energia, y al sumarle la cantidad de recursos transformados supera los 160, se le agregara 160-150, osea 10.
          calcEnergia = 160-listTerran.get(i).energia;
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
    if(koprulu.getp1Raza()=="Terran")
    {
     turn(180); move(retroceso);
    }
   }
  }
  if(intersects(comandos.get(0)))
  {
   if(this.rol == "Constructor")
   {
    if(koprulu.getp2Raza()=="Terran")
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
public void tocarHCT()
{
 List<HealthCenter> healthCenter = new ArrayList<HealthCenter>();
 World world;
 world = getWorld();
 Koprulu koprulu = (Koprulu)world;
 healthCenter = koprulu.getHealthC();
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
     turn(180);move(20);
    }         
   }                  
  }                   
 }
}
}
