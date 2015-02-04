import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class FirstFill implements Runnable {

	private ElementMatrix[][] matrix;
	private CountDownLatch _cdl;

	private ElementMatrix[] array = { new ElementMatrix(false, 1),
			new ElementMatrix(false, 2), new ElementMatrix(false, 3),
			new ElementMatrix(false, 4), new ElementMatrix(false, 5),
			new ElementMatrix(false, 6), new ElementMatrix(false, 7),
			new ElementMatrix(false, 8), new ElementMatrix(false, 9) };

	public FirstFill(ElementMatrix[][] matrix, CountDownLatch _cdl) {
		this.matrix = matrix;
		this._cdl = _cdl;
	}

	private void fillMatrix() {
		int x, countNumber, countDown, quater, addX, addY, kof;
		countNumber = new Random().nextInt(6);
		countDown = 0;
		while (countDown < countNumber) {
			x = new Random().nextInt(8);
			if (array[x].getStatus())
				continue;
			else {
				array[x].setStatus(true);
				countDown++;
			}

		}

		quater = Integer.valueOf(Thread.currentThread().getName());
		addX = 0;
		addY = 0;
		switch (quater) {
		case 1:
			addX = 0;
			addY = 0;
			break;
		case 2:
			addX = 3;
			addY = 0;
			break;
		case 3:
			addX = 6;
			addY = 0;
			break;
		case 4:
			addX = 0;
			addY = 3;
			break;
		case 5:
			addX = 3;
			addY = 3;
			break;
		case 6:
			addX = 6;
			addY = 3;
			break;
		case 7:
			addX = 0;
			addY = 6;
			break;
		case 8:
			addX = 3;
			addY = 6;
			break;
		case 9:
			addX = 6;
			addY = 6;
			break;
		}
		kof = 0;
		for (x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (array[y + kof].getStatus()) {
					matrix[x + addX][y + addY].setNumber(array[y + kof].getNumber());
					matrix[x + addX][y + addY].setStatus(true);
				}
			}
			kof = kof + 3;
		}
		_cdl.countDown();
	}

	
	public ElementMatrix[][] getMatrix(){
		return this.matrix;
	}
	
	@Override
	public void run() {
		fillMatrix();

	}

}
