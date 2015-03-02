import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

	A a = new A(5, 5.5f, 3.9);
	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Serial.ise"));
	ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Serial.ise"));
	
	oos.writeObject(a);
	oos.flush();
	oos.close();
	A ab = (A) ois.readObject();
	ois.close();

	System.out.println(ab.d);
    }

}
