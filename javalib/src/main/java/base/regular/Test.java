package base.regular;

import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args){

		Boolean ret=Pattern.compile("[^abc&&q-t]").matcher("s").matches();
		System.out.println(ret);

		ret=Pattern.compile("\\n").matcher("\n").matches();
		System.out.println(ret);

		ret =Pattern.compile("((\\d+\\.){3}\\d+):(\\d+)").matcher("192.168.10.1:8888").matches();
		System.out.println(ret);
	}

}
