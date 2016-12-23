/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecttt;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 *
 * @author root
 */
public class Admin extends PremiumUser {

    public Admin(DBConnect connect) {
        super(connect);
    }
    public Boolean AddToScheudle(){
        return null;
    }
     public Boolean CancelfromScheudle(){
      return null;   
    }
     public Boolean ReplayEmergencyRequest(EmergencyRequest req){
         
         Boolean flg=connect.changeReservationStatus(req.getMyReservation().getReservationID(),req.getReplacementReservation().getClassroom().getClassroomID(), "reserved");
         if(flg==false ) return false;
         
         flg=connect.changeReservationStatus(req.getReplacementReservation().getReservationID(),null, "waiting");
         
         return flg;
     }
     public Boolean disapproveEmergencyRequest(){
         return null;
     }
     public void ViewRecurringRequests(){
          //need to select by status recurring waiting    
     }
     public ArrayList<Integer>  GenerateRecurringReport(Reservation recurRes) throws CloneNotSupportedException {
         //assume last week data given
         LocalDate lastdate=LocalDate.of(2017, Month.JANUARY, 14);
         
         //System.out.println(lastdate);
       
         Reservation res=(Reservation) recurRes.clone();
       
         Map<Integer,Integer> Roomsmap=new TreeMap();
         ArrayList<ArrayList<Integer>> saverooms=new ArrayList(); 
         while((res.getDate().compareTo(lastdate))<=0){
         
         
         ArrayList<Integer> rooms=connect.SearchAvaliableClasses(res.getDate().toString(), res.getTimeslot(), res.getNumberofStudents(), res.getMic(), res.getComp(),res.getProj(), res.getSmart());
         saverooms.add(rooms);
         for(int i=0;i<rooms.size();i++){
             int x=rooms.get(i);
             if(Roomsmap.containsKey(x)){
                 Roomsmap.put(x, Roomsmap.get(x)+1);
             }
             else Roomsmap.put(x, 1);
             
         }
         res.setDate(res.getDate().plus(1,ChronoUnit.WEEKS));
        //System.out.println("1"+res.getDate());

         }
         ArrayList<Integer> report=new ArrayList();
         res.setDate(recurRes.getDate());
         ArrayList<MyPair> sortedrooms=new ArrayList();
         for(Map.Entry<Integer,Integer> temp:Roomsmap.entrySet()){
            MyPair x = new MyPair(temp.getValue(),temp.getKey());
             sortedrooms.add(x);
             x=null;
         }
         Collections.sort(sortedrooms);
         
         while((res.getDate().compareTo(lastdate))<=0){
             //System.out.println(res.getDate());
             int i=0;
             int j=0;
             int flg=0;
             while(saverooms.get(i).contains(sortedrooms.get(j).getRoomID())==false && flg==0){
                 j++;
                 if(j>=saverooms.get(i).size()) flg=1;
             }
              if(flg==1){
                  report.add(null);
              }
              else report.add(sortedrooms.get(j).getRoomID());
           res.setDate(res.getDate().plus(1,ChronoUnit.WEEKS));

         }
         return report;
     }
     
     public void ApproveRecurringRequest(ArrayList<Integer> report, Reservation recurrRes){
         Reservation res=recurrRes;
         //connect.DeleteReservation(res.getReservationID());
         
        for (Integer report1 : report) {
            res.setClassroom(connect.getClassRoom(report1));
            res.setStatus("recurringreserved");
            connect.addreservation(res);
            res.setDate(res.getDate().plus(1,ChronoUnit.WEEKS));

        }
         
     }
     public void RefuseRecurringRequest(int resID){
         connect.DeleteReservation(resID);
     }
     public void AddClassRoom(){
         
     }
     public void viewSchedule(){
         
     }
}
