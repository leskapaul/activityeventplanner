package org.geopaul.aep.entities;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class DatastoreEntityConverter {
	
	public static final String ENT_NAME_USER_ACT = "UserActivity";

	public static Entity toDatastoreEntity(Activity activity, Key dsKey) {
		// TODO
		return null;
	}
	
	public static Entity toDatastoreEntity(UserActivity userAct) {
		Entity ent = userAct.getId() == null ? new Entity(ENT_NAME_USER_ACT)
				: new Entity(ENT_NAME_USER_ACT, userAct.getId());
		ent.setProperty("activityId", userAct.getActivityId());
		ent.setProperty("userId", userAct.getUserId());
		ent.setProperty("schedule", userAct.getSchedule());
		return ent;
	}
	
	public static Activity toActivity(Entity entity) {
		// TODO
		return null;
	}
	
	public static UserActivity toUserActivity(Entity entity) {
		UserActivity userAct = new UserActivity();
		userAct.setActivityId((Long) entity.getProperty("activityId"));
		Key key = entity.getKey();
		if (key != null) {
			userAct.setId(key.getId());
		}
		userAct.setUserId((String) entity.getProperty("userId"));
		userAct.setSchedule((UserActivitySchedule) entity.getProperty("schedule"));
		return userAct;
	}
	
	public static UserActivitySchedule toUserActivitySchedule(Entity entity) {
		// TODO
		return null;
	}
}
