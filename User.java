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
import java.util.*;

public class User {
    protected int userID;
    protected String email;
    protected String UniversityPosition; //we will force the user to choose only the available positions in the GUI
    protected String phone_number;
    protected String Password; 
    protected DBConnect connect;

    public User(DBConnect connect) {
        this.connect = connect;
    }
    
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversityPosition() {
        return UniversityPosition;
    }

    public void setUniversityPosition(String UniversityPosition) {
        this.UniversityPosition = UniversityPosition;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

   
   
    
    public Boolean changePassword(int UserID,String oldPassword,String newPassword){
        Boolean flg=this.connect.checkuserwithIDandpassword(UserID, oldPassword);
        if(flg==false) 
        {
            return false;
        }
        else{
            flg=this.connect.updatepassword(UserID, newPassword);
            return flg;
        }
    }
    
    public ArrayList<Integer> viewAvailableRooms(String Time,String Date){
        ArrayList <Integer> Availablerooms;
        Availablerooms=connect.SearchAvaliableClasses(Date, Time, 0, 0, 0, 0, 0);
        return Availablerooms;
    }
    
}
