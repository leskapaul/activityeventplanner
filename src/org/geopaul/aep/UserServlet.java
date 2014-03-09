package org.geopaul.aep;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.geopaul.aep.entities.UserActivity;

import com.google.gson.Gson;


/**
 * The purpose of this servlet is to provide information about users.  Users 
 * come from Facebook, and so the only input value of interest to this servlet is user ID.
 */
@SuppressWarnings("serial")
public class UserServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(UserServlet.class.getName());
	
	private static final String P_USERID = "userId";
	private static final String P_USER_ACT_ID = "userActId";
	private static final String P_PAYLOAD = "payload";
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String requestType = req.getParameter("requestType");
		logger.info("received request type: " + requestType);
		
		if (requestType == null) {
			throw new IllegalArgumentException("parameter requestType is required");
		}
		
		resp.setContentType("application/json");
		UserRequestType urt = UserRequestType.valueOf(requestType);
		urt.process(req, resp);
	}
	
	static enum UserRequestType {
		GET_ACTIVITIES {
			@Override
			protected void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
				String userId = req.getParameter(P_USERID);
				List<UserActivity> list = Dao.getUserActivities(userId);
				Gson gson = new Gson();
				String json = gson.toJson(list);
				resp.getWriter().write(json);
			}
		},
		/* can test with payload of (URL encode first): {"userId":"geo","activityId":"2"}
		 * example: requestType=SAVE_ACTIVITY&payload=%7B%22userId%22%3A%22geo%22%2C%22activityId%22%3A%222%22%7D
		 */
		SAVE_ACTIVITY {
			@Override
			protected void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
				String json = req.getParameter(P_PAYLOAD);
				Gson gson = new Gson();
				UserActivity userAct = gson.fromJson(json, UserActivity.class);
				Dao.saveUserActivity(userAct);
			}
		},
		DELETE_ACTIVITY {
			@Override
			protected void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
				String userActId = req.getParameter(P_USER_ACT_ID);
				Dao.deleteUserActivity(Long.parseLong(userActId));  // TODO improve handling of parse error
			}
		};
		
		protected abstract void process(HttpServletRequest req, HttpServletResponse resp) throws IOException ; 
	}
	
}
