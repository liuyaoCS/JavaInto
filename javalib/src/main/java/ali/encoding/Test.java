package ali.encoding;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

/**
 * Created by liuyao-s on 2018/4/24.
 * file ClassInfoTest.java storage as utf-8 bytes: 49 20 e5 90 9b
 * javac -encoding UTF-8
 * java -Dfile.encoding=UTF-8
 * 存的时候用UTF-8字节数组，运行时存储的都是用unicode字符，代码设计编解码才会转成utf-8字节数组
 */

public class Test {
    public static void main(String[] args){
        String content = "I 君";

        Class clz = content.getClass();
        try {
            Field field = clz.getDeclaredField("value");
            field.setAccessible(true);

            try {
                char[] v = (char[]) field.get(content);
                for(char c:v){
                    System.out.print(Integer.toHexString(c)+ " ");
                }
                System.out.println();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        String csn = Charset.defaultCharset().name();
        System.out.println("default charset="+csn);

        System.out.println(content);

        System.out.println("unicode : "+string2Unicode(content));

        try {
            byte[] bytes = content.getBytes("UTF-8");
            System.out.print("utf-8 bytes : ");
            for(byte b:bytes){
                System.out.print(Integer.toHexString(b)+ " ");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }
    private static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        return string.toString();
    }

}
