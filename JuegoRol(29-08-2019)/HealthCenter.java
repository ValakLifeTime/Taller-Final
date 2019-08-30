import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthCenter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthCenter extends Actor
{
int numCelda =20;
boolean estado=true;
public HealthCenter()
{
    GreenfootImage image = getImage();  
     image.scale(40, 40);
}
public HealthCenter(int numCelda, boolean estado)
{
    GreenfootImage image = getImage();  
     image.scale(40, 40);
     this.numCelda = numCelda;
     this.estado = estado;
}
public void act() 
{
 GreenfootImage image = getImage();  
 image.scale(40, 40);
}   
public int getCeldaS()
{
 return numCelda;
}
public boolean getEstado()
{
 return estado;   
}    
public void restarCelda()
{
 if(estado)
 {
  if(numCelda >=0)
  {
   numCelda -= 1;
  }
 }
}
public void checkState()
{
 if(numCelda<=0)
 {
  estado = false;   
 }
}    
}
