
package testing;

import java.sql.Date;


public class studentClubs  {
     private int clubCode;
     private String clubName;
    private Date JoinDate;
    private int KAU_ID;

    public studentClubs(int clubCode,   int KAU_ID,Date JoinDate) {
        this.clubCode = clubCode;
        //this.clubName = clubName;  
        this.KAU_ID = KAU_ID;
        this.JoinDate = JoinDate;
     
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }


    

    public int getClubCode() {
        return clubCode;
    }

    public void setClubCode(int clubCode) {
        this.clubCode = clubCode;
    }
    
    
  
    

    public int getKAU_ID() {
        return KAU_ID;
    }

    public void setKAU_ID(int KAU_ID) {
        this.KAU_ID = KAU_ID;
    }

    public Date getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(Date JoinDate) {
        this.JoinDate = JoinDate;
    }


    
    
    
    
    
    
    
    
    
    
    
}
