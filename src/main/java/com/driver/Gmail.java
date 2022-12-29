package com.driver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Gmail extends Email {

    public Gmail(String emailId) {
        super(emailId);
    }


    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    int inboxSize=0,trashSize=0;
    TreeMap<Date,EmailId> Inbox =new TreeMap<>();
    TreeMap<Date,EmailId> trash =new TreeMap<>();

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        EmailId emailId=new EmailId(date,sender,message);
        if(this.inboxSize>this.inboxCapacity) {
            Date dat=Inbox.firstKey();
            trash.put(dat,Inbox.get(dat));
            Inbox.pollFirstEntry();
            this.inboxSize--;
            this.trashSize++;
        }
        Inbox.put(date,emailId);
        this.inboxSize++;
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(Date date:Inbox.keySet()){
            EmailId emailId=Inbox.get(date);
            if(emailId.getMessage().equals(message)){
                trash.put(date,emailId);
                Inbox.remove(date);
                inboxSize--;
                trashSize++;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(Inbox.size()==0){
            return  null;
        }else{
            Date date=Inbox.lastKey();
            return Inbox.get(date).getMessage();
        }

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(Inbox.size()==0){
            return null;
        }else{
            Date date=Inbox.firstKey();
            return Inbox.get(date).getMessage();
        }
    }





    public int findMailsBetweenDates(Date start, Date end) throws ParseException {
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count=0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String s1 = sdf.format(start);
        String s2 = sdf.format(end);

        Date date1=sdf.parse(s1);
        Date date2=sdf.parse(s2);

        for(EmailId emailId:Inbox.values()){
            Date date3=emailId.getDate();

            //date3>date1 and date3<date2
            if(date3.after(date1) || date3.equals(date1) && date3.before(date2)  || date3.equals(date2)){
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();

    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return  this.inboxSize;
    }
}

class EmailId{
    private   String message;
    private   Date date;
    private   String senderId;

    public EmailId(Date date, String senderId,String message) {
        this.message = message;
        this.date = date;
        this.senderId = senderId;
    }

    public String getMessage() {
        return this.message;
    }

    public Date getDate() {
        return this.date;
    }

    public String getSenderId() {
        return this.senderId;
    }
}