import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
     GreenfootImage image = getImage();  
     image.scale(40, 40);
     setImage(image);
     this.contEnergia = cont;
     this.contRecursos = gcont;
     cont.setEnergia(this.energia);
    }    
public void act() 
    {
        maxEnergy();
        corner();
        collectDeposit();
        collectGasDeposit();
        moving();
    }  
    /*
     * Este metodo sirve para interactuar con los depositos que dan cristales de energia
     */
    public void collectDeposit(){
        /*aqui creo un world de tipo World, y lo igualo a getWorld() para obtener el mundo en el que esta, luego creo un mundo de tipo MyWorld y casteo world a MyWorld
         * todo eso para poder interactuar entre la especie y el contador energyCounter el cual lleva el conteo de la energia del extraterrestre
        */
         
     if(isTouching(Deposito.class))
     {        
        contEnergia.addEnergy(15);   
        energia += 15;
    }
}

    public void maxEnergy(){
     if(contEnergia.maxEnergy() >=160){
     contEnergia.energia = 160; 
     energia =160;
        }
    }
    public void collectGasDeposit()
    {
        //estos if sirven para saber que rol tiene el miembro y acorde a la tabla que esta en la diapositiva del taller final añadir los recursos.
       if(isTouching(GasDeposit.class))
     {
     if(getRol() == "Constructor")
     {              
       contRecursos.addRecursos(45);
     
      
     }    
     }
    }
    public void combate()
    {
        
    }   
}
