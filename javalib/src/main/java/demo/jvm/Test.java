package demo.jvm;

/**
 * Created by liuyao-s on 2018/4/25.
 * javap -cp build\classes\java\main\ -v demo.jvm.Test
 */

public class Test {
    private static String str = "hello";
    public static void main(String[] args){
        System.out.println(str);
    }
}
