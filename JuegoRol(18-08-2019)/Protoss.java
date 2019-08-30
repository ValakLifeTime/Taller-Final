

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
    long pauseTime = 0;
    /**
     * Notese que para crear un Protoss, Terran o Zerg hay que darle un rol en especifico.
     */
    public Protoss(String rol, EnergyCounter cont, Counter gcont){
     this.rol = rol; 
     //esto es para ajustar la escala de la imagen. Pronto se haran los diseños para darselas con este metodo.
     this.contEnergia = cont;
     this.contRecursos = gcont;
     this.recursos = 0;
        if(rol == "Medico")
     {
         this.energia = 120;
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
    public void act() 
    {
        maxEnergy();
        corner();
        collectDeposit();
        collectGasDeposit();
        controlMoving();
        Combate();
        pause(1000);
        running();
        tocarComandoP();
        checkLife();
         GreenfootImage image = getImage();  
         image.scale(40, 40);
        curar();
       
        //conversionRecursos();
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
    public void controlMoving()
    {
        if(Greenfoot.mouseClicked(this)){

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
     else 
     {
         moving();
        }
    }
      public void checkLife()
    {
     if(this.energia <=0)
     {
         getWorld().removeObject(this);
        }
        }
        
    public void curarC()
    {
         List<Protoss> lista = new ArrayList<Protoss>(); 
        World world;
        world = getWorld();
        Koprulu koprulu = (Koprulu)world;
        lista = koprulu.getProtossList();
        if(isTouching(Protoss.class))
        {
           
            if(getRol() == "Medico")
            {
                if(this.energia>40)
                {
                   
                    this.energia -=20;
                    
                }
              
            }
            
            
            for(int i=0; i<3; i++)
            {

                if(intersects(lista.get(i)))
                {
                    if(getRol()=="Cosntructor")
                    {
                        this.energia += 20;
                        turn(180);move(60);
                        
                    }
                    
                }
            }

           
        }
    }
    public void curar()
    {
        List<Protoss> lista = new ArrayList<Protoss>(); 
        List<Protoss> listaG = new ArrayList<Protoss>(); 
        World world;
        world = getWorld();
        Koprulu koprulu = (Koprulu)world;
        lista = koprulu.getProtossList();
        int aux = lista.size();
        for(Protoss i: lista)
        {
            if(i.getRol()=="Constructor")
            {
                listaG.add(i);
            }
        }
        
        if(isTouching(Protoss.class))
        {
             
            if(getRol() == "Medico")
            {
                if(this.energia>40)
                {
                   
                    this.energia -=20;
                   
                }
    
            }           
            for(int i=0; i<aux; i++)
            {
                if(intersects(lista.get(i)))
                {
                    if(getRol()=="Guerrero")
                    {
                        this.energia +=20;
                        turn(180);move(50);
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
     int aux = lista.size();
     if(isTouching(Deposito.class))
     {
         int aux2 =25;
        if(!maxEnergy())
       {
           if(this.energia > 135)
           {
             aux2 = 160 - this.energia;
             contEnergia.addEnergia(aux2);
             this.energia += aux2;
             turn(180); move(20);
            }
           else{
               
               contEnergia.addEnergia(aux2); 
               this.energia += aux2;
               turn(180); move(20);
            }
           }
           
        for(int i=0; i<aux; i++)
        {
           if(intersects(lista.get(i)))
          {
              lista.get(i).restCant(aux2);
           }    
           
        }
        

     
     }
     
    } 
    public void collectGasDeposit()
    {
     if(isTouching(GasDeposit.class))
     {
         if(getRol() == "Constructor")
         {  
             this.contRecursos.addRecursos(35);
             this.recursos += 35;
             turn(180); move(10);
       
   
         }
         if(getRol() == "Medico")
        {
            turn(180); move(20);
        }
        if(getRol() == "Guerrero")
        {
            turn(180); move(20);
        }
     }
     
    }    
    //este metodo sirve para poner un tope de energia de 160.
    public boolean maxEnergy(){
    if(this.energia >= 160){
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
     int hit = 20, calibrarGolpe; //la variable hit es el daño que se asigna a cada enfrentamiento.
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
              turn(180); move(20); //esto es para que despues de cada enfrentamiento se alejen un poco.
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
              //aqui algo similar a lo anterior, 
              turn(180); move(5);
               if(terranL.get(i).energia >0)
              {
                   if(terranL.get(i).energia-hit >= 0)
              {
                     terranL.get(i).energia -= hit;
                     terranL.get(i).contEnergia.restEnergia(hit);
                      
              } 
              else{
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
               turn(180); move(20); 
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
              turn(180); move(20);
               if(zergL.get(i).energia >0)
              {
                  if(zergL.get(i).energia-hit >= 0)
                  {
                   zergL.get(i).energia -= hit;
                   zergL.get(i).contEnergia.restEnergia(hit);
                }
                else{
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
    //En este metodo se realza la conversion de los recursos obtenidos por los constructores de una raza a unidades de energia.
    public void conversionRecursos()
    {
     if(getRol() == "Constructor")   //si es cosntructor y esta tocando la base.
     if(isTouching(Comando.class))
     {
       int conversion;
       conversion = this.recursos/5; //cada 5 de recursos equivale a 1 de energia. Aqui no hay problema en que conversion sea int debido a que la energia en todos los casos va a ser multiplo
       this.contEnergia.addEnergia(conversion); //multiplo de 5*
       this.contRecursos.recursos = 0;
       this.recursos = 0; //Aqui reduzco los recursos a 0.
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
        int aux, calcEnergia=0;
        
        if(isTouching(Comando.class))
        {
            if(intersects(comandos.get(0)))
            {
                if(koprulu.getp1Raza()=="Protoss")
                {
                    if(getRol() == "Constructor")
                    {
                        if(contRecursos.getRecursos()>0)
                        {
                               aux = this.recursos/5;
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
                                    }
                                    else{
                                        calcEnergia = 160-listProtoss.get(i).energia;
                                        aux = aux-calcEnergia;
                                    }
                                      }
                                    }
                            }
                               contRecursos.quitarRecursos(recursos);
                               this.recursos =0;
                            
                        }

                        turn(180); move(20);
                    }
                }
                }

            
            
            if(intersects(comandos.get(1)))
            {
                if(koprulu.getp2Raza()=="Protoss")
                {
                    if(this.rol == "Constructor")
                    {
                        if(contRecursos.getRecursos()>0)
                        {
                               aux = recursos/5;
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
                               contRecursos.quitarRecursos(recursos);
                               this.recursos =0;
                            
                        }

                        turn(180); move(20);
                    }
                }

            }
            if(intersects(comandos.get(1)))
            {
                if(this.rol == "Constructor")
                {
                    if(koprulu.getp1Raza()=="Protoss")
                    {
                        turn(180); move(20);
                    }
                    
                }
                
            }
            if(intersects(comandos.get(0)))
            {
                if(this.rol == "Constructor")
                {
                    if(koprulu.getp2Raza()=="Protoss")
                    {
                        turn(180); move(20);
                    }
                    
                }
                
            }
            if(this.rol == "Medico")
            {
                 turn(180); move(20);
            }
            if(this.rol == "Guerrero")
            {
                 turn(180); move(20);
            }
        }   
    }
}
