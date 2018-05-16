package base.generic;

import java.util.ArrayList;


public class Test<Q> {
	private Q mQ;
	public Test(Q q){
		mQ = q;
	}
	/**
	 * 会报Erasure of method b(ArrayList<Integer>) is the same as another method in type ClassInfoTest
	 * 即是改变一个方法的返回值也不可行，因为返回值不参与函数重载。
	 */
//	public static void b(ArrayList<Integer> list){
//		System.out.println("Integer");
//	}
	public static void b(ArrayList<String> list){
		System.out.println("string");
	}

	/**
	 * 泛型方法:类型参数位于返回类型之前
	 */
	public <T> T a(T t){
		return t;
	}
	public <T,K> T a(T t,K k){
		return t;
	}


	/**
	 *<T extends Comparable<? super T>> -> <T 继承父类/实现接口 Comparable<T的父类>>
	 * 确保父类实现接口后，子类可以直接使用。
	 */
	public static <T extends Comparable<? super T>> T findMax(T[] ps){
		int maxIndex=0;
		for(int i=0;i<ps.length;i++){
			if(ps[i].compareTo(ps[maxIndex])>0){
				maxIndex=i;
			}
		}
		return ps[maxIndex];
	}
	public static void main(String[] args) {
		String[] ss=new String[]{"a","ba","bc"};
		System.out.println(findMax(ss));

		Test<String> test = new Test<>("ss");
		//Test<int> testInt = new Test<>(1); //不能用基本类型实例化泛型参数
		//boolean ret = test instanceof Test<String>; //运行时类型检查只适用于原始类型
		//t=new T();//不能创建一个泛型类型：new Object()肯定不符合本意，可以通过反射实现
		//T[] arr=new T[];//不能创建泛型数组 :防止跳过数组存储检查
	}


}
