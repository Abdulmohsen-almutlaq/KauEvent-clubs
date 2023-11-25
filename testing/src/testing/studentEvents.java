
package testing;

import java.sql.Date;







public class studentEvents {

    public studentEvents(int eventNum, String eventName, String KAU_ID) {
        this.eventNum = eventNum;
        this.eventName = eventName;
       
        this.KAU_ID = KAU_ID;
    }

   





    private int eventNum;
    private String eventName;
    private Date eventDate;
    private String KAU_ID;
   
            
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

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
    
    
    



    
}
