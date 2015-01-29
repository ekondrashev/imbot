
public class HelloWorld extends Object {
	public static void main(String args[]) {
		new	Thread(new MyThread()).start();
		System.out.println("Hello from main");
		
	}
	static int a=0;
	int b=3;
	public void summa(){
		System.out.println("Summa a+b="+(a+b));
	}

}
