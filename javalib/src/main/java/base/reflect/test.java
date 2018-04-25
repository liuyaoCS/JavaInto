package base.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class test {
	private static final String CLASSNAME = "advance.reflect.Person";
	public static void main(String[] args) throws Exception{
		Class classz=Class.forName(CLASSNAME);
		Constructor c=classz.getConstructor(null);
		Person p=(Person) c.newInstance(null);
		
		Class classz1=Class.forName(CLASSNAME);
		Constructor c1=classz1.getDeclaredConstructor(String.class);
		c1.setAccessible(true);
		Person p1=(Person) c1.newInstance("liuyao");

		testMethod();
		testField();
	}
	private static void testMethod() throws Exception{
		
		Person pp=new Person();
		Class classz2=Class.forName(CLASSNAME);
		Method m=classz2.getMethod("method1", String.class);
		Class cs[]=(Class[]) m.invoke(pp, "ssinfo");
		System.out.println(cs[0]);
	}
	private static void testField() throws Exception{
		Person pp=new Person();
		Class classz2=Class.forName(CLASSNAME);
		Field f=classz2.getField("name");
		String name=(String) f.get(pp);
		System.out.println(name);
	}
	
}