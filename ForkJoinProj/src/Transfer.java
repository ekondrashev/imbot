import java.util.Random;


public class Transfer implements Runnable {
	private Account[] array;

	public Transfer(Account[] array) {
		this.array = array;
	}

	public void moveMoney() {
		Random rand = new Random();
		int x = rand.nextInt(10);
		int y = rand.nextInt(10);
		int tsum = rand.nextInt(150);
 		synchronized (array[x]) {
 			synchronized (array[y]) {

				if (array[x].money >= tsum) {
					array[x].money = array[x].money - tsum;
					array[y].money = array[y].money + tsum;
				} else if (array[y].money >= tsum) {
					array[x].money = array[x].money + tsum;
					array[y].money = array[y].money - tsum;
				} else
					return;
 			}
 		}

	}

	public void run() {
		moveMoney();
	}
}
