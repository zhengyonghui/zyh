/**
 * Created by Administrator on 2016/6/3.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，例如兔子都不死，问每个月兔子的总数是多少？
 */
public class Test {
    private List<Rabit> rabits;

    public Test() {
        rabits=new ArrayList<>();
        rabits.add(new Rabit(0));
    }

    public static void main(String[] args){
        Test test=new Test();
        System.out.println("2个月后共有兔子"+2*test.count(2));
        System.out.println(test.digui(2));
    }
    public int count(int month){
        for(int i=0;i<month;i++){
            int addNum=0;
            for(Rabit rabit:rabits){
                rabit.setAge(rabit.getAge()+1);
                if(rabit.getAge()>=3){
                    addNum++;
                }
            }
            for(int j=0;j<addNum;j++){
                rabits.add(new Rabit(0));
            }
        }
        return rabits.size();
    }
    public int digui(int n){
        int sum=1;
        if(n>1){
            sum=n*digui(n-1);
        }
        return sum;
    }
}
