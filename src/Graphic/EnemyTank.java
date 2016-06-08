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
        while(!this.isBeHitted()){
            try {
                Thread.sleep(1000);
                this.getLoadBullets().add(loadBullet());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.move();
        }
    }


}
