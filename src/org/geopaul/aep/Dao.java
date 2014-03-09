package org.geopaul.aep;

import java.util.List;

import org.geopaul.aep.entities.UserActivity;

/**
 * I know the name of this one looks weird, but there doesn't seem to be all that 
 * much stuff to warrant separate DAOs just yet.  Let's split out as necessary 
 * rather than before we know that it's necessary.
 */
public class Dao {

	/**
	 * Retrieves the specified user's activities.
	 */
	public static List<UserActivity> getUserActivities(String userId) {
		/* TODO retrieve from Google App Engine Datastore
		 * see https://developers.google.com/appengine/docs/java/gettingstarted/usingdatastore
		 */
		return null;
	}

	public static void deleteUserActivity(String userActId) {
		// TODO Auto-generated method stub
		
	}

	public static void saveUserActivity(UserActivity userAct) {
		// TODO Auto-generated method stub
		
	}
}
