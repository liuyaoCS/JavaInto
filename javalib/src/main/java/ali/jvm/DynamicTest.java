package ali.jvm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicTest {
	interface IHello{
		public void say();
	}
	class Hello implements IHello{
		@Override
		public void say() {
			// TODO Auto-generated method stub
			System.out.println("hello");
		}
	}
	class ProxyHandler implements InvocationHandler{
		Object mOrigin;
		public ProxyHandler(Object origin) {
			// TODO Auto-generated constructor stub
			mOrigin=origin;
		}
		/**
		 * 这个proxy就是自动生成的代理对象，这里是vm.$Proxy0,也就是后面得到的IHello proxy。
		 * 但是在这里，一般不会使用，method.invoke唤起的也是原始被代理对象。
		 */
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			// TODO Auto-generated method stub
			System.out.println("proxy "+proxy.getClass().getName()+" coming");
			return method.invoke(mOrigin, args);
		}
		
	}
	public static void main(String[] args) {
		DynamicTest test=new DynamicTest();
		Hello hello=test.new Hello();
		ProxyHandler handler=test.new ProxyHandler(hello);
		Class[] interfaces=new Class[]{IHello.class};
		
		Object proxyObject= Proxy.newProxyInstance(hello.getClass().getClassLoader(),interfaces, handler);
		System.out.println("proxyObject->"+proxyObject.getClass().getName());
		IHello proxy=(IHello) proxyObject;
		System.out.println("proxy->"+proxy.getClass().getName());
		//IHello proxy=(IHello) Proxy.newProxyInstance(hello.getClass().getClassLoader(),interfaces, handler);
		proxy.say();
	}

}
