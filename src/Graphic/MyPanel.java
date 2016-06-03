package Graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
public class MyPanel extends JPanel implements KeyListener {
    private MyTank myTank;
    private List<EnemyTank> enemys;
    MyPanel(){
        this.setSize(Constant.MY_PANEL_WIDTH,Constant.MY_PANEL_HEIGHT);
        myTank=new MyTank(30,30,20,DirectConstant.NORTH);
        enemys=new ArrayList<>();
        for(int i=0;i<Constant.ENEMY_NUM;i++){
            EnemyTank enemyTank=new EnemyTank(50,20*(i+1),10,DirectConstant.SOUTH);
            Thread t=new Thread(enemyTank);
            t.start();
            enemys.add(enemyTank);
        }
    }
    public void paint(Graphics g) {
        super.paint(g);
        //首先画我的tank
        g.setColor(Color.RED);
        drawTank(Color.BLACK,myTank,g);
        for(Tank tank:enemys){
            System.out.println("--");
            drawTank(Color.YELLOW,tank,g);
            if(tank.getLoadBullets()!=null){
                System.out.println(2222);
                for(Bullet bullet:tank.getLoadBullets()){
                    System.out.println(3333);
                    drawBullet(Color.BLUE,bullet,g);
                }
            }
        }
        System.out.println("repaint");
    }

    public void startRun(){
        while(!enemys.isEmpty()){
            boolean myTankHitted=false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(EnemyTank enemyTank:enemys){
                for(Bullet bullet:enemyTank.getLoadBullets()){
                    if(beHitted(myTank,bullet)){
                        myTankHitted=true;
                        break;
                    }
                }
                if(myTankHitted){
                    break;
                }
            }
            if(myTankHitted){
                System.out.println("game over");
                break;
            }
            repaint();
        }
    }
    private void drawBullet(Color color,Bullet bullet,Graphics g){
        g.setColor(color);
        g.fillOval(bullet.getX(),bullet.getY(),bullet.getRadius(),bullet.getRadius());
    }
    private void drawTank(Color color,Tank tank,Graphics g){
        g.setColor(color);
        switch(tank.getDirect()){
            case DirectConstant.NORTH:
                //炮台
                g.fillOval(tank.getX()-tank.getWarehouseWidth()/2,tank.getY()-tank.getWarehouseWidth()/2,tank.getFortRadius()*2,tank.getFortRadius()*2);
                //炮筒
                g.drawLine(tank.getX(),tank.getY(),tank.getX(),tank.getY()-tank.getBarrelLength());
                //中仓
                g.drawRect(tank.getX()-tank.getWarehouseWidth()/2,tank.getY()-tank.getWarehouseWidth()/2,tank.getWarehouseWidth(),tank.getWarehouseWidth());
                //轮毂1
                g.fillRect(tank.getX()-tank.getWarehouseWidth()/2-tank.getWheelShort(),tank.getY()-tank.getWheelLong()/2,tank.getWheelShort(),tank.getWheelLong());
                //轮毂2
                g.fillRect(tank.getX()+tank.getWarehouseWidth()/2,tank.getY()-tank.getWheelLong()/2,tank.getWheelShort(),tank.getWheelLong());
                break;
            case DirectConstant.EAST:
                //炮台
                g.fillOval(tank.getX()-tank.getWarehouseWidth()/2,tank.getY()-tank.getWarehouseWidth()/2,tank.getFortRadius()*2,tank.getFortRadius()*2);
                //炮筒
                g.drawLine(tank.getX(),tank.getY(),tank.getX()+tank.getBarrelLength(),tank.getY());
                //中仓
                g.drawRect(tank.getX()-tank.getWarehouseWidth()/2,tank.getY()-tank.getWarehouseWidth()/2,tank.getWarehouseWidth(),tank.getWarehouseWidth());
                //轮毂1
                g.fillRect(tank.getX()-tank.getWheelLong()/2,tank.getY()-tank.getWarehouseWidth()/2-tank.getWheelShort(),tank.getWheelLong(),tank.getWheelShort());
                //轮毂2
                g.fillRect(tank.getX()-tank.getWheelLong()/2,tank.getY()+tank.getWarehouseWidth()/2,tank.getWheelLong(),tank.getWheelShort());
                break;
            case DirectConstant.SOUTH:
                //炮台
                g.fillOval(tank.getX()-tank.getWarehouseWidth()/2,tank.getY()-tank.getWarehouseWidth()/2,tank.getFortRadius()*2,tank.getFortRadius()*2);
                //炮筒
                g.drawLine(tank.getX(),tank.getY(),tank.getX(),tank.getY()+tank.getBarrelLength());
                //中仓
                g.drawRect(tank.getX()-tank.getWarehouseWidth()/2,tank.getY()-tank.getWarehouseWidth()/2,tank.getWarehouseWidth(),tank.getWarehouseWidth());
                //轮毂1
                g.fillRect(tank.getX()-tank.getWheelShort()-tank.getWarehouseWidth()/2,tank.getY()-tank.getWheelLong()/2,tank.getWheelShort(),tank.getWheelLong());
                //轮毂2
                g.fillRect(tank.getX()+tank.getWarehouseWidth()/2,tank.getY()-tank.getWheelLong()/2,tank.getWheelShort(),tank.getWheelLong());
                break;
            case DirectConstant.WEST:
                //炮台
                g.fillOval(tank.getX()-tank.getWarehouseWidth()/2,tank.getY()-tank.getWarehouseWidth()/2,tank.getFortRadius()*2,tank.getFortRadius()*2);
                //炮筒
                g.drawLine(tank.getX(),tank.getY(),tank.getX()-tank.getBarrelLength(),tank.getY());
                //中仓
                g.drawRect(tank.getX()-tank.getWarehouseWidth()/2,tank.getY()-tank.getWarehouseWidth()/2,tank.getWarehouseWidth(),tank.getWarehouseWidth());
                //轮毂1
                g.fillRect(tank.getX()-tank.getWheelLong()/2,tank.getY()-tank.getWarehouseWidth()/2-tank.getWheelShort(),tank.getWheelLong(),tank.getWheelShort());
                //轮毂2
                g.fillRect(tank.getX()-tank.getWheelLong()/2,tank.getY()+tank.getWarehouseWidth()/2,tank.getWheelLong(),tank.getWheelShort());
                break;
        }
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode()==KeyEvent.VK_A){
            System.out.println("a");
            if(myTank.getDirect()==DirectConstant.WEST){
                if(canMove(myTank)){
                    myTank.move();
                }
            }
            else{
                myTank.setDirect(DirectConstant.WEST);
            }
        }
        else if(keyEvent.getKeyCode()==KeyEvent.VK_W){
            System.out.println("w");
            if(myTank.getDirect()==DirectConstant.NORTH){
                if(canMove(myTank)){
                    myTank.move();
                }
            }
            else{
                myTank.setDirect(DirectConstant.NORTH);
            }
        }
        else if(keyEvent.getKeyCode()==KeyEvent.VK_S){
            System.out.println("s");
            if(myTank.getDirect()==DirectConstant.SOUTH){
                if(canMove(myTank)){
                    myTank.move();
                }
            }
            else{
                myTank.setDirect(DirectConstant.SOUTH);
            }
        }
        else if(keyEvent.getKeyCode()==KeyEvent.VK_D){
            System.out.println("d");
            if(myTank.getDirect()==DirectConstant.EAST){
                if(canMove(myTank)){
                    myTank.move();
                }
            }
            else{
                myTank.setDirect(DirectConstant.EAST);
            }
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }


