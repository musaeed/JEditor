package Utility;

import java.io.IOException;

public class Notifications {
	
	public static void showNotification(String text){
		try {
			Runtime.getRuntime().exec(new String[]{"notify-send" , "JEditor", text});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
