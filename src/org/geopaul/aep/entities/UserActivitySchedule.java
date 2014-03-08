package org.geopaul.aep.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a schedule for a user activity.  As an example, 
 * suppose that we have a user activity that represents Joe Schmoe playing 
 * tennis.  An instance of this class could specify that Joe Schmoe is available 
 * to play tennis weeknights after 7pm and weekends until 7pm.
 */
public class UserActivitySchedule {

	private Set<TimeRange> availableTimeRanges = new HashSet<TimeRange>();
	private Set<TimeRange> blockedTimeRanges = new HashSet<TimeRange>();
	
	public boolean isAvailable(Date time) {
		// first check blocked times...
		for (TimeRange tr : blockedTimeRanges) {
			if (tr.isInRange(time)) {
				return false;
			}
		}
		// so far so good, now see if it's in an available time slot
		for (TimeRange tr : availableTimeRanges) {
			if (tr.isInRange(time)) {
				return true;
			}
		}
		// if available times were specified, then they weren't met, so return isEmpty
		return availableTimeRanges.isEmpty();
	}
	
	public Set<TimeRange> getAvailableTimeRanges() {
		return availableTimeRanges;
	}

	public Set<TimeRange> getBlockedTimeRanges() {
		return blockedTimeRanges;
	}

	private class TimeRange {
		Date begin;
		Date end;
		
		boolean isInRange(Date time) {
			return ((begin == null || begin.before(time) || begin.equals(time))
					&& (end == null || end.after(time) || end.equals(time)));
		}
	}
}