    public boolean canMove(Tank tank){
        boolean canMove=false;
        int temp;
        switch(tank.getDirect()){
            case DirectConstant.NORTH:
                temp=myTank.getY()-myTank.getSpeed();
                if(temp>=myTank.getBarrelLength()&&temp>=myTank.getWheelLong()/2){
                    canMove=true;
                }
                break;
            case DirectConstant.EAST:
                temp=myTank.getX()+myTank.getSpeed();
                if(temp<=Constant.MY_PANEL_WIDTH-myTank.getBarrelLength()&&temp<=Constant.MY_PANEL_WIDTH-myTank.getWheelLong()/2){
                    canMove=true;
                }
                break;
            case DirectConstant.SOUTH:
                temp=myTank.getY()+myTank.getSpeed();
                if(temp<=Constant.MY_PANEL_HEIGHT-myTank.getBarrelLength()&&temp<=Constant.MY_PANEL_HEIGHT-myTank.getWheelLong()/2){
                    canMove=true;
                }
                break;
            case DirectConstant.WEST:
                temp=myTank.getX()-myTank.getSpeed();
                if(temp>=myTank.getWheelLong()/2&&temp>=myTank.getBarrelLength()){
                    canMove=true;
                }
                break;
        }
        return canMove;
    }
    public boolean beHitted(Tank tank,Bullet bullet){
        boolean beHitted=false;
        if(tank.getDirect()==DirectConstant.EAST||tank.getDirect()==DirectConstant.WEST){
            System.out.println("qqqqqqqqqqqqqqqqqqqqqqq");
            if(bullet.getX()>=tank.getX()-tank.getWheelLong()/2&&bullet.getX()<=tank.getX()+tank.getWheelLong()/2&&bullet.getY()>=tank.getY()-tank.getWarehouseWidth()/2-tank.getWheelShort()&&bullet.getY()<=tank.getY()+tank.getWarehouseWidth()/2+tank.getWheelShort()){
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                beHitted=true;
            }
        }
        if(tank.getDirect()==DirectConstant.NORTH||tank.getDirect()==DirectConstant.SOUTH){
            if(bullet.getX()>=tank.getX()-tank.getWarehouseWidth()/2-tank.getWheelShort()&&bullet.getX()<=tank.getX()+tank.getWarehouseWidth()/2+tank.getWheelShort()&&bullet.getY()>=tank.getY()-tank.getWheelLong()/2&&bullet.getY()<=tank.getY()+tank.getWheelLong()/2){
                beHitted=true;
            }
        }
        return beHitted;
    }
}
