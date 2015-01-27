




public class TwoGis {

	public static void main(String[] args) {
		Transport tr =new Transport("from=проспект шевченко Одесса to= польский спуск Одесса");
		System.out.println(tr.getTransport());
		tr.newQuery("from=проспект шевченко Одесса to=берлин");
		System.out.println(tr.getTransport());
	}
}
