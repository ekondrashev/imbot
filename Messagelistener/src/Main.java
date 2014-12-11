import java.io.IOException;


public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try{
		RecvMes.recvMessage();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
