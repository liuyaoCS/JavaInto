package com.ly.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyao-s on 2018/4/23.
 */

public class InputNew {
    int a = 1;
    @Deprecated
    public List test1(String s){
        System.out.println("before  code");
        s+=":endl";

        List<String> lists=new ArrayList<>();
        lists.add(s);
        return lists;
    }

}

