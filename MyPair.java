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
public class MyPair implements Comparable<MyPair> {
    private final int count;
    private final int roomID;

    public MyPair(int count, int roomID) {
        this.count = count;
        this.roomID = roomID;
    }
    public int compareTo(MyPair x) {

	 if(this.count==x.count) return this.roomID-x.roomID;
         return x.count-this.count;
	}

    public int getCount() {
        return count;
    }

    public int getRoomID() {
        return roomID;
    }

}
