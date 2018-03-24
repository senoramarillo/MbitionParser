package model;

import java.util.List;
import java.util.Map;

public class Statistics {

	private List<ApplicationTime> appTime;
	private Map<String, Integer> searchOccurrences;
	private Map<String, Integer> vehicleStateOccurrences;

	public Statistics(List<ApplicationTime> appTime, Map<String, Integer> searchOccurrences,
			Map<String, Integer> vehicleStateOccurrences) {
		this.appTime = appTime;
		this.searchOccurrences = searchOccurrences;
		this.vehicleStateOccurrences = vehicleStateOccurrences;
	}

	public List<ApplicationTime> getAppTime() {
		return appTime;
	}

	public void setAppTime(List<ApplicationTime> appTime) {
		this.appTime = appTime;
	}

	public Map<String, Integer> getSearchOccurrences() {
		return searchOccurrences;
	}

	public void setSearchOccurrences(Map<String, Integer> searchOccurrences) {
		this.searchOccurrences = searchOccurrences;
	}

	public Map<String, Integer> getVehicleStateOccurrences() {
		return vehicleStateOccurrences;
	}

	public void setVehicleStateOccurrences(Map<String, Integer> vehicleStateOccurrences) {
		this.vehicleStateOccurrences = vehicleStateOccurrences;
	}
}