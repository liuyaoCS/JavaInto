package ali.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 *-vmargs -Xms128M -Xmx512M -XX:PermSize=64M -XX:MaxPermSize=128M
 *-vmargs 说明后面是VM的参数，所以后面的其实都是JVM的参数了
 *-Xms128m JVM初始分配的堆内存
 *-Xmx512m JVM最大允许分配的堆内存，按需分配
 *-XX:PermSize=64M JVM初始分配的非堆内存
 *-XX:MaxPermSize=128M JVM最大允许分配的非堆内存，按需分配
 */
public class OOMTest {
	static long count=0;
	static class OOMObject{
//		@Override
//		protected void finalize() throws Throwable {
//			// TODO Auto-generated method stub
//			System.out.println("i am over");
//			super.finalize();
//		}
	}
	/**err语法上虽然可以被catch，但是不建议，因为它基本上不可以通过后续代码修复。
	 * 
	 * Java中的异常分为两大类：
　　   *1.Checked Exception（非Runtime Exception）
　　   * Java中凡是继承自Exception，而不继承自RuntimeException类的异常都是非运行时异常。
　　   *2.Unchecked Exception（Runtime Exception）
     * 运行时异常 RuntimeException类是Exception类的子类，它叫做运行时异常，Java中的所有运行时异常都会直接或者间接地继承自RuntimeException类。
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DeepOOM();               
		//StackOF();               
		//RuntimeConstantPoolOOM();
	}
	/**
	 * java.lang.OutOfMemoryError: GC overhead limit exceeded
	 * 1.6包括以前，会把运行时常量池放在方法区，这是个永久代，不会gc，因此会很快报oom。
	 * 1.7以后移出了，受gc控制，报GC overhead limit exceeded是因为GC花了大量时间来回收无用对象。
	 */
	private static void RuntimeConstantPoolOOM() {
		// TODO Auto-generated method stub
		List<String> lists=new ArrayList<>();
		while(true){
			lists.add(String.valueOf(count++).intern());
		}
	}
	/**
	 * java.lang.StackOverflowError
	 */
	private static void StackOF() {
		// TODO Auto-generated method stub
		count++;
		if(count%100==0)System.out.println("method "+count+" times");
		StackOF();
	}
	/**
	 * java.lang.OutOfMemoryError: Java heap space
	 */
	private static void DeepOOM() {
	
		List<OOMObject> lists=new ArrayList<OOMObject>();
		try {
			while(true){
				
				//new OOMObject();
				lists.add(new OOMObject());//lists保持对OOMObject的引用，这样不会GC
				count++;
				if(count%100000==0)System.out.println("create "+count+" times");
				
			}
		} catch (OutOfMemoryError e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	

}
