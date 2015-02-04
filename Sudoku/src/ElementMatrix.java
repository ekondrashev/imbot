public class ElementMatrix {
	private boolean status;
	private int number;

	ElementMatrix(boolean status, int number) {
		this.status = status;
		this.number = number;
	}

	public boolean getStatus() {
		return this.status;
	}
	
	public void setStatus(boolean status) {
		this.status=status;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
