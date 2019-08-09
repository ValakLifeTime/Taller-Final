import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Deposito here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Deposito extends Actor
{
    int cantMaxima=getRandomNumber(100,400);
    /**
     * Act - do whatever the Deposito wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Deposito(){
     setImage(new GreenfootImage("crystal.png"));
     GreenfootImage image = getImage();  
     image.scale(20, 20);
    }  
    public int getRandomNumber(int start,int end)
{
       int normal = Greenfoot.getRandomNumber(end-start+1);
       return normal+start;
}
    public void act() 
    {
     if(cantMaxima <=0){
        removeTouching(Deposito.class); 
    }   
}
public void restCant(int cantidad)
{
 cantMaxima = cantMaxima - cantidad; 
 if(cantMaxima<=0)
 {
     getWorld().removeObject(this);
    }    
}    
}
