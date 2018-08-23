package io.github.gronnmann.velox;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Print {
	
	private static String time() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}
	
	public static void debug(String msg) {
		if (Engine.getDebug()) {
			System.out.println("[DEBUG] [" + time() + "] " + msg);
		}
	}
	
	public static void info(String msg) {
		System.out.println("[INFO] [" + time() + "] " + msg);
	}
	
	public static void error(String msg) {
		System.err.println("[ERROR] [" + time() + "] " + msg);
	}
}
