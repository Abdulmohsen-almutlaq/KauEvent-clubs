
package testing;

import java.sql.Date;

//import java.util.Date;



public class club {
    
    private int clubCode;
    private String clubName;
    private String clubDescription;
    private Date clubFounded;

    public club(int clubCode, String clubName, String clubDescription, Date clubFounded) {
        this.clubCode = clubCode;
        this.clubName = clubName;
        this.clubDescription = clubDescription;
        this.clubFounded = clubFounded;
    }

    public int getClubCode() {
        return clubCode;
    }

    public void setClubCode(int clubCode) {
        this.clubCode = clubCode;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubDescription() {
        return clubDescription;
    }

    public void setClubDescription(String clubDescription) {
        this.clubDescription = clubDescription;
    }

    public Date getClubFounded() {
        return clubFounded;
    }

    public void setClubFounded(Date clubFounded) {
        this.clubFounded = clubFounded;
    }
    
    
    
    
    
    
    
    
}
