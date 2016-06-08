package Graphic;

/**
 * Created by Administrator on 2016/6/2.
 */
public class Bullet implements Runnable{
    private int speed=10;
    private int x;
    private int y;
    private int direct;
    private int radius=5;
    private boolean isLive=true;
    public Bullet(){}
    public Bullet(int speed, int x, int y, int direct, int radius) {
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.radius = radius;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public void run() {
        while(isLive()){
            try{
                Thread.sleep(500);
                switch (direct){
                    case DirectConstant.NORTH:
                        y-=speed;
                        break;
                    case DirectConstant.EAST:
                        x+=speed;
                        break;
                    case DirectConstant.SOUTH:
                        y+=speed;
                        break;
                    case DirectConstant.WEST:
                        x-=speed;
                        break;
                }
                if(overSpill()){
                    break;
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public boolean overSpill(){
        if(this.getX()<=0||this.getX()>=Constant.MY_PANEL_WIDTH||this.getY()<=0||this.getY()>=Constant.MY_PANEL_HEIGHT){
            this.setLive(false);
            return true;
        }
        return false;
    }
}
