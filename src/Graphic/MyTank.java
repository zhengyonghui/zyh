package Graphic;

/**
 * Created by Administrator on 2016/6/1.
 */
public class MyTank extends Tank implements Runnable{
    public MyTank(int x,int y,int speed,int direct){
        super(x,y,speed,direct);
    }

    @Override
    public void run() {

        System.out.println(1);
        switch (this.getDirect()){
            case DirectConstant.NORTH:
                this.setY(this.getY()-this.getSpeed());
                break;
            case DirectConstant.EAST:
                this.setX(this.getX()+this.getSpeed());
                break;
            case DirectConstant.SOUTH:
                this.setY(this.getY()+this.getSpeed());
                break;
            case DirectConstant.WEST:
                this.setX(this.getX()-this.getSpeed());
                break;
        }


    }
}
