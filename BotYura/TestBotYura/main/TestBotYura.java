/**
 * 
 */
package main;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Yura
 *
 */
public class TestBotYura {

	/**
	 * @throws java.lang.Exception
	 */
	
		

	@Test
	public void test() {
		String argi[] = {"-cmd=send_message", "-user_id=Yura", "-message=Hello!"};
		ComLineArg expected = new ComLineArg("send_message", "Yura", "Hello!");
		ComLineArg actual = BotYura.parsingComLine(argi);
		assertEquals(expected.cmd ,actual.cmd);
		assertEquals(expected.userId ,actual.userId);
		assertEquals(expected.userMessage ,actual.userMessage);
		
	}
	
	@Test
	public void test1() {
		String argi[] = {"-cmd=help"};
		ComLineArg expected = new ComLineArg("help", "", "");
		ComLineArg actual = BotYura.parsingComLine(argi);
		assertEquals(expected.cmd ,actual.cmd);
	}
		
	@Test
	public void test2() {
		String argi[] = {};
		ComLineArg expected = new ComLineArg("help", "", "");
		ComLineArg actual = BotYura.parsingComLine(argi);
		assertEquals(expected.cmd ,actual.cmd);
	}

}
