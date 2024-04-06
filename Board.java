
package snakegame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener{
    
    
    public int dots;
    private Image apple;
    private Image dot;
    private Image head;
    
    private final int all_dots=900;
    private final int dots_size=10;
    private final int random=29;
    
    private int apple_x;
    private int apple_y;
    
    private Timer timer;
    
    private boolean ingame=true;
    
    private boolean left=false;
    private boolean right=true;
    private boolean up=false;
    private boolean down=false;
    
    private final int x[] = new int[all_dots];
    private final int y[] = new int[all_dots];
    
    Board(){
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(300,300)); 
        setFocusable(true);
        
        loadImages();
        initGame();
    }
    
    public void loadImages()
    {
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/apple.png"));
        apple=i1.getImage();
        
        ImageIcon i2= new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/dot.png"));
        dot=i2.getImage();
        
        ImageIcon i3= new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/head.png"));
        head=i3.getImage();
    }
    
    
    public void  initGame(){
        dots= 3; 
        
        for(int i=0;i<dots;i++)
        {
            y[i]=50;
            x[i]=50-dots_size*i;
        }
        
        locateApple();
        
        timer= new Timer(140, this);
        timer.start();
    }
    
    public void locateApple(){
       int r =(int)(Math.random()*random);
       apple_x = r*dots_size;
       
       r=(int)(Math.random()*random);
       apple_y=r*dots_size;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        draw(g);
    }
    
    public void draw(Graphics g){
        if(ingame){
           g.drawImage(apple, apple_x, apple_y, this);
        
           for(int i=0;i<dots;i++){
             if(i==0)
             {
                 g.drawImage(head, x[i], y[i], this);
             }
             else{
                 g.drawImage(dot, x[i], y[i], this);
             }
         }
         Toolkit.getDefaultToolkit().sync();
        }else{
            gameOver(g);
        }
    }
    
    public void gameOver(Graphics g){
        String msg="GAME OVER";
        Font font=new Font("SAN_SERIF",Font.BOLD,14);
        FontMetrics metrices=getFontMetrics(font);
        g.setColor(Color.WHITE);
        g.drawString(msg,(300-metrices.stringWidth(msg))/2, 300/2);
        
    }
    
    public void move(){
        for(int i=dots;i>0;i--)
        {
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        if(left){
            x[0]=x[0]-dots_size;
        }
        if(right){
            x[0]=x[0]+dots_size;
        }
        if(up){
            y[0]=y[0]-dots_size;
        }
        if(down){
            x[0]=x[0]+dots_size;
        }
    }
    
    public void checkApple(){
        if((x[0]==apple_x)&&(y[0]==apple_y)){
            dots++;
            locateApple();
        }
    }
    
    public void checkCollision(){
        for(int i=dots;i>0;i--){
            if((i>4)&&(x[0]==x[i]) && (y[0]==y[i])){
                ingame=false;
            }
        }
        
        if(y[0]>300){
            ingame=false;
        }
        if(x[0]>300){
            ingame=false;
        }
        if(x[0]<300){
            ingame=false;
        }
        if(y[0]<300){
            ingame=false;
        }
        if(!ingame){
            timer.stop();
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if(ingame){
           checkApple();
           move();
           repaint();
           checkCollision();}
    }
    
    public class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            int key=e.getKeyCode();
            
            if(key == KeyEvent.VK_LEFT && (!right)){
                left=true;
                up=false;
                down=false;
                //yaha right ko false ni kia kyuki pehle snake neeche turn lega fir right mea jyega, turn lega
            }
            
            if(key == KeyEvent.VK_RIGHT && (!left)){
                right=true;
                up=false;
                down=false;
               
            }
            
            if(key == KeyEvent.VK_UP && (!down)){
                left=true;
                up=true;
                right=false;
                
            }
            
            if(key == KeyEvent.VK_DOWN && (!up)){
                left=true;
                right=false;
                down=true;
                
            }
        }
    }
}
