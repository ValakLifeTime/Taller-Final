import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    public Terran(String rol, EnergyCounter cont, Counter gcont){
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
        contEnergia.addEnergy(20);    
        energia += 20;
        }
        
    } 
    public void collectGasDeposit()
    {
        //aca es lo mismo que comente en el metodo anterior
       if(isTouching(GasDeposit.class))
     {
     if(getRol() == "Constructor")
     {   

       contRecursos.addRecursos(40);
   

     }
     }
     
    }    
    //este metodo sirve para poner un tope de energia de 160.
    public void maxEnergy(){
    if(contEnergia.maxEnergy() >=160){
     contEnergia.energia = 160;
     energia = 160;
        }
    }
}
