package demo.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by liuyao-s on 2018/4/24.
 */

public class Test {
    public static void main(String[] args){
//        testBytes();
//        testChars();
//        testFile();
        testObject();
    }

    private static void testObject() {
        try {
            Person p = new Person();
            p.setAge(12);
            p.setName("ly");

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.dat"));
            oos.writeObject(p);
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.dat"));
            Person ret = (Person) ois.readObject();
            System.out.println("ret="+ret.getName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void testChars() {
        OutputStream os = System.out;
        OutputStreamWriter osw = new OutputStreamWriter(os);
        try {
            osw.write("hello ly");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("please input :");
        char[] chars = new char[1024];
        InputStream is = System.in;
        InputStreamReader isr = new InputStreamReader(is);
        try {
            int len = isr.read(chars);
            System.out.println(chars);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testBytes() {
        System.out.println("please input :");
        byte[] bytes = new byte[2];
        InputStream is = System.in;
        try {
            int len = 0;
            while((len=is.read(bytes))!=-1){
                if(bytes[len-1]==10){ //换行键
                    byte[] tmp = new byte[len];
                    System.arraycopy(bytes,0,tmp,0,len);
                    System.out.println("len="+len+" content="+new String(tmp));
                    break;
                }else{
                    System.out.println("len="+len+" content="+new String(bytes));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static void testFile()  {
        FileOutputStream fos = null;
        FileInputStream fis = null;
        byte[] b = {65,66,67,68,69};
        int i=0;

        try{
            // create new file output stream
            fos=new FileOutputStream("test.txt");
            // writes bytes to the output stream
            fos.write(b);
            // flushes the content to the underlying stream
            //fos.flush();

            // create new file input stream
            fis = new FileInputStream("test.txt");
            // read till the end of the file
            while((i=fis.read())!=-1) {
                System.out.print((char)i);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            // closes and releases system resources from stream
            try {
                if(fos!=null)fos.close();
                if(fis!=null)fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
