import org.python.util.PythonInterpreter;

public class Currency {
	public static String getCurrencyNBU() {
		String cur = "";
		try {
			PythonInterpreter inter = new PythonInterpreter();
			inter.exec("import httplib");
			inter.exec("from datetime import date");
			inter.exec("today = date.today()");
			inter.exec("day=today.strftime(\"%Y/%m/%d\")");
			inter.exec("cur = {\"RUB\":\"643\", \"USD\":\"840\", \"EUR\":\"978\"}");
			inter.exec("conn = httplib.HTTPConnection(\"fin.1c.ua\")");
			inter.exec("for key,value in cur.items():\n\tconn.request(\"GET\", \"/1c/\"+value+\"/\"+day+\".tsv\")\n\tr1 = conn.getresponse()\n\tif r1.status==200:\n\t\tcur[key] = r1.read()\n\telse:\n\t\tcur[key] =\"Error connect\"");
			inter.exec("conn.close()");
			inter.exec("USD=\"USD-\"+cur[\"USD\"]");
			inter.exec("RUB=\"RUB-\"+cur[\"RUB\"]");
			inter.exec("EUR=\"EUR-\"+cur[\"EUR\"]");
			inter.exec("CURRENCY=USD+EUR+RUB");
			cur = inter.get("CURRENCY").asString().replaceAll("\r\n", " ");
			System.out.println(cur);
		} catch (Exception e) {
			cur=e.getMessage();
			System.out.println(cur);
		}
		return cur;

	}
}
