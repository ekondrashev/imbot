import java.util.concurrent.CountDownLatch;


public class MainSudoku {

	public static void main(String[] args) throws InterruptedException {
		ElementMatrix[][] matrix=new ElementMatrix[9][9];
		
		for (int x = 0; x < 9; x++) 
			for (int y = 0; y < 9; y++) 
				{matrix[x][y]=new ElementMatrix(false, 0);}
		
		CountDownLatch ex=new CountDownLatch(9);
		FirstFill myFill=new FirstFill(matrix, ex);
		
		for (int i=0;i<9;i++){
			Thread myThread = new Thread(myFill);
			myThread.setName(String.valueOf(i+1));
			myThread.start();
		}
		
		ex.await();
		
		matrix=myFill.getMatrix();
		
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				System.out.print(matrix[x][y].getNumber() + "-");
			}
			System.out.println("");
		}
		
		

	}

}
