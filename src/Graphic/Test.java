package Graphic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/27.
 */
public class Test {
    public static void main(String args[]){
        List<String> test=new ArrayList<>();
        test.add("hello");
        test.add("a");
        test.add("b");
        test.add("c");
        test.add("d");
        test.add("e");
        test.add("f");
        test.add("g");
        test.add("h");
        test.add("i");
        System.out.println("删除以前size="+test.size());
        List<String> needRemove=new ArrayList<>();
        for(int i=0;i<test.size();i++){
            String s=test.get(i);
            test.remove(s);
        }
        test.removeAll(needRemove);
        System.out.println("删除后的size="+test.size());

    }


}
