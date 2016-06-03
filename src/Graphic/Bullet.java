package Graphic;

/**
 * Created by Administrator on 2016/6/2.
 */
public class Bullet{
    private int speed=10;
    private int x;
    private int y;
    private int direct;
    private int radius=5;
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


    public void move() {
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
    }
    public boolean isLive(){
        if(x<=0||y<=0||x>=Constant.MY_PANEL_WIDTH||y>=Constant.MY_PANEL_HEIGHT){
            return false;
        }
        return true;
    }
}
