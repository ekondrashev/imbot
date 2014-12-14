import javax.swing.JOptionPane;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try{
		JOptionPane.showMessageDialog(null, "Hello I RECIEVING");
		RecvMes.recvMessage();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
