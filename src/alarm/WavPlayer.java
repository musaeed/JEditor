package alarm;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class WavPlayer {
	
	private final int BUFFER_SIZE = 128000;
	private URL soundFile;
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	private SourceDataLine sourceLine;
	Thread thread;


	public WavPlayer(URL url){
		soundFile = url;
	}



	/**
	 * @param filename the name of the file that is going to be played
	 */
	public void playSound(){

		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					audioStream = AudioSystem.getAudioInputStream(soundFile);
				} catch (Exception e){
					e.printStackTrace();
					return;
				}

				audioFormat = audioStream.getFormat();

				DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
				try {
					sourceLine = (SourceDataLine) AudioSystem.getLine(info);
					sourceLine.open(audioFormat);
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

				sourceLine.start();

				int nBytesRead = 0;
				byte[] abData = new byte[BUFFER_SIZE];
				while (nBytesRead != -1) {
					try {
						nBytesRead = audioStream.read(abData, 0, abData.length);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (nBytesRead >= 0) {

						sourceLine.write(abData, 0, nBytesRead);
					}
				}

				sourceLine.drain();
				sourceLine.close();
			}
		});

		thread.start();
	}
	
	@SuppressWarnings("deprecation")
	public void stopSound(){
		thread.stop();
		sourceLine.stop();
	}

}
