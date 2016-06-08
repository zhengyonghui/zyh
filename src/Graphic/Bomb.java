package Graphic;

/**
 * Created by Administrator on 2016/6/7.
 */
public class Bomb {
    private int x;
    private int y;
    private int life;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
        this.life=9;
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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
    public void lifeDown(){
        this.life-=3;
    }
}
