package demo.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liuyao on 2018/4/21.
 */

public class ClassReloader extends ClassLoader{
    private String classpath;
    private String classname="com.ly.freemarker.TestMain";
    public ClassReloader(String path){
        classpath=path;
    }

    @Override
    protected Class<?> findClass(String s) throws ClassNotFoundException {

        byte[] data=getData(s);
        if(data==null){
            throw new ClassNotFoundException();
        }else{
            return defineClass(classname,data,0,data.length);
        }
    }

    private byte[] getData(String s) {
        String path=classpath+s;
        try {
            InputStream is=new FileInputStream(path);
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            byte[] buffer=new byte[1024*2];
            int num=0;
            while((num=is.read(buffer))!=-1){
                baos.write(buffer,0,num);
            }
            return baos.toByteArray();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public static  void main(String[] args){
        String path="/Users/liuyao/home/project/gradleProject/freemarker/build/classes/main/com/ly/freemarker/";
        String name="TestMain.class";
        ClassReloader classReloader=new ClassReloader(path);
        try {
            Class c = classReloader.findClass(name);
            System.out.println(c.newInstance().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
