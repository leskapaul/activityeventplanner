package org.geopaul.aep.entities;

public class UserActivity {

	private String id;  // the id of this UserActivity 
	private String userId;  
	private String activityId;
	private UserActivitySchedule schedule;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public UserActivitySchedule getSchedule() {
		return schedule;
	}
	public void setSchedule(UserActivitySchedule schedule) {
		this.schedule = schedule;
	}
	
}
