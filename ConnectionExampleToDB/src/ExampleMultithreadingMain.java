import java.util.ArrayList;

public class ExampleMultithreadingMain {
	static ExampleMultithreadingSend mSet;
	static ExampleMultithreadingGet mGet;
	static ArrayList<String> list = new ArrayList<String>();

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		mSet = new ExampleMultithreadingSend(list);
		mGet = new ExampleMultithreadingGet(list);

		Thread myThready = new Thread(mSet);
		myThready.start();

		Thread myGetThready = new Thread(mGet);
		myGetThready.start();
		
		myThready.join();
		myGetThready.join();
	}
}
