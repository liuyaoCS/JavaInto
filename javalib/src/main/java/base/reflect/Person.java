package base.reflect;

public class Person {
	public String name="ss";
	public Person(){
		System.out.println("person");
	}
	private Person(String name){
		System.out.println(name);
	}
	public Class[] method1(String info){
		System.out.println(info);
		return new Class[]{String.class};
	}
}
