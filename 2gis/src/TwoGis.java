




public class TwoGis {

	public static void main(String[] args) {
		Transport tr =new Transport("from=�������� �������� ������ to= �������� ����� ������");
		System.out.println(tr.getTransport());
		tr.newQuery("from=�������� �������� ������ to=������");
		System.out.println(tr.getTransport());
	}
}
