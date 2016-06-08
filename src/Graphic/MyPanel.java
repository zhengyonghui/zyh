package Graphic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
public class MyPanel extends JPanel implements KeyListener {
    private MyTank myTank;
    private List<EnemyTank> enemys;
    private List<Bomb> bombs;
    Image image1=null;
    Image image2=null;
    Image image3=null;
    MyPanel(){
        this.setSize(Constant.MY_PANEL_WIDTH,Constant.MY_PANEL_HEIGHT);
        myTank=new MyTank(30,30,20,DirectConstant.NORTH);
        enemys=new ArrayList<>();
        bombs=new ArrayList<>();
        for(int i=0;i<Constant.ENEMY_NUM;i++){
            EnemyTank enemyTank=new EnemyTank(50*(i+1),20*(i+1),10,DirectConstant.SOUTH);
            Thread t=new Thread(enemyTank);
            t.start();
            enemys.add(enemyTank);
        }
        try {
            image1=ImageIO.read(new File("images/bomb_1.gif"));
            System.out.println("image1");
            image2=ImageIO.read(new File("images/bomb_2.gif"));
            image3=ImageIO.read(new File("images/bomb_3.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void paint(Graphics g) {
        super.paint(g);
        //首先画我的tank
        if(!myTank.isBeHitted()){
            g.setColor(Color.RED);
            drawTank(Color.BLACK,myTank,g);
        }
        //画敌人的tank
        for(Tank tank:enemys){
            drawTank(Color.YELLOW,tank,g);
            if(tank.getLoadBullets()!=null){
                List<Bullet> bullets=tank.getLoadBullets();
                List<Bullet> needRemove=new ArrayList<>();
                for(Bullet bullet:bullets){
                    if(!bullet.isLive()){
                        needRemove.add(bullet);
                    }
                    drawBullet(Color.BLUE,bullet,g);
                }
                bullets.removeAll(needRemove);
            }
        }
        List<Bullet> bullets=myTank.getLoadBullets();
        List<Bullet> needRemove=new ArrayList<>();
        for(Bullet bullet:bullets){
            if(!bullet.isLive()){
                needRemove.add(bullet);
            }
            drawBullet(Color.RED,bullet,g);
        }
        bullets.removeAll(needRemove);


        //画bombs
        if(bombs.size()>0){
            drawBomb(g);
        }
    }
    public void drawBomb(Graphics g){
        System.out.println("draw bomb");
        List<Bomb> removedBombs=new ArrayList<>();
        for(Bomb bomb:bombs){
            if(bomb.getLife()>=6){
                g.drawImage(image1,bomb.getX(),bomb.getY(),30,30,this);
            }
            if(bomb.getLife()>=3){
                g.drawImage(image2,bomb.getX(),bomb.getY(),30,30,this);
            }
            if(bomb.getLife()>=0){
                g.drawImage(image3,bomb.getX(),bomb.getY(),30,30,this);
            }
            bomb.lifeDown();
            if(bomb.getLife()<=0){
                removedBombs.add(bomb);
            }
        }
        bombs.removeAll(removedBombs);
    }

    public void startRun(){
        while(!enemys.isEmpty()){
            boolean myTankHitted=false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //判断我有没有被击中
            for(EnemyTank enemyTank:enemys){
                for(Bullet bullet:enemyTank.getLoadBullets()){
                    if(bullet.isLive()){
                        if(beHitted(myTank,bullet)){
                            myTankHitted=true;
                            addBomb(myTank);
                            break;
                        }
                    }
                }
            }

            //判断敌人的坦克有没有被我击中
            List<EnemyTank> removed=new ArrayList<>();
            for(Bullet bullet:myTank.getLoadBullets()){
                if(bullet.isLive()){
                    for(EnemyTank e :enemys){
                        if (beHitted(e, bullet)) {
                            addBomb(e);
                            removed.add(e);
                        }
                    }
                }
            }
            enemys.removeAll(removed);
            repaint();
        }
    }
    private void drawBullet(Color color,Bullet bullet,Graphics g){
        g.setColor(color);
        g.draw3DRect(bullet.getX(),bullet.getY(),1,1,false);
    }
    public void addBomb(Tank tank){
        int x;
        int y;
        if(tank.getDirect()==DirectConstant.NORTH||tank.getDirect()==DirectConstant.SOUTH){
            x=tank.getX()-tank.getWarehouseWidth()/2-tank.getWheelShort();
            y=tank.getY()-tank.getWheelLong()/2;
        }
        else{
            x=tank.getX()-tank.getWheelLong()/2;
            y=tank.getY()-tank.getWarehouseWidth()/2-tank.getWheelShort();
        }
        bombs.add(new Bomb(x,y));
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
        else if(keyEvent.getKeyCode()==KeyEvent.VK_J){
            Bullet bullet=myTank.loadBullet();
            myTank.getLoadBullets().add(bullet);
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
            if(bullet.getX()>=tank.getX()-tank.getWheelLong()/2&&bullet.getX()<=tank.getX()+tank.getWheelLong()/2&&bullet.getY()>=tank.getY()-tank.getWarehouseWidth()/2-tank.getWheelShort()&&bullet.getY()<=tank.getY()+tank.getWarehouseWidth()/2+tank.getWheelShort()){
                beHitted=true;
            }
        }
        if(tank.getDirect()==DirectConstant.NORTH||tank.getDirect()==DirectConstant.SOUTH){
            if(bullet.getX()>=tank.getX()-tank.getWarehouseWidth()/2-tank.getWheelShort()&&bullet.getX()<=tank.getX()+tank.getWarehouseWidth()/2+tank.getWheelShort()&&bullet.getY()>=tank.getY()-tank.getWheelLong()/2&&bullet.getY()<=tank.getY()+tank.getWheelLong()/2){
                beHitted=true;
            }
        }
        if(beHitted){
            tank.setBeHitted(true);
            bullet.setLive(false);
        }
        return beHitted;
    }
}
