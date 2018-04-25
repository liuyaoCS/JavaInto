package ali.jvm;

public class GcTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		gcTest();
	}
	/**
	 * gc结果：[GC (System.gc())  67123K->66144K(126976K), 0.0012979 secs]
	 *		[Full GC (System.gc())  66144K->66057K(126976K), 0.0084694 secs]
     * 说明：1 年轻代有大量朝生夕死对象，gc了约1m，而年老代约100k
     *      2 年老代垃圾收集时间约是年轻代的8倍
     *      3 并没有回收b的空间，是因为虽然离开了b所在括号的作用域，但是没有其他变量使用b的slot，这种引用关系依然被保持。
     *        直到后面需要占用大量内存或者手动置空b（jit可能会忽略），再调垃圾回收就会回收b的空间。
	 */    
	private static void gcTest() {
		// TODO Auto-generated method stub
		{
			byte[] b=new byte[64*1024*1024];
		}
		//int a=0;
		System.gc();
	}

}
