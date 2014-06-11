package core;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.JFrame;

import Components.CTabbedPane;
import Gui.JEditor;
import IOFactory.Reader;

public class StartService {

	private ServerSocket server;
	private Socket client;

	public StartService(String args[]){
		init(args);
	}

	public void init(String args[]){
		try {
			server = new ServerSocket(8888);
			startService();
		} catch (IOException e) {
			writeToServer(args);
			e.printStackTrace();
			return;
		}

	}

	public void startService(){

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				BufferedReader reader = null;
				while(true){
					try {

						client = server.accept();

						System.out.println("Connected to " + client.toString());

						reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

						String line;

						while((line = reader.readLine()) != null){
							if(line.endsWith("end")){
								line = line.substring(0, line.length()-4);
								openFiles(line);
								break;
							}
						}


					} catch (IOException e) {
						e.printStackTrace();
					}


				}
			}
		});

		thread.start();
	}

	public void writeToServer(String args[]){

		PrintWriter writer;

		try {

			client = new Socket("localhost", 8888);
			writer = new PrintWriter(client.getOutputStream());
			StringBuffer output = new StringBuffer("");
			for(String s : args){
				output.append(s+"$");
			}
			output.append("end");
			writer.write(output.toString());
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Opening the files in the instance already running!");
		System.exit(0);

	}

	public void openFiles(String paths){

		final StringTokenizer st = new StringTokenizer(paths, "$");
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				while(st.hasMoreTokens()){
					
					if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() != null && (CTabbedPane.getInstance().getPanel().getTextArea().getText() != null || CTabbedPane.getInstance().getPanel().getTextArea().getText().equals("")))
					CTabbedPane.getInstance().addTab("Untitled");

					Reader.loadFile(st.nextToken());
				}
				JEditor.frame.setVisible(true);
				if(JEditor.frame.getExtendedState() == JFrame.ICONIFIED){
					
					JEditor.frame.setExtendedState( JEditor.frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
				}
				
				JEditor.frame.toFront();
				JEditor.frame.repaint();
			}
		});	

	}

}
