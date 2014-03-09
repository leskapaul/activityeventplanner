Active Event Planner ("activityeventplanner" was an unfortunate mistake, please ignore that)

This is a mobile app meant to help people participate in activities that they're interested in.  
The idea is that users publish information about activities they're interested in and when they're 
available for those activities.  The server-side would then match users to other users via activities.


Architecture 
------------
* Mobile app using Facebook identity
* Mobile app sends user and event information from Facebook to server-side
* Server-side actively matches users to activities and sends notifications


Event Acquisition
-----------------
* Facebook Event Processing
Facebook events, available via the Facebook Graph API from authenticated instances of the mobile app, 
shall be sent to the server-side for processing.  Events shall be categorized (via a dictionary of key 
words if necessary) and persisted.  The relationship between users and event categories shall also be 
persisted (for the purpose of suggesting events/activities, i.e. 'you might be interested in...').
* Scraping the Internet
Similar to the use of Facebook, the app could scrape the internet (e.g. meetup.com, available APIs, etc) 
to gain awareness of events.
* Manual Entry
Events can be manually entered into the system, by administrators or by users.


Event Notifications (TODO1, TODO2)
-------------------
* Candidate Detection
When the system determines that two users are interested in the same activity, and are eligible do that 
activity together (e.g. connection via Facebook, proximity, etc), the system shall notify users.  
* Facebook Post Processing
Facebook posts could be processed in such a way that the app learns what users are interested in.  
For example, a person that often is tagged at restaurants could be labeled a "foodie" and sent related 
notifications. 


TODO
----
1) How shall users proceed from a notification of shared interest/eligibility to an actual event?  Some 
possibilities are:
  > let them post to one another via the app (requires persistence of posts, a significant dev effort)
  > let them connect to one another outside of the app (e.g. text, Facebook, whatever)
2) How shall the system know which activities/events to suggest to users?
  > friends via Facebook
  > proximity/schedule, e.g. north jersey after 7pm on weekdays
  > for proximity to be effective, the app would need to know where a user is likely to be at a particular time. 
    this could be predicted based on the user's past locations (periodic storage of user location).  users should 
    also be able to specify this information