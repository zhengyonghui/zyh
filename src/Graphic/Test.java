package Graphic;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2016/5/27.
 */
public class Test extends JPanel {
    public static void main(String args[]){
        JButton button=new JButton("hello");
        JButton j1=new JButton("right");
        JButton j2=new JButton("left");
        JButton j3=new JButton("up");
        JButton j4=new JButton("bellow");
        JFrame jFrame=new JFrame();
        jFrame.setSize(300,300);
        jFrame.add(button,BorderLayout.CENTER);
        jFrame.add(j1,BorderLayout.EAST);
        jFrame.add(j2,BorderLayout.WEST);
        jFrame.add(j3,BorderLayout.NORTH);
        jFrame.add(j4,BorderLayout.SOUTH);
        Test t=new Test();
        jFrame.add(t);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
    public void paint(Graphics g){
        g.drawLine(0,1,100,1000);
        g.drawRect(0,0,12,23);
    }

}
