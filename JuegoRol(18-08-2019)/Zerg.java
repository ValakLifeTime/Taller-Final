import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Medico here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Zerg extends Extraterrestre
{
    /**
     * Act - do whatever the Medico wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
     public Zerg(String rol, EnergyCounter cont, Counter gcont){
     this.rol = rol; 
     //esto es para ajustar la escala de la imagen. Pronto se haran los diseños para darselas con este metodo.
     this.contEnergia = cont;
     this.contRecursos = gcont;
     this.recursos=0;
        if(rol == "Medico")
     {
         this.energia = 120;
         setImage("ZergMedico.png");
        }
     else{
         this.energia = 100;
    }
        if(rol=="Constructor")
       { 
    setImage("ZergConstructor.png");
}
else if(rol=="Guerrero")
       { 
    setImage("ZergGuerrero.png");
}
    }
        
    public void act() 
    {
        maxEnergy();
        corner();
        collectDeposit();
        collectGasDeposit();
        moving();
        tocarComandoZ();
        checkLife();
        GreenfootImage image = getImage();  
     image.scale(40, 40);
     setImage(image);
        //conversionRecursos();
     }
       public void checkLife()
    {
     if(this.energia <=0)
     {
         getWorld().removeObject(this);
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
         int aux2 =15;
        if(!maxEnergy())
       {
           if(this.energia > 145)
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

    public boolean maxEnergy(){
    if(this.energia >= 160){
     this.energia =160;
     return true;
    }
    else{
     return false;   
        }
    }
    public void collectGasDeposit()
    {
        //estos if sirven para saber que rol tiene el miembro y acorde a la tabla que esta en la diapositiva del taller final añadir los recursos.
     if(isTouching(GasDeposit.class))
     {
         if(getRol() == "Constructor")
         {              
             this.contRecursos.addRecursos(45);
             this.recursos += 45;
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
     public void conversionRecursos()
    {
     if(getRol() == "Constructor")   
     if(isTouching(Comando.class))
     {
       int conversion;
       conversion = this.recursos/5;
       this.contRecursos.recursos = 0;
       this.contEnergia.addEnergia(conversion);
       this.recursos =0;
        }    
    } 
    public void tocarComandoZ() 
    {
        List<Comando> comandos = new ArrayList<Comando>();
        List<Zerg> listZerg = new ArrayList<Zerg>();
        World world;
        world = getWorld();
        Koprulu koprulu = (Koprulu)world;
        comandos = koprulu.getComando();
        listZerg = koprulu.getZergList();
        int aux, calcEnergia=0;
        
        if(isTouching(Comando.class))
        {
            if(intersects(comandos.get(0)))
            {
                if(koprulu.getp1Raza()=="Zerg")
                {
                    
                
                    if(this.rol == "Constructor")
                    {
                        if(contRecursos.getRecursos()>0)
                        {
                               aux = recursos/5;
                               for(int i=0; i<listZerg.size(); i++) //recorro todos las instancias de la clase Protoss para darle la energia que fue obtenida mediante la transformacion
                               //de recursos a cada instancia de la clase, o hasta que esa cantidad de energia se acabe.
                               { 
                                 if(aux > 0)
                                 {
                                 if(!listZerg.get(i).maxEnergy()) //si las instancia numero i no tiene la maxima energia, entra 
                                  {
                                      if(listZerg.get(i).energia+aux <= 160) //aqui es para ver si al sumarle la energia esta llega a superar el maximo, si no lo supera entra.
                                      {
                                      contEnergia.addEnergia(aux); 
                                      listZerg.get(i).energia += aux;
                                      aux = 0; //puesto que aux vale 0, seguira haciendo iteraciones, pero la energia que va a añadir en todos los casos será 0.
                                    }
                                    else{ //si al sumarle la energia supera el maximo, se le sumara la resta entre el maximo que es 160, y la energia que tenga
                                        // si por ejemplo tiene 150 de energia, y al sumarle la cantidad de recursos transformados supera los 160, se le agregara 160-150, osea 10.
                                        calcEnergia = 160-listZerg.get(i).energia;
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
                if(koprulu.getp2Raza()=="Zerg")
                {
                    if(this.rol == "Constructor")
                    {
                        if(contRecursos.getRecursos()>0)
                        {
                               aux = recursos/5;
                               for(int i=0; i<listZerg.size(); i++) //recorro todos las instancias de la clase Protoss para darle la energia que fue obtenida mediante la transformacion
                               //de recursos a cada instancia de la clase, o hasta que esa cantidad de energia se acabe.
                               { 
                                 if(aux > 0)
                                 {
                                 if(!listZerg.get(i).maxEnergy()) //si las instancia numero i no tiene la maxima energia, entra 
                                  {
                                      if(listZerg.get(i).energia+aux <= 160) //aqui es para ver si al sumarle la energia esta llega a superar el maximo, si no lo supera entra.
                                      {
                                      contEnergia.addEnergia(aux); 
                                      listZerg.get(i).energia += aux;
                                      aux = 0; //puesto que aux vale 0, seguira haciendo iteraciones, pero la energia que va a añadir en todos los casos será 0.
                                    }
                                    else{ //si al sumarle la energia supera el maximo, se le sumara la resta entre el maximo que es 160, y la energia que tenga
                                        // si por ejemplo tiene 150 de energia, y al sumarle la cantidad de recursos transformados supera los 160, se le agregara 160-150, osea 10.
                                        calcEnergia = 160-listZerg.get(i).energia;
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
                    if(koprulu.getp1Raza()=="Zerg")
                    {
                        turn(180); move(20);
                    }
                    
                }
                
            }
            if(intersects(comandos.get(0)))
            {
                if(this.rol == "Constructor")
                {
                    if(koprulu.getp2Raza()=="Zerg")
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
