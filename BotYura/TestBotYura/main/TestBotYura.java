package main;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBotYura {

	@Test
	public void testArgsParam() {
		String argi[] = {"-cmd=send_message", "-user_id=Yura", "-message=Hello!"};
		assertEquals("-cmd=send_message -user_id=Yura -message=Hello!", BotYura.argsParam(argi));
	}

}
