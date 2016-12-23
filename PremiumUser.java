/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecttt;

/**
 *
 * @author root
 */
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class PremiumUser extends User {

    public PremiumUser(DBConnect connect) {
        super(connect);
    }
    
    
    public Boolean reserveClass(Reservation res){
       Boolean flg=connect.addreservation(res);
       return flg;
    }
    public ArrayList<Reservation> viewmyReservation(String startDate,String endDate){
        ArrayList<Reservation> myReservations;
        myReservations=connect.viewmyreservation(this.userID,startDate,endDate);
        return myReservations;
    } 
    public Boolean cancelReservation(Reservation res){//missing notify users
        int CurrentresID=res.getReservationID();
        Boolean flg=connect.DeleteReservation(CurrentresID);
        if(flg==false){
            return false;
        }
        else{
            Integer waiting_resID=connect.ChooseFromWaiting(res.getTimeslot(), res.getDate().toString(), res.getClassroom());
            flg=connect.changeReservationStatus(waiting_resID,res.getClassroom().getClassroomID(),"reserved");
            return flg;
        }
    }
    public Boolean addEmaillist(Reservation res,ArrayList Emails){
        for (Object Email : Emails) {
            Boolean flg = connect.addemail(res.getReservationID(), Email.toString());
            if(flg==false) return flg;
        }
        return true;
    }
  
    public EmergencyRequest requestEmergencyRequest(Reservation res){
        
        Integer victimresID=connect.chooseAttackedReservation(res);
        if(victimresID==null) return null;
        EmergencyRequest emgreq=new EmergencyRequest();
        emgreq.setMyReservation(res);
        emgreq.setReplacementReservation(connect.getreservation(victimresID));
        return emgreq;
    }
    public void RequestRecurring(){
        
    }
      public Boolean NotifyUsers(Reservation res,int Status){
        return null;
    }
      public void CancelRecurring(Reservation res) throws CloneNotSupportedException {
          Reservation currentRes=(Reservation) res.clone();
         LocalDate lastdate=LocalDate.of(2017, Month.JANUARY, 14);
        while(currentRes.getDate().compareTo(lastdate)<=0) {//we can add multiple checks here
            this.cancelReservation(currentRes);
            int resID=currentRes.getReservationID()+1;
            currentRes=connect.getreservation(resID);
        }        
          
      }
}

    