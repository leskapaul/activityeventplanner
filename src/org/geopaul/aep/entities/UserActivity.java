package org.geopaul.aep.entities;

public class UserActivity {

	private Long id;  // the id of this UserActivity 
	private String userId;  
	private Long activityId;
	private UserActivitySchedule schedule;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public UserActivitySchedule getSchedule() {
		return schedule;
	}
	public void setSchedule(UserActivitySchedule schedule) {
		this.schedule = schedule;
	}
	
}
