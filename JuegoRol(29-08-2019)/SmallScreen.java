import greenfoot.*;

public class SmallScreen extends Actor {

    GreenfootImage message;
    Font messageFont;
    int counter = 0;
    int counterMax;
    
    public SmallScreen(String Message, int height, int width, int duration) 
    {
    
        message = new GreenfootImage(width, height);
        this.counterMax = duration;
        
        int letterCount = Message.length();
        int fontSize = (int)((6.0/(letterCount*4.0)) * width);

        messageFont = new Font("Consolas", false, false, fontSize);

        message.setColor(Color.WHITE);
        message.fill();

        message.setColor(Color.BLACK);
        message.fillRect(10, 10, width-20, height-20);
       
        message.setColor(Color.WHITE);
        message.setFont(messageFont);
        message.drawString(Message, (int)(fontSize/1.5), (int)(height/4 + fontSize/3));
        setImage(message);
    }
    public SmallScreen(String Message, int height, int width, int duration,String empate) 
    {
    
        message = new GreenfootImage(width, height);
        this.counterMax = duration;
        
        int letterCount = Message.length();
        int fontSize = (int)((6.0/(letterCount*4.0)) * width);

        messageFont = new Font("Consolas", false, false, fontSize);

        message.setColor(Color.WHITE);
        message.fill();

        message.setColor(Color.BLACK);
        message.fillRect(10, 10, width-20, height-20);
       
        message.setColor(Color.WHITE);
        message.setFont(messageFont);
        message.drawString(Message, (int)(fontSize/1.5), (int)(height/2 + fontSize/3));
        setImage(message);
    }
    @Override
    public void act() {
        super.act();

        counter += 1;

        if (counter >= counterMax) {
            getWorld().removeObject(this);
        }
    }

    public SmallScreen(String Message, int duration) {
        this(Message, 100, 400, duration);
    }

    public SmallScreen(String Message) {
        this(Message, 100, 400, 9999);
    }
    public void deleteScreen(){
        getWorld().removeObject(this);
    }


}
