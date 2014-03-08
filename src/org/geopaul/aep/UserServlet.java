package org.geopaul.aep;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * The purpose of this servlet is to provide information about users.  Users 
 * come from Facebook, and so the only input value of interest to this servlet is user ID.
 */
@SuppressWarnings("serial")
public class UserServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(UserServlet.class.getName());
	
	private static final String P_USERID = "userId";
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String requestType = req.getParameter("requestType");
		logger.info("received request type: " + requestType);
		
		if (requestType == null) {
			throw new IllegalArgumentException("parameter requestType is required");
		}
		UserRequestType urt = UserRequestType.valueOf(requestType);
		urt.process(req, resp);
	}
	
	static enum UserRequestType {
		GET_ACTIVITIES {
			@Override
			protected void process(HttpServletRequest req, HttpServletResponse resp) {
				String userId = req.getParameter(P_USERID);
				// TODO retrieve UserActivity objects for the given user id
			}
		},
		SAVE_ACTIVITY {
			@Override
			protected void process(HttpServletRequest req, HttpServletResponse resp) {
				String userId = req.getParameter(P_USERID);
				// TODO persist a UserActivity object for the given user id
			}
		},
		DELETE_ACTIVITY {
			@Override
			protected void process(HttpServletRequest req, HttpServletResponse resp) {
				String userId = req.getParameter(P_USERID);
				// TODO delete a UserActivity object for the given user id
			}
		};
		
		protected abstract void process(HttpServletRequest req, HttpServletResponse resp); 
	}
}
