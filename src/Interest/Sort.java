package Interest;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Administrator on 2016/5/27.
 */
public class Sort {
    public static void main(String args[]){
        int[] a=new int[]{2,3,5,2,1,3,0,4,5,78,98,55,22};
/*        for(int i=0;i<100000;i++){
            a[i]= new Random().nextInt();
        }
        long begin=System.currentTimeMillis();
        bubbleSort(a);

        selectSort(a);

        quickSort(a);

        long end=System.currentTimeMillis();*/
        System.out.println("5<<2="+(5<<2));
        System.out.println("4>>2="+(4>>2));
/*        System.out.println("未排序"+ Arrays.toString(a));
        insertionSort(a);
        System.out.println("已排序"+Arrays.toString(a));*/

    }
    public static void bubbleSort(int[] array){
        int num=array.length;
        int temp;
        for(int i=1;i<num;i++){
            for(int j=0;j<num-i;j++){
                if(array[j]>array[j+1]){
                    temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }
    }
    public static void selectSort(int[] array){
        int num=array.length;
        for(int i=1;i<num;i++){
            int min=i-1;
            for(int j=i;j<num;j++){
                if(array[j]<array[min]){
                    min=j;
                }
            }
            if(min!=i-1){
                exchange(i-1,min,array);
            }
        }
    }
    public static void quickSort(int[] array){
        int num=array.length;
        for(int i=1;i<num;i++){
            int insert=i;
            for(int j=i-1;j>=0;j--){
                if(array[j]>array[i]){
                    insert=j;
                }
            }
            if(insert!=i){
                int temp=array[i];
                for(int x=i;x>insert;x--){
                    array[x]=array[x-1];
                }
                array[insert]=temp;
            }
        }

    }
    public static void insertionSort(int[] array){
        int num=array.length;
        for(int i=1;i<num;i++){
            int temp=array[i];
            for(int j=i-1;j>=0;j--){
                if(array[j]>temp){
                    array[j+1]=array[j];
                    array[j]=temp;
                }
                else{
                    break;
                }
            }
        }
    }
    public static void exchange(int x,int y,int[] array){
        int temp;
        temp=array[x];
        array[x]=array[y];
        array[y]=temp;
    }
}
