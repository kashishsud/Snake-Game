
package snakegame;//jvm runs the code,and jvm takes the source package, but if the source package has some clas in it , it has to be mentoned. hence we mention the package name.
import javax.swing.*;
 
public class SnakeGame extends JFrame {
    
    SnakeGame()
    {
        setTitle("Snake Game");
        add(new Board());
        pack();//many times we have to reflect the changes. pack funaction is used to refresh the changes we have made. khule huuye frame ko bhi refresh kardega pack function.
         
        setLocation(100,100);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
      
        new SnakeGame();
    }
    
}

