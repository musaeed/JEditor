package alarm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import org.freixas.jcalendar.JCalendar;

import Components.CButton;
import Layouts.FlowCustomLayout;


public class AlarmUtilities {
	private ArrayList<Alarm> list;
	private Timer timer;
	private static AlarmUtilities instance = null;
//	private MP3 player;
	static Clip clip;
//	private MakeSound sound;

	private AlarmUtilities(){
		list = new ArrayList<Alarm>();
		startTimer();
	}

	public static AlarmUtilities getInstance(){

		if(instance == null){
			instance = new AlarmUtilities();
		}
		
		return instance;
	}


	public ArrayList<Alarm> getList(){
		return list;
	}

	public void addToList(Alarm alarm){
		list.add(alarm);
	}


	public void startTimer(){

		timer = new Timer(10000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0 ; i < list.size() ; i++){
					if(list.get(i).getTime().before(Calendar.getInstance().getTime())){
						//showRingDialog(list.get(i));
					}
				}
			}
		});

		timer.start();

	}

	public Timer getTimer() {
		return timer;
	}

/*
	public void showRingDialog(final Alarm e){

		AlarmUtilities.getInstance().getTimer().stop();
		
		final JDialog dialog = new JDialog();

		JPanel mainPanel = new JPanel() , bPanel = new JPanel(new FlowCustomLayout(FlowLayout.RIGHT)) , left = new JPanel(new FlowCustomLayout(FlowLayout.LEFT)) , right = new JPanel(new BorderLayout());
		CButton snooze = new CButton("Snooze", "snooze the alarm", null, 'S'),stop = new CButton("Stop", "stop the alarm", KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), 'T');
		JEditorPane epane = new JEditorPane();
		JLabel image = new JLabel() , time = new JLabel(e.getTime().toString());


		if(Settings.getInstance().isDefaultTone()){
			sound = new MakeSound(Reader.class.getClass().getResource("/other/alarm.wav"));
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					sound.playSound();
				}
			});
		}
		else{
			player = new MP3(Settings.getInstance().getUserTonePath());
			player.play();
		}

		epane.setOpaque(false);
		epane.setBackground(dialog.getBackground());
		epane.setText(e.getAlertMessage());
		epane.setEditable(false);

		if(e.getPriority().equals(Alarm.HIGH)){
			image.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Ribbon.class.getClassLoader().getResource("images/high.png"))));
		}
		else if(e.getPriority().equals(Alarm.MED)){
			image.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Ribbon.class.getClassLoader().getResource("images/mid.png"))));
		}
		else{
			image.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Ribbon.class.getClassLoader().getResource("images/low.png"))));
		}

		bPanel.add(snooze);
		bPanel.add(stop);

		left.add(image);

		right.add(time , BorderLayout.NORTH);
		right.add(epane , BorderLayout.CENTER);

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		mainPanel.add(left);
		mainPanel.add(right);

		snooze.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Date dNow = e.getTime();
				Calendar cal = Calendar.getInstance();
				cal.setTime(dNow);
				cal.add(Calendar.MINUTE, 1);
				dNow = cal.getTime();

				e.setTime(dNow);

				((TableModel)Components.mainPanel.table.getModel()).updateAlarms();
				if(Settings.getInstance().isDefaultTone()){
					sound.stopSound();
				}
				else{
					player.close();				
				}
		
				AlarmUtilities.getInstance().getTimer().start();
				dialog.dispose();

			}
		});

		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e1) {

				AlarmUtilities.getInstance().getList().remove(e);
				((TableModel)Components.mainPanel.table.getModel()).updateAlarms();
				if(Settings.getInstance().isDefaultTone()){
					sound.stopSound();
				}
				else{
					player.close();				
				}
				AlarmClock.frame.validate();
				AlarmUtilities.getInstance().getTimer().start();
				dialog.dispose();
			}
		});

		dialog.setLayout(new BorderLayout());
		dialog.add(mainPanel , BorderLayout.CENTER);
		dialog.add(bPanel , BorderLayout.SOUTH);
		dialog.setModal(true);
		dialog.setTitle(e.getTime().toString());
		dialog.setSize(new Dimension(400,200));
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}*/

	public static void showEditDialog(final Alarm e){

		final JDialog dialog = new JDialog();
		JLabel alertMessage = new JLabel("Alert message:"),priority = new JLabel("Priority:");

		final JCalendar calendar = new JCalendar(JCalendar.DISPLAY_DATE | JCalendar.DISPLAY_TIME,false);
		calendar.setDate(e.getTime());
		final JTextField message = new JTextField(e.getAlertMessage());
		final JRadioButton low = new JRadioButton("Low"), mid = new JRadioButton("Mid"),high = new JRadioButton("High");

		if(e.getPriority().equals(Alarm.LOW)){
			low.setSelected(true);
		}
		else if(e.getPriority().equals(Alarm.MED)){
			mid.setSelected(true);
		}
		else{
			high.setSelected(true);
		}

		CButton save = new CButton("Save", "save the edited data", 'S',null, null);
		CButton cancel = new CButton("Cancel", "cancel and go back",'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);
		JPanel mainPanel  = new JPanel(); mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));JPanel north = new JPanel(new FlowLayout()),cent = new JPanel(new GridLayout(1,1)),south = new JPanel(new GridLayout(1,1)) , bPanel = new JPanel(new FlowCustomLayout(FlowLayout.RIGHT));

		JPanel rbPanel = new JPanel(new FlowLayout());

		rbPanel.add(low);
		rbPanel.add(mid);
		rbPanel.add(high);

		north.add(calendar);

		cent.add(alertMessage);
		cent.add(message);

		south.add(priority);
		south.add(rbPanel);

		mainPanel.add(north);
		mainPanel.add(cent);
		mainPanel.add(south);

		bPanel.add(save);
		bPanel.add(cancel);

		low.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mid.setSelected(false);
				high.setSelected(false);
			}
		});

		mid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				low.setSelected(false);
				high.setSelected(false);
			}
		});

		high.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				low.setSelected(false);
				mid.setSelected(false);
			}
		});

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				e.setTime(calendar.getDate());
				e.setAlertMessage(message.getText());
				e.setPriority(low.isSelected() ? Alarm.LOW : mid.isSelected() ? Alarm.MED : Alarm.HIGH);

				//((TableModel)Components.mainPanel.table.getModel()).updateAlarms();

				dialog.dispose();
			}

		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});

		dialog.setLayout(new BorderLayout());
		dialog.add(mainPanel , BorderLayout.CENTER);
		dialog.add(bPanel , BorderLayout.SOUTH);
		dialog.setTitle("Edit");
		dialog.setModal(true);
		dialog.setSize(new Dimension(400,470));
		dialog.setLocationRelativeTo(AlarmDialog.getInstance().getDialog());
		dialog.setVisible(true);
	}
/*
	public static void exitApplication(){
		if(Settings.getInstance().isExitAnimation()){
			Dimension size = AlarmClock.frame.getSize();

			for(int i = 1 ; i < 50 ; i++){
				AlarmClock.frame.setSize((int)size.getWidth()/i, (int)size.getHeight()/i);
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if(Settings.getInstance().getIsWidget()){
				new Widget();
				AlarmClock.frame.setVisible(false);
			}
			else{
				System.exit(0);
			}
		}
		else{
			if(Settings.getInstance().getIsWidget()){
				new Widget();
				AlarmClock.frame.setVisible(false);
			}
			else{
				System.exit(0);
			}
		}
	}*/

}
