package alarm;

import java.util.Date;

public class Alarm {
	
	private Date time;
	private String alertMessage;
	private String priority;

	public static String LOW = "Low";
	public static String MED = "Medium";
	public static String HIGH = "High";

	public Alarm(Date time , String alertMessage, String priority){
		this.time = time;
		this.alertMessage = alertMessage;
		this.priority = priority;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getAlertMessage() {
		return alertMessage;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
}
