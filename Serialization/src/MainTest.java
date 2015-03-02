import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MainTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws FileNotFoundException, IOException, ClassNotFoundException {
	A a = new A(5, 5.5f, 3.9);
	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Serial.ise"));
	ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Serial.ise"));
	
	Date dat = new Date();
	oos.writeObject(a);
	oos.writeObject(dat);
	oos.flush();
	oos.close();
	A ab = (A) ois.readObject();
	Date date = (Date) ois.readObject();
	ois.close();
	
	assertEquals(dat, date);
	assertEquals(a.d, ab.d, 0);
	assertEquals(a.f, ab.f, 0);
	assertEquals(a.i, ab.i);
    }

}
