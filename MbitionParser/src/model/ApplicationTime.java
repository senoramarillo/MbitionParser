package model;

import java.time.Duration;
import java.time.LocalTime;

public class ApplicationTime {
	private String appName;
	private LocalTime time;
	private Duration duration;

	public ApplicationTime(String appName, LocalTime time, Duration duration) {
		this.appName = appName;
		this.time = time;
		this.duration = duration;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "ApplicationTime{" + "appName='" + appName + '\'' + ", time=" + time + ", duration=" + duration + '}';
	}
}