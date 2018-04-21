package com.example.javalib.classloader;

public class SystemProperties {
    public static void main(String[] args){
        testProperties();
    }

    private static void testProperties() {
        String path=System.getProperty("java.class.path");
        String[] paths=path.split(":");
        for (String p:paths) {
            System.out.println(p);
        }

        String ext=System.getProperty("java.ext.dirs");
        System.out.println(ext);

        String os=System.getProperty("os.name");
        System.out.println(os);

        java.util.Properties props = System.getProperties();
        java.util.Enumeration keys = props.keys();
        String key = null;
        while(keys.hasMoreElements()){
            key = keys.nextElement().toString();
            System.out.println(key + "=" + props.get(key));
        }
    }

}
