package alarm;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javazoom.jl.player.Player;

public class MP3 {
	private String filename;
	private Player player; 
	private FileInputStream fis;
	private BufferedInputStream bis;

	// constructor that takes the name of an MP3 file
	public MP3(String filename) {
		this.filename = filename;
	}

	public MP3(InputStream fi){

		bis = new BufferedInputStream(fi);
	}

	public void close() { if (player != null) player.close(); }

	// play the MP3 file to the sound card
	public void play() {
		try {

			if(filename != null){
				fis = new FileInputStream(filename);
				bis = new BufferedInputStream(fis);
			}

			player = new Player(bis);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// run in new thread to play in background
		new Thread() {
			public void run() {
				try { player.play(); }
				catch (Exception e) { e.printStackTrace(); }
			}
		}.start();


	}
}
