package Graphic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
public class Tank {
    private int x;
    private int y;
    private int wheelLong=15;
    private int wheelShort=5;
    private int fortRadius=5;
    private int warehouseWidth=10;
    private int barrelLength=8;
    private int speed;
    private int direct;
    private List<Bullet> loadBullets;

    public Tank(int x, int y, int speed, int direct) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direct = direct;
        loadBullets=new ArrayList<>();
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

    public int getWheelLong() {
        return wheelLong;
    }

    public void setWheelLong(int wheelLong) {
        this.wheelLong = wheelLong;
    }

    public int getWheelShort() {
        return wheelShort;
    }

    public void setWheelShort(int wheelShort) {
        this.wheelShort = wheelShort;
    }

    public int getFortRadius() {
        return fortRadius;
    }

    public void setFortRadius(int fortRadius) {
        this.fortRadius = fortRadius;
    }

    public int getWarehouseWidth() {
        return warehouseWidth;
    }

    public void setWarehouseWidth(int warehouseWidth) {
        this.warehouseWidth = warehouseWidth;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getBarrelLength() {
        return barrelLength;
    }

    public void setBarrelLength(int barrelLength) {
        this.barrelLength = barrelLength;
    }

    public void move(){
        switch(direct){
            case DirectConstant.NORTH:
                y-=speed;
                break;
            case DirectConstant.EAST:
                x+=speed;
                break;
            case DirectConstant.SOUTH:
                y=y+speed;
                break;
            case DirectConstant.WEST:
                x-=speed;
        }
    }

    public List<Bullet> getLoadBullets() {
        return loadBullets;
    }

    public void setLoadBullets(List<Bullet> loadBullets) {
        this.loadBullets = loadBullets;
    }

    public Bullet  loadBullet(){return null;}
}
