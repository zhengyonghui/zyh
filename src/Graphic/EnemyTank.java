package Graphic;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 */
public class EnemyTank extends Tank implements Runnable {

    EnemyTank(int x,int y,int speed,int direct){
        super(x,y,speed,direct);
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
                this.getLoadBullets().add(loadBullet());
                System.out.println("生成子弹");
                selectBullet(this.getLoadBullets());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.move();
        }
    }
    public void move(){

    }
    public void selectBullet(List<Bullet> bullets){
        for(Bullet bullet:bullets){
            bullet.move();
            if(!bullet.isLive()){
                bullets.remove(bullet);
            }
        }
    }
    public Bullet loadBullet(){
        Bullet bullet=new Bullet();
        switch(this.getDirect()){
            case DirectConstant.NORTH:
                bullet.setY(this.getY()-this.getBarrelLength());
                bullet.setX(this.getX());
                break;
            case DirectConstant.EAST:
                bullet.setX(this.getX()+this.getBarrelLength());
                bullet.setY(this.getY());
                break;
            case DirectConstant.SOUTH:
                bullet.setX(this.getX());
                bullet.setY(this.getY()+this.getBarrelLength());
                break;
            case DirectConstant.WEST:
                bullet.setX(this.getX()-this.getBarrelLength());
                bullet.setY(this.getY());
                break;
        }
        bullet.setDirect(this.getDirect());
        return bullet;
    }
}
