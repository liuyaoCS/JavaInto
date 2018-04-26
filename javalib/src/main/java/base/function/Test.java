package base.function;

/**
 * Created by liuyao-s on 2018/4/26.
 * java 基本类型传值的拷贝，引用类型传递引用【的拷贝，也就是引用，其实内部是拷贝了对象的指针】
 * c++ 默认所有类型都是按值传递，除非指明&，表示按引用传递
 * 综上，c++和java都是传值的拷贝，c++多了&可以用来传引用
 */

public class Test {
    public static void main(String[] args){
        String s = "1";
        Person p =new Person(23);
        change(s,p);
        System.out.println("s="+s); //not changed
        System.out.println("age ="+p.getAge());//changed
    }
    private static void change(String s,Person p){
        s="2"; //这是重新赋值了
        p.setAge(24);
    }
}
