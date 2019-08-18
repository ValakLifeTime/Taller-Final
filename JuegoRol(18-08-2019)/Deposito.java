import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Deposito here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Deposito extends Actor
{
    //Aqui le asigno una cantidad maxima a cada Deposito de energia, oscila entre 100 y 400.
    int cantMaxima=getRandomNumber(100,400);
    /**
     * Act - do whatever the Deposito wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Deposito(){
     setImage(new GreenfootImage("crystal.png"));
     GreenfootImage image = getImage();  
     image.scale(50, 50);
    }  
    public int getRandomNumber(int start,int end)
{
       int normal = Greenfoot.getRandomNumber(end-start+1);
       return normal+start;
}
    public void act() 
    {
        //aqui en act va a estar durante cada iteracion comprobando que tal cantidad sea mayor que 0, si es menor o igual la instancia es removida.
     if(cantMaxima <=0){
        getWorld().removeObject(this); 
    }   
}
public void restCant(int cantidad)
{
 this.cantMaxima = this.cantMaxima - cantidad; 
 if(this.cantMaxima<=0)
 {
     getWorld().removeObject(this);
 }    
}    
}
