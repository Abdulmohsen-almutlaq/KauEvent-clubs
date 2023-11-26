
package testing;

import java.sql.Date;


public class event {
    
    private int eventNum;
    private String eventName;
    private String eventDescription;
    private Date eventDate;
    private int Admin_ID;

    public event(int eventNum, String eventName, String eventDescription, Date eventDate, int Admin_ID) {
        this.eventNum = eventNum;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.Admin_ID = Admin_ID;
    }

    public int getEventNum() {
        return eventNum;
    }

    public void setEventNum(int eventNum) {
        this.eventNum = eventNum;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public int getAdmin_ID() {
        return Admin_ID;
    }

    public void setAdmin_ID(int Admin_ID) {
        this.Admin_ID = Admin_ID;
    }
    
    
    
    
    
    
    
    
    
}
