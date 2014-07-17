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
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

import org.freixas.jcalendar.JCalendar;

import Components.CButton;
import Components.CLabel;
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
	
public static void addAlarmDialog(){
		
		final JDialog dialog = new JDialog();
		CButton add = new CButton("Set", "set the alarm", 'S', null,null);
		CButton cancel = new CButton("Cancel", "cancel and go back",'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);
		JPanel bPanel = new JPanel(new FlowCustomLayout(FlowLayout.RIGHT));
		JPanel mainPanel = new JPanel(new GridLayout(1,2)) , left = new JPanel(new FlowCustomLayout(FlowLayout.LEFT)), right = new JPanel();
		final JEditorPane message = new JEditorPane();
		final JRadioButton low = new JRadioButton("Low");
		final JRadioButton mid = new JRadioButton("Mid");
		final JRadioButton high = new JRadioButton("High");
		final CLabel priority = new CLabel("Priority:");
		JScrollPane scroll = new JScrollPane(message);
		final JCalendar calender = new JCalendar(JCalendar.DISPLAY_DATE | JCalendar.DISPLAY_TIME,true);
		
		message.setSize(20, 10);
		mid.setSelected(true);
	
		
		right.setLayout(new BorderLayout());
		JPanel mPanel = new JPanel(new BorderLayout());
		mPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Message"));
		mPanel.add(scroll , BorderLayout.CENTER);
		right.add(mPanel , BorderLayout.CENTER);
		
		JPanel pPanel = new JPanel(new FlowLayout());
		pPanel.add(priority);
		pPanel.add(low);
		pPanel.add(mid);
		pPanel.add(high);
		
		right.add(pPanel , BorderLayout.SOUTH);
		
		left.add(calender);
		
		mainPanel.add(left);
		mainPanel.add(right);
		
		
		bPanel.add(add);
		bPanel.add(cancel);
		
		low.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mid.setSelected(false);
				high.setSelected(false);
			}
		});
		
		mid.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				low.setSelected(false);
				high.setSelected(false);
			}
		});
		
		high.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mid.setSelected(false);
				low.setSelected(false);
			}
		});
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				AlarmUtilities.getInstance().addToList(new Alarm(calender.getDate(), message.getText(), high.isSelected() ? Alarm.HIGH : mid.isSelected() ? Alarm.MED : Alarm.LOW));
				((TableModel)AlarmTable.getInstance().getRealTable().getModel()).updateAlarms();
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
		dialog.add(bPanel , BorderLayout.SOUTH);
		dialog.add(mainPanel , BorderLayout.CENTER);
		dialog.setTitle("New Alarm");
		dialog.setModal(true);
		dialog.setSize(new Dimension(580,350));
		dialog.setLocationRelativeTo(AlarmDialog.getInstance().getDialog());
		dialog.setVisible(true);
	}

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
