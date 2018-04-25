package base.autoBoxing;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//自动装箱
		int a=1;
		Integer b=1;
		System.out.println(b.equals(a));
		System.out.println(a==b);
		//java 装箱类对-128到127这个范围内的数字有缓存，每个数字都分配了对象。
		Long g=127L;
		Long h=127L;
		System.out.println(g==h);
		Integer e=128;
		Integer f=128;
		System.out.println(e==f);
		System.out.println(e.equals(f));
		//自动装箱类的equal不处理数据类型，返回false
		Long l=3L;
		Integer m=1;
		Integer n=2;
		System.out.println(l.equals(m+n));
	
	}

}
