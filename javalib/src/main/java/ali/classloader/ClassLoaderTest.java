package ali.classloader;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {

	/**
	 * 加载当前类下的统一路径的类文件
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ClassLoader loader=new ClassLoader() {
			@Override
			public Class<?> loadClass(String name)
					throws ClassNotFoundException {
				// TODO Auto-generated method stub
				String filename=name.substring(name.lastIndexOf(".")+1)+".class";
				InputStream is=getClass().getResourceAsStream(filename);
				if(is==null){
					return super.loadClass(name);
				}
				try {
					byte[] b=new byte[is.available()];
					is.read(b);
					return defineClass(name, b, 0, b.length);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return super.loadClass(name);
			}
		};
		Object obj=loader.loadClass("ali.classloader.ClassLoaderTest").newInstance();
		System.out.println(obj.getClass());
		
	}
	static{
		System.out.println("test");
	}

}
