package Graphic;

import javax.swing.*;

/**
 * Created by Administrator on 2016/6/1.
 */
public class MyFrame extends JFrame {
    MyFrame(){
        this.setSize(Constant.MY_FRAME_WIDTH,Constant.MY_FRAME_HEIGHT);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        MyPanel myPanel=new MyPanel();
        MyFrame myFrame=new MyFrame();
        myFrame.add(myPanel);
        myFrame.addKeyListener(myPanel);
        myPanel.startRun();
    }
}
