package org.geopaul.aep;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.geopaul.aep.entities.DatastoreEntityConverter;
import org.geopaul.aep.entities.UserActivity;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

/**
 * I know the name of this one looks weird, but there doesn't seem to be all that 
 * much stuff to warrant separate DAOs just yet.  Let's split out as necessary 
 * rather than before we know that it's necessary.
 * 
 * For help using the datastore refer to https://developers.google.com/appengine/docs/java/gettingstarted/usingdatastore.
 */
public class Dao {
	private static final Logger logger = Logger.getLogger(Dao.class.getName());
	
	/**
	 * Retrieves the specified user's activities.
	 */
	public static List<UserActivity> getUserActivities(String userId) {
		logger.info("retrieving user activities for userId=" + userId);
		List<UserActivity> userActs = null;
		Key dsKey = KeyFactory.createKey(DatastoreEntityConverter.ENT_NAME_USER_ACT, userId);
		Query query = new Query(DatastoreEntityConverter.ENT_NAME_USER_ACT, dsKey);
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		List<Entity> results = ds.prepare(query).asList(FetchOptions.Builder.withLimit(5));
		if (results != null) {
			userActs = new ArrayList<UserActivity>();
			for (Entity ent : results) {
				logger.info("attempting to map result entity=" + ent);
				userActs.add(DatastoreEntityConverter.toUserActivity(ent));
			}
		}
		logger.info("retrieved user activities for userId=" + userId + ": " + userActs);
		return userActs;
	}

	public static void deleteUserActivity(Long userActId) {
		logger.info("deleting UserActivity id=" + userActId);
		Key dsKey = KeyFactory.createKey(DatastoreEntityConverter.ENT_NAME_USER_ACT, userActId);
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		ds.delete(dsKey);
		logger.info("deleted UserActivity id=" + userActId);
	}

	public static void saveUserActivity(UserActivity userAct) {
		logger.info("saving UserActivity=" + userAct);
		Entity ent = DatastoreEntityConverter.toDatastoreEntity(userAct);
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		ds.put(ent);
		logger.info("saved UserActivity=" + userAct);
	}
}
