package Utility;

import java.io.IOException;

public class Notifications {
	
	public static void showNotification(final String text){
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Runtime.getRuntime().exec(new String[]{"notify-send" ,"-i","/usr/share/jeditor/icon.png", "JEditor", text});
					Thread.sleep(100);
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});

		thread.start();
	}

}
