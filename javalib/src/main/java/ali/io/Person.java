package ali.io;

import java.io.Serializable;

/**
 * Created by liuyao-s on 2018/4/24.
 */

public class Person implements Serializable{
    int age;
    String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
