package alarm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Components.CButton;
import Components.CTabbedPane;
import Components.RibbonMenu;
import Utility.Notifications;

public class ShowAlarmPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JLabel message;
	private CButton snooze,stop;
	private Alarm alarm;
	private MP3 mp3Player;
	private WavPlayer wavPlayer; 
	
	public ShowAlarmPanel(Alarm alarm){
		Notifications.showNotification("Alarm message: " + alarm.getAlertMessage());
		init(alarm);
		setAlarm();
		addActions();
		addToPanel();
		playSound();
	}
	
	public void init(Alarm alarm){
		message = new JLabel(RibbonMenu.time.getIcon());
		snooze = new CButton("Snooze", "snooze the alarm by 1 hour", 'n', null, null);
		stop = new CButton("Stop", "stop the alarm", 'S', null, null);
		this.alarm = alarm; 
	}
	
	public void setAlarm(){
		message.setText("<html><b>Message: </b>" + alarm.getAlertMessage() + "<html>");
	}
	
	public void addActions(){
		
		snooze.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(mp3Player != null){
					mp3Player.close();
				}
				
				if(wavPlayer != null){
					wavPlayer.stopSound();
				}
				
				Date dNow = alarm.getTime();
				Calendar cal = Calendar.getInstance();
				cal.setTime(dNow);
				cal.add(Calendar.MINUTE, 1);
				dNow = cal.getTime();

				alarm.setTime(dNow);
				((TableModel)AlarmTable.getInstance().getRealTable().getModel()).updateAlarms();
				
				CTabbedPane.getInstance().getPanel().removeAlarmPanel();
			}
		});
		
		stop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(mp3Player != null){
					mp3Player.close();
				}
				
				if(wavPlayer != null){
					wavPlayer.stopSound();
				}
				
				AlarmUtilities.getInstance().getList().remove(alarm);
				((TableModel)AlarmTable.getInstance().getRealTable().getModel()).updateAlarms();
				
				CTabbedPane.getInstance().getPanel().removeAlarmPanel();
			}
		});
		
	}
	
	public void addToPanel(){
		setBackground(CTabbedPane.getInstance().getBackground());
		setOpaque(false);
		setLayout(new BorderLayout());
		
		JPanel bPanel = new JPanel(new FlowLayout());
		bPanel.add(snooze);
		bPanel.add(stop);
		
		add(message,BorderLayout.CENTER);
		add(bPanel,BorderLayout.EAST);
	}
	
	public void playSound(){
		if(Preferences.getInstance().getDef().isSelected()){

			wavPlayer = new WavPlayer(ShowAlarmPanel.class.getResource("/other/alarm.wav"));
			
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					wavPlayer.playSound();
				}
			});

		}
		else{
			mp3Player = new MP3(Preferences.getInstance().getLastPath());
			mp3Player.play();
		}
	}
	
}
