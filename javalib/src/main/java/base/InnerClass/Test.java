package base.InnerClass;


public class Test {
	private void method(){
		final int tmp=1;
		class Inner2{
			private void Test(){
				System.out.println(tmp);
			}
		}
		new Inner2().Test();
	}
	public static void main(String[] args) {
		new Test().method();
	}
}
